package com.liuhg.service;

import java.util.HashMap;

public interface CategoryService {
    public HashMap<String, Object> queryByPage(Integer page, Integer rows, String parent_id);
}
