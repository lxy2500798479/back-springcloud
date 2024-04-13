package com.lxy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lxy.userEntity.BO.HomeworkClassBO;
import com.lxy.userEntity.Homeworks;
import org.springframework.web.multipart.MultipartFile;

public interface HomeworkService extends IService<Homeworks> {

    int createHomework(MultipartFile file, HomeworkClassBO homeworkClassBO);

}
