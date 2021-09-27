package com.liuhg.Controller;

import com.liuhg.entity.ChinaData;
import com.liuhg.entity.User;
import com.liuhg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping("queryByPage")
    public HashMap<String, Object> queryByPage(Integer page, Integer rows) {
        HashMap<String, Object> map = userService.queryByPage(page, rows);
        return map;

    }

    @ResponseBody
    @RequestMapping("updateStatus")
    public void updateStatus(User user) {
        userService.updateStatus(user);
    }

    @ResponseBody
    @RequestMapping("edit")
    public String edit(User user, String oper) {
        System.out.println(user);
        String edit = userService.edit(user, oper);
        return edit;
    }

    @ResponseBody
    @RequestMapping("fileUpload")
    public void fileUpload(MultipartFile headImg, String userId) {
        System.out.println(headImg.getOriginalFilename() + "----------");
        System.out.println(userId + "----------");
        userService.fileUpload(headImg, userId);
    }

    @ResponseBody
    @RequestMapping("poiUserData")
    public HashMap<String, Object> poiUserData() {
        HashMap<String, Object> map = new HashMap<>();
        String message = userService.poiGetUserData();
        System.out.println(message);
        map.put("message", message);
        return map;
    }

    @ResponseBody
    @RequestMapping("importXls")
    public HashMap<String, Object> importXls(MultipartFile file, String fileName, Integer begin) {
        HashMap<String, Object> map = new HashMap<>();
        String message = userService.importXls(file, fileName, begin);
        map.put("message", message);
        return map;
    }

    @ResponseBody
    @RequestMapping("queryChinaCity")
    public List<ChinaData> queryCity() {
        List<ChinaData> chinaData = userService.queryCity();
        return chinaData;
    }
}
