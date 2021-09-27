package com.liuhg.app;

import com.liuhg.service.AppInterfaceService;
import com.liuhg.service.VideoService;
import com.liuhg.util.SeondPhoneCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RequestMapping("app")
@RestController//该类所有的返回值都是json串
public class AppInterface {
    @Autowired
    AppInterfaceService appInterfaceService;
    @Autowired
    VideoService videoService;

    @RequestMapping("getPhoneCode")
    public HashMap<String, Object> getPhoneCode(String phone) {
        HashMap<String, Object> map = new HashMap<>();
        String message = null;
        String random = SeondPhoneCodeUtil.getRandom(6).toString();
        try {
            message = SeondPhoneCodeUtil.sendPhoneCode(phone, random);

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (message.equals("发送成功")) {
            map.put("data", phone);
            map.put("message", message);
            map.put("status", 101);
        }
        if (message.equals("发送失败")) {
            map.put("data", null);
            map.put("message", message);
            map.put("status", 100);
        }
        return map;
    }

    @GetMapping("queryByReleaseTime")
    public HashMap<String, Object> queryByReleaseTime() {
        HashMap<String, Object> map = null;
        try {
            map = appInterfaceService.queryByReleaseTime();
            map.put("message", "查询成功");
            map.put("status", 100);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("data", null);
            map.put("message", "查询失败");
            map.put("status", 100);

        }
        return map;
    }

}
