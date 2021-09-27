package com.liuhg;

import com.alibaba.druid.support.spring.stat.SpringStatUtils;
import com.liuhg.dao.AdminDao;
import com.liuhg.dao.CategoryMapper;
import com.liuhg.dao.UserMapper;
import com.liuhg.entity.*;
import com.liuhg.service.CategoryService;
import org.apache.ibatis.session.RowBounds;
import org.eclipse.jdt.internal.compiler.IErrorHandlingPolicy;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class YingxueLiuhgApplicationTests {
    @Resource
    AdminDao adminDao;
    @Resource
    UserMapper userDao;
    @Resource
    CategoryService categoryService;

    @Test
    public void test() {
        Admin admin = adminDao.queryByUsername("admin");
        System.out.println(admin);

    }

    @Test
    void contextLoads() {
        HashMap<String, Object> map = categoryService.queryByPage(0, 3, "1");
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println(entry);
        }
    }

}
