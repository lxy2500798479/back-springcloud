package com.lxy.service.Impl;

import com.kangaroohy.minio.entity.MultiPartUploadInfo;
import com.kangaroohy.minio.service.MinioService;
import com.lxy.mapper.HomeworksMapper;

import com.lxy.service.FileService;
import com.lxy.userEntity.Homeworks;
import io.minio.errors.MinioException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRange;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {


    private final MinioService minioService;
    private final HomeworksMapper homeworksMapper;


    @Override
    public MultiPartUploadInfo getMultiPartUploadInfo(String bucketName, String fileName, int partSize, String contentType) throws MinioException {
        return minioService.initMultiPartUploadId(bucketName, fileName, partSize, contentType);
    }

    @Override
    public String StartMergePart(String bucketName, String fileName, String uploadId) throws MinioException {

        return minioService.mergeMultiPartUpload(bucketName, fileName, uploadId);
    }

    @Override
    public void downloadFile(String homeworkId, HttpHeaders headers, HttpServletResponse response) throws MinioException, IOException {
        Homeworks homeworks = homeworksMapper.selectById(homeworkId);
        String fileUrl = homeworks.getDownloadUrl();

        String bucketName = extractBucketName(fileUrl);
        System.out.println("bucketName:" + bucketName);
        String objectName = extractObjectName(fileUrl);
        System.out.println("objectName:" + objectName);
        long objectSize = homeworks.getFileSize();


        if (headers.getRange() != null && !headers.getRange().isEmpty()) {
            List<HttpRange> ranges = headers.getRange();
            HttpRange range = ranges.get(0);
            long start = range.getRangeStart(objectSize);
            long end = range.getRangeEnd(objectSize);


            InputStream object = minioService.getObject(bucketName, objectName, end - start + 1, start);

            byte[] data = object.readAllBytes();

            response.setHeader(HttpHeaders.CONTENT_RANGE, "bytes " + start + "-" + end + "/" + objectSize);
            response.setHeader(HttpHeaders.ACCEPT_RANGES, "bytes");
            response.setContentLength(data.length);
            response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);

            OutputStream out = response.getOutputStream();
            out.write(data);
            out.flush();
        } else {
            // 获取整个对象
            InputStream object = minioService.getObject(bucketName, objectName);

            byte[] data = object.readAllBytes();

            response.setHeader(HttpHeaders.ACCEPT_RANGES, "bytes");
            response.setContentLength(data.length);

            OutputStream out = response.getOutputStream();
            out.write(data);
            out.flush();

        }


    }

    private String extractBucketName(String fileUrl) {
        // 解析URL，提取桶名 (根据你URL的格式做相应解析)
        String[] parts = fileUrl.split("/");
        return parts[3]; // 假设第4部分是桶名
    }

    private String extractObjectName(String fileUrl) {
        // 解析URL，提取对象名 (根据你URL的格式做相应解析)
        String[] parts = fileUrl.split("/");
//        return parts[4] + "/" + parts[5]; // 假设第5和第6部分组成对象名
        return parts[4];
    }

//    private Byte[] toByteObjectArray(byte[] bytes) {
//        Byte[] byteObjects = new Byte[bytes.length];
//        Arrays.setAll(byteObjects, n -> bytes[n]);
//        return byteObjects;
//    }


}
