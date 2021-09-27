package com.liuhg.Controller;

import com.liuhg.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("exit")
public class ExitController {
    @Autowired
    HttpSession session;

    @RequestMapping("exit")
    public String exit() {
        session.removeAttribute("Admin");
        return "login/login";
    }
}
