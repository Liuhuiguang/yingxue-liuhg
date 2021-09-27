package com.liuhg.service;

import com.liuhg.dao.AdminDao;
import com.liuhg.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;

    @Override
    public Admin login(String username, String password) {
        Admin admin = adminDao.queryByUsername(username);
        if (admin == null)
            throw new RuntimeException("用户不存在");
        if (admin.getPassword().equals(password) == false)
            throw new RuntimeException("密码错误");
        return admin;
    }
}
