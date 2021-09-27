package com.liuhg.dao;

import com.liuhg.entity.City;
import com.liuhg.entity.User;
import com.liuhg.entity.UserExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User> {
    List<City> queryCity(String sex);
}