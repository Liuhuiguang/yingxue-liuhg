package com.liuhg.dao;

import com.liuhg.entity.Category;
import com.liuhg.entity.CategoryExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface CategoryMapper extends Mapper<Category> {
    public List<Category> queryCategoryAll();
}