package com.lxy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lxy.userEntity.BO.HomeworkClassBO;
import com.lxy.userEntity.Homeworks;
import com.lxy.userEntity.VO.HomeworkInfoVO;
import com.lxy.userEntity.VO.HomeworkStudentVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface HomeworkService extends IService<Homeworks> {

    int createHomework( HomeworkClassBO homeworkClassBO);

    List<HomeworkInfoVO> getHomeworkInfoVOList(String teacherId);

    List<HomeworkStudentVO> getStudentHomeworkInfoVOList(String stuId);

    String isExistFile(String fileName);


}
