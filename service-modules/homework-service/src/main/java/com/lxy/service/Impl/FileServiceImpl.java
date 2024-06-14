package com.lxy.service.Impl;

import com.kangaroohy.minio.entity.MultiPartUploadInfo;
import com.kangaroohy.minio.service.MinioService;
import com.lxy.mapper.HomeworksMapper;
import com.lxy.service.FileService;
import com.lxy.userEntity.Homeworks;
import io.minio.errors.MinioException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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
        System.out.println("fileUrl:" + fileUrl);

        String bucketName = extractBucketName(fileUrl);
        String objectName = extractObjectName(fileUrl);
        long objectSize = homeworks.getFileSize();

        System.out.println("bucketName:" + bucketName);
        System.out.println("objectName:" + objectName);

        List<HttpRange> ranges = headers.getRange();
        if (ranges != null && !ranges.isEmpty()) {
            HttpRange range = ranges.get(0);
            long start = range.getRangeStart(objectSize);
            long end = range.getRangeEnd(objectSize);

            InputStream object = minioService.getObject(bucketName, objectName, end - start + 1, start);
            response.setHeader(HttpHeaders.CONTENT_RANGE, "bytes " + start + "-" + end + "/" + objectSize);
            response.setHeader(HttpHeaders.ACCEPT_RANGES, "bytes");
            response.setStatus(HttpStatus.PARTIAL_CONTENT.value());
            streamToResponse(object, response);
        } else {
            InputStream object = minioService.getObject(bucketName, objectName);
            response.setHeader(HttpHeaders.ACCEPT_RANGES, "bytes");
            response.setContentLengthLong(objectSize);
            streamToResponse(object, response);
        }
    }

    private void streamToResponse(InputStream inputStream, HttpServletResponse response) throws IOException {
        OutputStream outputStream = response.getOutputStream();
        byte[] buffer = new byte[8192];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        outputStream.flush();
        inputStream.close();
        outputStream.close();
    }

    private String extractBucketName(String fileUrl) {
        String[] parts = fileUrl.split("/");
        return parts[3];
    }

    //    private String extractObjectName(String fileUrl) {
//        String[] parts = fileUrl.split("/");
//        Arrays.stream(parts).forEach(System.out::println);
//        return parts[4];
//    }
    private String extractObjectName(String fileUrl) {
        String[] parts = fileUrl.split("/");
        if (parts.length > 4) {
            // 拼接数组中的剩余部分作为对象名
            StringBuilder objectName = new StringBuilder(parts[4]);
            for (int i = 5; i < parts.length; i++) {
                objectName.append("/").append(parts[i]);
            }
            try {
                return URLDecoder.decode(objectName.toString().split("\\?")[0], StandardCharsets.UTF_8.name()); // 移除查询参数部分并解码
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }
}
