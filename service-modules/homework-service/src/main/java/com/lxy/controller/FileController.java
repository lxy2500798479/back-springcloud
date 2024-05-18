package com.lxy.controller;


import com.kangaroohy.minio.entity.MultiPartUploadInfo;


import com.kangaroohy.minio.service.MinioService;
import com.lxy.result.Result;
import com.lxy.service.FileService;
import com.lxy.service.HomeworkService;
import io.minio.errors.MinioException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class FileController {

    private final HomeworkService homeworkService;

    private final MinioService minioService;
    private final FileService fileService;




    @GetMapping("/checkExistByMd5")
    public Result<String> checkExistByMd5(@RequestParam String md5) {
       return Result.success(homeworkService.isExistFile(md5));
    }

    @GetMapping("/partUrl")
    public Result<MultiPartUploadInfo> partUrl( @RequestParam String fileName,
                                               @RequestParam int partSize, @RequestParam String contentType) throws MinioException {
        return Result.success(fileService.getMultiPartUploadInfo("test",fileName, partSize, contentType));
    }

    @GetMapping("/mergePart")
    public Result<String> mergePart(@RequestParam String fileName, @RequestParam String uploadId) throws MinioException {
        return Result.success(fileService.StartMergePart("test", fileName, uploadId));
    }


    @GetMapping("/partInfo")
    public Result<List<Integer>> parInfo(@RequestParam String fileName, @RequestParam String uploadId) throws MinioException {
        List<Integer> test = minioService.listUploadMultiPart("test", fileName, uploadId);
        return Result.success(test);
    }

    @GetMapping("/download")
    public void downloadFile(@RequestParam String homeworkId,
                             @RequestHeader HttpHeaders headers, HttpServletResponse response) throws MinioException, IOException {
        fileService.downloadFile(homeworkId, headers, response);
    }






}
