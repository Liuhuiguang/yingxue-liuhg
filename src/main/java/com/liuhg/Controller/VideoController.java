package com.liuhg.Controller;

import com.liuhg.entity.Video;
import com.liuhg.service.VideoService;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("video")
public class VideoController {
    @Autowired
    VideoService videoService;

    @ResponseBody
    @RequestMapping("edit")
    public String edit(Video video, String oper) {
        System.out.println(video);
        System.out.println(oper);
        String edit = videoService.edit(video, oper);
        return edit;

    }

    @ResponseBody
    @RequestMapping("fileUpload")
    public void fileUpload(String videoId, MultipartFile videoPath) {
        System.out.println(videoId);
        videoService.fileUpload(videoPath, videoId);
    }

    @ResponseBody
    @RequestMapping("queryByPage")
    public HashMap<String, Object> queryByPage(Integer page, Integer rows) {
        HashMap<String, Object> map = videoService.queryByPage(page, rows);
        return map;
    }
}
