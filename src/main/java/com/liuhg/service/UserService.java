package com.liuhg.service;

import com.liuhg.entity.ChinaData;
import com.liuhg.entity.City;
import com.liuhg.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

public interface UserService {
    String poiGetUserData();

    HashMap<String, Object> queryByPage(@Param("page") Integer page, @Param("rows") Integer rows);

    void updateStatus(User user);

    String edit(User user, String oper);

    void fileUpload(MultipartFile file, String id);

    String importXls(MultipartFile file, String fileName, Integer begin);

    List<ChinaData> queryCity();
}
