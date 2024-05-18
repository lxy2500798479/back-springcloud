package com.lxy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kangaroohy.minio.entity.MultiPartUploadInfo;
import com.lxy.result.Result;
import io.minio.errors.MinioException;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public interface FileService {


    /**
     * 获取分块上传信息
     *
     * @param bucketName 桶名
     * @param fileName 文件名，可以为空，默认值为 "defaultFileName"
     * @param partSize 分块大小，以字节为单位， 1048576 (1MB)
     * @param contentType 文件内容类型，可以为空
     * @return MultiPartUploadInfo 对象
     */
    MultiPartUploadInfo getMultiPartUploadInfo (
            @RequestParam(value = "bucketName",defaultValue = "test") String bucketName,
//            @RequestParam(value = "path") String path,
            @RequestParam(value = "fileName") String fileName,
            @RequestParam(value = "partSize") int partSize,
            @RequestParam(value = "contentType") String contentType
    ) throws MinioException;

    String StartMergePart(@RequestParam(value = "bucketName",defaultValue = "test") String bucketName, String fileName, String uploadId) throws MinioException;

    void  downloadFile(@RequestParam String homeworkId,
                                @RequestHeader HttpHeaders headers, HttpServletResponse response) throws MinioException, IOException;

//    Object uploadFile(@RequestParam(value = "bucketName",defaultValue = "test") String bucketName, String fileName, String uploadId) throws MinioException;



}
