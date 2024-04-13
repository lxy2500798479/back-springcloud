package com.lxy.utils;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
@Slf4j
public class FileStorageComponent {

    private final Path rootLocation;
    private final Path teachersDirectory;

    public FileStorageComponent() {
        this.rootLocation = Paths.get("uploads");
        // 在根目录下预设teachers目录的路径
        this.teachersDirectory = this.rootLocation.resolve("teachers");
    }


    @PostConstruct
    public void init() {

        try {
            // 创建根目录
            if (!rootLocation.toFile().exists()) {
                rootLocation.toFile().mkdir();
            }

            // 创建教师目录
            if (!teachersDirectory.toFile().exists()) {
                teachersDirectory.toFile().mkdir();
            }

        } catch (Exception e) {
            throw new RuntimeException("创建文件夹uploads失败", e);
        }

    }

    public String storeFile(MultipartFile file, String teacherId, String homeworkId) {

        try {
            Path teacherDirectory = this.teachersDirectory.resolve(teacherId);
            Path homeworkDirectory = teacherDirectory.resolve(homeworkId);

            FileUtil.mkdir(homeworkDirectory.toFile());

            String originalFileName = file.getOriginalFilename();
            String safeFileName = originalFileName != null ? FileUtil.getName(originalFileName) : "unknown";
            Path targetLocation = homeworkDirectory.resolve(safeFileName);

            FileUtil.writeBytes(file.getBytes(), targetLocation.toFile());

            return targetLocation.toString();
        } catch (IOException ex) {
            log.error("无法存储文件 {}。请重试！", file.getOriginalFilename(), ex);
            throw new RuntimeException("无法存储文件 " + file.getOriginalFilename() + "。请重试！", ex);
        }


    }


}
