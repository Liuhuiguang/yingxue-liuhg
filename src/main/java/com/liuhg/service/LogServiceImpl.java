package com.liuhg.service;


import com.liuhg.dao.LogMapper;
import com.liuhg.entity.Log;
import com.liuhg.entity.LogExample;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class LogServiceImpl implements LogService {
    @Autowired
    LogMapper logDao;

    @Override
    public HashMap<String, Object> queryByPage(Integer page, Integer rows) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("page", page);
        LogExample logExample = new LogExample();
        int records = logDao.selectCountByExample(logExample);
        map.put("records", records);
        int total = records % rows == 0 ? records / rows : records / rows + 1;
        map.put("total", total);
        List<Log> logs = logDao.selectByExampleAndRowBounds(logExample, new RowBounds((page - 1) * rows, rows));
        map.put("rows", logs);
        return map;
    }
}
