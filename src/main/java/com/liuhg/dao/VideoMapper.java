package com.liuhg.dao;

import com.liuhg.entity.Video;
import com.liuhg.entity.VideoExample;

import java.util.HashMap;
import java.util.List;

import com.liuhg.po.VideoPo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface VideoMapper extends Mapper<Video> {
    public List<VideoPo> queryByReleaseTime();

}