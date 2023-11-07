package com.example.mqq.controller;

import com.example.mqq.token.PassToken;
import com.example.mqq.utils.FileUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@RestController
public class FileController {
    /**
     *
     *调用图片上传工具类
     * @param file
     * @return java.lang.String
     * @author wrx
     * @date 2023/5/17 0017 16:00
     */
    @PassToken
    @PostMapping("/uploadImage")
    public String uploadImage(@RequestParam("file") MultipartFile file) throws Exception {
        String fileName = String.valueOf(System.currentTimeMillis())+".jpg";
        String filePath =  System.getProperty("user.dir")+"/src/main/resources/static/images/"+fileName; // 保存路径
        File dest = new File(filePath);
        file.transferTo(dest); // 保存文件
        return "/static/images/" + fileName; // 返回图片路径
    }
}
