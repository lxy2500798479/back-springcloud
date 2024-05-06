package com.lxy.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lxy.mapper.HomeworksMapper;
import com.lxy.result.Result;
import com.lxy.service.HomeworkService;
import com.lxy.userEntity.BO.HomeworkClassBO;

import com.lxy.userEntity.VO.HomeworkInfoVO;
import com.lxy.userEntity.VO.HomeworkStudentVO;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * The type Homework controller.
 */
@RestController
@RequiredArgsConstructor
public class HomeworkController {

    private static final Logger log = LoggerFactory.getLogger(HomeworkController.class);
    private final HomeworkService homeworkService;

    private final HomeworksMapper homeworksMapper;

    /**
     * Create homework result.
     *
     * @param homeworkJson the homework json
     * @param file         the file
     * @return the result
     * @throws JsonProcessingException the json processing exception
     */


    @PostMapping("/createHomework")
    public Result<String> createHomework(@RequestParam("homework") String homeworkJson, @RequestParam(value = "file", required = false) MultipartFile file) throws JsonProcessingException {
        // 先将homeworkJson字符串转换为Homeworks对象
        HomeworkClassBO homeworkClassBO = new ObjectMapper().readValue(homeworkJson, HomeworkClassBO.class);


        boolean isSuccess = homeworkService.createHomework(file, homeworkClassBO) > 0;

        return isSuccess ? Result.success("作业创建成功") : Result.fail("作业创建失败");
    }


    @GetMapping("/teacherPublicHomework/teacherId={teacherId}")
    public Result<List<HomeworkInfoVO>> getTeacherPublicHomework(@PathVariable(value = "teacherId") @NotBlank(message = "teacherId不能为空") String teacherId) {
        return Result.success(homeworkService.getHomeworkInfoVOList(teacherId));

    }

    @GetMapping("/studentHomework/classId={classId}")
    public Result<List<HomeworkStudentVO>> getStudentHomework(@PathVariable(value = "classId") @NotBlank(message = "classId不能为空") String classId) {
        return Result.success(homeworkService.getStudentHomeworkInfoVOList(classId));
    }

    @GetMapping("/getFile")
    public void download(HttpServletResponse response) {
        response.reset();
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition",
                "attachment;filename=" + System.currentTimeMillis() + ".txt");

        // 从文件读到servlet response输出流中
        File file = new File("D://test.txt");
        try (FileInputStream inputStream = new FileInputStream(file);) { // try-with-resources
            byte[] b = new byte[1024];
            int len;
            while ((len = inputStream.read(b)) > 0) {
                response.getOutputStream().write(b, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
