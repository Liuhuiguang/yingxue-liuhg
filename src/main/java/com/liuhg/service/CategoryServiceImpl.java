package com.liuhg.service;

import com.liuhg.dao.CategoryMapper;
import com.liuhg.entity.Category;
import com.liuhg.entity.CategoryExample;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryMapper categoryDao;

    @Override
    public HashMap<String, Object> queryByPage(Integer page, Integer rows, String parent_id) {
        HashMap<String, Object> map = new HashMap<>();
        //当前页
        map.put("page", page);
        CategoryExample categoryExample = new CategoryExample();
        int records = 0;
        int total = 0;
        List<Category> categories = null;
        //判断父ID是否为空
        if (parent_id == null || parent_id == "") {
            categoryExample.createCriteria().andLevelsEqualTo(1);
            //总条数
            records = categoryDao.selectCountByExample(categoryExample);

            categories = categoryDao.selectByExampleAndRowBounds(categoryExample, new RowBounds((page - 1) * rows, rows));
            //数据
            map.put("rows", categories);
        } else {
            categoryExample.createCriteria().andParentIdEqualTo(parent_id);
            records = categoryDao.selectCountByExample(categoryExample);
            categories = categoryDao.selectByExampleAndRowBounds(categoryExample, new RowBounds((page - 1) * rows, rows));
            map.put("rows", categories);

        }
        //总页数
        total = records % rows == 0 ? records / rows : records / rows + 1;
        map.put("records", records);
        map.put("total", total);
        return map;
    }
}
