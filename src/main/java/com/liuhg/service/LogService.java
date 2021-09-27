package com.liuhg.service;

import java.util.HashMap;

public interface LogService {
    public HashMap<String, Object> queryByPage(Integer page, Integer rows);
}
