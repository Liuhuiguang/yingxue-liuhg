package com.liuhg.service;

import com.liuhg.entity.Video;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

public interface VideoService {
    public String edit(Video video, String oper);

    public void fileUpload(MultipartFile video, String id);

    HashMap<String, Object> queryByPage(Integer page, Integer rows);
}
