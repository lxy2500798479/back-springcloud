package com.lxy.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lxy.result.Result;
import com.lxy.service.HomeworkService;
import com.lxy.userEntity.BO.HomeworkClassBO;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * The type Homework controller.
 */
@RestController
@RequiredArgsConstructor
public class HomeworkController {

    private final HomeworkService homeworkService;

    /**
     * Create homework result.
     *
     * @param homeworkJson the homework json
     * @param file         the file
     * @return the result
     * @throws JsonProcessingException the json processing exception
     */



    @PostMapping("/createHomework")
    public Result<String> createHomework(@RequestParam("homework") String homeworkJson,@RequestParam(value = "file",required = false) MultipartFile file) throws JsonProcessingException {
        System.out.println(file.getOriginalFilename());
        // 先将homeworkJson字符串转换为Homeworks对象
        HomeworkClassBO homeworkClassBO = new ObjectMapper().readValue(homeworkJson, HomeworkClassBO.class);
        System.out.println(homeworkClassBO.getHomeworks().toString());


        boolean isSuccess = homeworkService.createHomework(file, homeworkClassBO) > 0;



        return isSuccess ? Result.success("作业创建成功") : Result.fail("作业创建失败");
    }

}
