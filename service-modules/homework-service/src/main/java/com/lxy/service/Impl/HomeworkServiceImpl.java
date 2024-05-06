package com.lxy.service.Impl;


import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxy.mapper.ClassHomeworksMapper;
import com.lxy.mapper.HomeworksMapper;

import com.lxy.service.HomeworkService;
import com.lxy.userEntity.BO.HomeworkClassBO;
import com.lxy.userEntity.ClassHomeworks;
import com.lxy.userEntity.Homeworks;
import com.lxy.userEntity.VO.HomeworkInfoVO;
import com.lxy.userEntity.VO.HomeworkStudentVO;
import com.lxy.utils.FileStorageComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HomeworkServiceImpl extends ServiceImpl<HomeworksMapper, Homeworks> implements HomeworkService {

    private final HomeworksMapper homeworksMapper;

    private final ClassHomeworksMapper classHomeworksMapper;

    private final FileStorageComponent fileStorageComponent;


    @Override
    @Transactional
    public int createHomework(MultipartFile file, HomeworkClassBO homeworksBO) {
        Homeworks homework = homeworksBO.getHomeworks();
        // 为作业生成唯一的ID
        Long homeworkId = IdWorker.getId();
        homework.setId(homeworkId);

        homework.setCreatedAt(new Date());
        homework.setUpdatedAt(new Date());
        // 处理文件存储逻辑，如果有文件的话
        if (file != null && !file.isEmpty()) {
            String filePath = fileStorageComponent.storeFile(file, homework.getTeacherId().toString(), homeworkId.toString());
            homework.setFilePath(filePath);
            homework.setFileName(file.getOriginalFilename());
            homework.setFileSize(file.getSize());
            homework.setFileType(file.getContentType());
        }

        // 插入作业到数据库中
        homeworksMapper.insert(homework);

        // 为每个班级创建一个关联到这个作业的ClassHomeworks实例
        List<ClassHomeworks> classHomeworksList = homeworksBO.getClassId().stream()
                .map(classId -> {
                    ClassHomeworks classHomework = new ClassHomeworks();
                    classHomework.setId(IdWorker.getId()); // 可选，如果你的表自动生成ID，则不需要这一行
                    classHomework.setClassId(classId);
                    classHomework.setHomeworkId(homeworkId);
                    return classHomework;
                })
                .collect(Collectors.toList());


        return classHomeworksMapper.insertBatch(classHomeworksList);
    }

    @Override
    public List<HomeworkInfoVO> getHomeworkInfoVOList(String teacherId) {
        return homeworksMapper.getHomeworkInfoVOList(teacherId);
    }

    @Override
    public List<HomeworkStudentVO> getStudentHomeworkInfoVOList(String stuId) {
        return homeworksMapper.getStudentHomeworkInfoVOList(stuId);
    }


}

