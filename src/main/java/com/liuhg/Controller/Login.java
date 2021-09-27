package com.liuhg.Controller;

import com.liuhg.entity.Admin;
import com.liuhg.service.AdminService;
import com.liuhg.util.CreateValidateCode;
import com.liuhg.util.ImageCodeUtil;
import com.sun.org.apache.bcel.internal.classfile.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("login")
public class Login {
    @Autowired
    private AdminService adminService;

    @RequestMapping("login")
    @ResponseBody
    public Map<String, Object> login(Admin admin, String Code, HttpServletResponse response, HttpServletRequest request) {
        HttpSession session = request.getSession();
        HashMap<String, Object> map = new HashMap<>();
        try {
            if (Code.equals(session.getAttribute("Code")) == false)
                throw new RuntimeException("验证码错误");
            Admin login = adminService.login(admin.getUsername(), admin.getPassword());
            session.setAttribute("Admin", login);
            map.put("message", "登陆成功");
            map.put("status", 100);
            System.out.println("登陆成功");
        } catch (Exception e) {
            String message = e.getMessage();
            map.put("message", message);
            map.put("status", 104);
            System.out.println("登陆失败");
        }
        return map;
    }

    @RequestMapping("getCode")
    public void getCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获得随机字符
        HttpSession session = request.getSession();
        CreateValidateCode vCode = new CreateValidateCode(100, 30, 5, 10);
        session.setAttribute("Code", vCode.getCode());
        System.out.println(vCode.getCode());
        vCode.write(response.getOutputStream());


    }
}
