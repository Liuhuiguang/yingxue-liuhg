package com.liuhg.service;

import com.liuhg.dao.VideoMapper;
import com.liuhg.po.VideoPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class AppInterfaveServiceImpl implements AppInterfaceService {
    @Autowired
    VideoMapper videoMapper;

    @Override
    public HashMap<String, Object> queryByReleaseTime() {
        HashMap<String, Object> map = new HashMap<>();
        List<VideoPo> videoPos = videoMapper.queryByReleaseTime();
        map.put("data", videoPos);
        return map;
    }
}
