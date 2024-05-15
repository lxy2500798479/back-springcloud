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
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;

/**
 * The type Homework controller.
 */
@RestController
@RequiredArgsConstructor
public class HomeworkController {

    private static final Logger log = LoggerFactory.getLogger(HomeworkController.class);
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
    public Result<String> createHomework(@RequestParam("homework") String homeworkJson) throws JsonProcessingException {
        // 先将homeworkJson字符串转换为Homeworks对象
        HomeworkClassBO homeworkClassBO = new ObjectMapper().readValue(homeworkJson, HomeworkClassBO.class);

        boolean isSuccess = homeworkService.createHomework(homeworkClassBO) > 0;

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


    private volatile boolean stopDownload = false; // Flag to indicate whether to stop download

    @GetMapping("/getFile")
    public ResponseEntity<InputStreamResource> downloadFile(@RequestHeader(value = "Range", required = false) String rangeHeader) throws IOException {
        File file = new File("C:\\Users\\Administrator\\Desktop\\SourceFiles\\Big\\homework\\upfile-back\\uploads\\teachers\\1778089553653182466\\1788578183500087297\\E.rar");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", URLEncoder.encode(file.getName(), "UTF-8"));

        InputStream inputStream;
        long fileSize = file.length();

        if (rangeHeader == null) {
            inputStream = new FileInputStream(file);
            headers.setContentLength(fileSize);
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(new InputStreamResource(inputStream));
        }

        String[] ranges = rangeHeader.substring("bytes=".length()).split("-");
        long start = Long.parseLong(ranges[0]);
        long end = fileSize - 1;
        if (ranges.length > 1) {
            end = Long.parseLong(ranges[1]);
        }

        headers.set("Content-Range", "bytes " + start + "-" + end + "/" + fileSize);

        inputStream = new FileInputStream(file);
        inputStream.skip(start);

        long contentLength = end - start + 1;
        headers.setContentLength(contentLength);

        // Check if stop flag is set, if so, return 206 with no content
        if (stopDownload) {
            stopDownload = false; // Reset flag for subsequent downloads
            return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                    .headers(headers)
                    .body(null);
        }

        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                .headers(headers)
                .body(new InputStreamResource(inputStream));
    }

    @PostMapping("/stopDownload")
    public Result<String> stopDownload() {
        stopDownload = true;
        return Result.success("Download stopped");
    }








}
