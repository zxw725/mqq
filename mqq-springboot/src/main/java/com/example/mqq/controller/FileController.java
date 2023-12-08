package com.example.mqq.controller;

import com.example.mqq.entity.FileVo;
import com.example.mqq.token.PassToken;
import com.example.mqq.utils.FileUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class FileController {
    /**
     * 调用图片上传工具类
     *
     * @param file
     * @return java.lang.String
     * @author wrx
     * @date 2023/5/17 0017 16:00
     */
    @PassToken
    @PostMapping("/uploadImage")
    public String uploadImage(@RequestParam("file") MultipartFile file) throws Exception {
        System.out.println(111);
        System.out.println(file);
        String fileName = String.valueOf(System.currentTimeMillis())+".jpg";
        String filePath = System.getProperty("user.dir") + "/src/main/resources/static/images/" + fileName; // 保存路径
        File dest = new File(filePath);
        file.transferTo(dest); // 保存文件
        return "/static/images/" + fileName; // 返回图片路径
    }

    @PassToken
    @PostMapping("/uploadVideo")
    public String uploadVideo(@RequestParam("file") MultipartFile file) throws Exception {
        String fileName = String.valueOf(System.currentTimeMillis())+".mp4";
        String filePath = System.getProperty("user.dir") + "/src/main/resources/static/images/" + fileName; // 保存路径
        File dest = new File(filePath);
        file.transferTo(dest); // 保存文件
        return "/static/images/" + fileName; // 返回图片路径
    }

    @PassToken
    @PostMapping("/upload/files")
    public ResponseEntity<List<String>> handleFileUpload(@RequestBody ArrayList<FileVo> files) {
        List<String> fileNames = new ArrayList<>();
        System.out.println(11233);
        System.out.println(files);
        for (FileVo file : files) {
            String fileName = null;
            try {
                fileName = storeFile(file);
            } catch (Exception e) {
                e.printStackTrace();
            }
            fileNames.add(fileName);
        }

        return ResponseEntity.ok().body(fileNames);
    }

    private String storeFile(FileVo file) throws Exception {
        String fileName = String.valueOf(System.currentTimeMillis());
        String filePath = "";
        if (file.type == "image") {
            filePath = System.getProperty("user.dir") + "/src/main/resources/static/images/" + fileName + ".jpg"; // 保存路径

        } else if (file.type == "video") {
            filePath = System.getProperty("user.dir") + "/src/main/resources/static/images/" + fileName + ".mp4"; // 保存路径

        }
        File dest = new File(filePath);
        file.url.transferTo(dest); // 保存文件
        return "/static/images/" + fileName; // 返回图片路径
    }
}
