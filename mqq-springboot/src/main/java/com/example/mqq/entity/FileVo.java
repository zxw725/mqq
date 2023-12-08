package com.example.mqq.entity;

import org.springframework.web.multipart.MultipartFile;

public class FileVo {
    public MultipartFile url;
    public String type;
    private String size;
    private String width;
    private String height;

    public FileVo() {
    }

    public FileVo(MultipartFile url, String type, String size, String width, String height) {
        this.url = url;
        this.type = type;
        this.size = size;
        this.width = width;
        this.height = height;
    }
}
