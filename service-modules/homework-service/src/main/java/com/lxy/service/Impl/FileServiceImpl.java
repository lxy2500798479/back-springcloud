package com.lxy.service.Impl;

import com.kangaroohy.minio.entity.MultiPartUploadInfo;
import com.kangaroohy.minio.service.MinioService;
import com.lxy.service.FileService;
import io.minio.errors.MinioException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {



    private final MinioService minioService;




    @Override
    public MultiPartUploadInfo getMultiPartUploadInfo(String bucketName, String fileName, int partSize, String contentType) throws MinioException {
        return minioService.initMultiPartUploadId(bucketName, fileName,partSize, contentType);
    }

    @Override
    public String StartMergePart(String bucketName, String fileName, String uploadId) throws MinioException {

        return minioService.mergeMultiPartUpload(bucketName, fileName, uploadId);
    }


    @Override
    public Object uploadFile(String bucketName, String fileName, String uploadId) throws MinioException {
        return minioService.getObjectUrl(bucketName, fileName);
    }
}
