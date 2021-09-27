package com.liuhg.Controller;

import com.liuhg.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.HashMap;

@Controller
@RequestMapping("log")
public class LogController {
    @Autowired
    LogService logService;

    @ResponseBody
    @RequestMapping("queryByPage")
    public HashMap<String, Object> queryByPage(Integer page, Integer rows) {
        HashMap<String, Object> map = logService.queryByPage(page, rows);
        return map;

    }
}
