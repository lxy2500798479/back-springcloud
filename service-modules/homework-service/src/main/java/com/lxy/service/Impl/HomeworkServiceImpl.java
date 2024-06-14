package com.lxy.service.Impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxy.mapper.ClassHomeworksMapper;
import com.lxy.mapper.HomeworkSubmissionsMapper;
import com.lxy.mapper.HomeworksMapper;

import com.lxy.service.HomeworkService;
import com.lxy.userEntity.BO.HomeworkClassBO;
import com.lxy.userEntity.ClassHomeworks;
import com.lxy.userEntity.HomeworkSubmissions;
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
    private final HomeworkSubmissionsMapper homeworkSubmissionsMapper;


    @Override
    @Transactional
    public int createHomework(HomeworkClassBO homeworksBO) {
        Homeworks homework = homeworksBO.getHomeworks();
        // 为作业生成唯一的ID
        Long homeworkId = IdWorker.getId();
        homework.setId(homeworkId);

        homework.setCreatedAt(new Date());
        homework.setUpdatedAt(new Date());
        // 处理文件存储逻辑，如果有文件的话
//        if (file != null && !file.isEmpty()) {
//            String filePath = fileStorageComponent.storeFile(file, homework.getTeacherId().toString(), homeworkId.toString());
//            homework.setFilePath(filePath);
//            homework.setFileName(file.getOriginalFilename());
//            homework.setFileSize(file.getSize());
//            homework.setFileType(file.getContentType());
//        }


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

    @Override
    public String isExistFile(String fileMd5, String Utype) {
        // 根据用户类型选择查询的表
        if ("teacher".equalsIgnoreCase(Utype)) {
            LambdaQueryWrapper<Homeworks> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Homeworks::getFileMd5, fileMd5);

            // 查询匹配的所有记录
            List<Homeworks> homeworkList = homeworksMapper.selectList(queryWrapper);

            if (!homeworkList.isEmpty()) {
                // 如果存在匹配的记录，则返回第一条记录的下载URL
                return homeworkList.get(0).getDownloadUrl();
            } else {
                // 如果记录不存在，则返回空字符串或者抛出异常，根据具体需求来决定
                return null;
            }
        } else if ("student".equalsIgnoreCase(Utype)) {
            LambdaQueryWrapper<HomeworkSubmissions> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(HomeworkSubmissions::getFileMd5, fileMd5);


            // 查询匹配的所有记录
            List<HomeworkSubmissions> homeworkSubmissionList = homeworkSubmissionsMapper.selectList(queryWrapper);

            if (!homeworkSubmissionList.isEmpty()) {
                // 如果存在匹配的记录，则返回第一条记录的下载URL
                return homeworkSubmissionList.get(0).getSubmissionPath();
            } else {
                // 如果记录不存在，则返回空字符串或者抛出异常，根据具体需求来决定
                return null;
            }
        } else {
            // 如果 Utype 既不是 teacher 也不是 student，返回空字符串或者抛出异常，根据具体需求来决定
            return null;
        }
    }



    /*
     * 更新或插入作业提交信息。
     * 如果存在相同的作业ID和学生ID，则更新作业提交的相关信息；否则，插入新的作业提交记录。
     *
     * @param homeworkSubmissions 作业提交对象，包含需要更新或插入的所有信息。
     * @return 返回更新或插入的记录数。
     */
    @Override
    public int updateHomeworkSubmission(HomeworkSubmissions homeworkSubmissions) {
        // 创建查询条件，根据作业ID和学生ID查找是否存在相同的作业提交记录。
        LambdaQueryWrapper<HomeworkSubmissions> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(HomeworkSubmissions::getHomeworkId, homeworkSubmissions.getHomeworkId())
                .eq(HomeworkSubmissions::getStudentId, homeworkSubmissions.getStudentId());

        // 根据查询条件查找是否存在对应的作业提交记录。
        HomeworkSubmissions existingHomeworkSubmissions = homeworkSubmissionsMapper.selectOne(queryWrapper);
        if (existingHomeworkSubmissions != null) {
            // 如果存在相同的作业提交记录，则创建更新条件，更新所有相关字段。
            // 更新操作
            LambdaUpdateWrapper<HomeworkSubmissions> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(HomeworkSubmissions::getId, existingHomeworkSubmissions.getId()) // 使用唯一ID进行更新
                    .set(HomeworkSubmissions::getStatus, homeworkSubmissions.getStatus())
                    .set(HomeworkSubmissions::getFeedback, homeworkSubmissions.getFeedback())
                    .set(HomeworkSubmissions::getSubmittedAt, new Date())
                    .set(HomeworkSubmissions::getSubmissionPath, homeworkSubmissions.getSubmissionPath())
                    .set(HomeworkSubmissions::getFileName, homeworkSubmissions.getFileName())
                    .set(HomeworkSubmissions::getFileSize, homeworkSubmissions.getFileSize())
                    .set(HomeworkSubmissions::getFileType, homeworkSubmissions.getFileType())
                    .set(HomeworkSubmissions::getFileMd5, homeworkSubmissions.getFileMd5());

            // 执行更新操作并返回更新的记录数。
            return homeworkSubmissionsMapper.update(null, updateWrapper);
        } else {
            // 如果不存在相同的作业提交记录，则插入新的作业提交记录。
            return homeworkSubmissionsMapper.insert(homeworkSubmissions);
        }
    }




}

