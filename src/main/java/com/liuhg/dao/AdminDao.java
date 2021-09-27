package com.liuhg.dao;

import com.liuhg.entity.Admin;

public interface AdminDao {
    Admin queryByUsername(String username);
}
