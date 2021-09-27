package com.liuhg.service;

import com.liuhg.annotation.AddLog;
import com.liuhg.dao.VideoMapper;
import com.liuhg.entity.Video;
import com.liuhg.entity.VideoExample;
import com.liuhg.util.AliyunOSSUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.management.ObjectName;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class VideoServiceImpl implements VideoService {
    @Autowired
    VideoMapper videoDao;
    @Autowired
    HttpServletRequest request;

    @AddLog("操作视频信息")
    @Override
    public String edit(Video video, String oper) {
        if (oper.equals("add")) {
            video.setId(UUID.randomUUID().toString());
            video.setUploadTime(new Date());
            video.setLikeCount(0);
            video.setPlayCount(0);
            System.out.println(video);
            videoDao.insert(video);
        }
        if (oper.equals("del")) {
            System.out.println(oper);
            String bucketName = "yingxue-liu";
            Video video2 = videoDao.selectOne(video);
            System.out.println(video2);
            String cover = video2.getCoverPath();
            String videos = video2.getVideoPath();

            String cover2 = cover.replaceFirst("http://yingxue-liu.oss-cn-beijing.aliyuncs.com/", "");
            String video3 = videos.replaceFirst("http://yingxue-liu.oss-cn-beijing.aliyuncs.com/", "");
            System.out.println(cover2);
            AliyunOSSUtil.deleteFile(bucketName, cover2);
            AliyunOSSUtil.deleteFile(bucketName, video3);

            videoDao.delete(video);

        }
        return video.getId();
    }

    @AddLog(value = "上传视频")
    @Override
    public void fileUpload(MultipartFile video, String id) {
        String backeName = "yingxue-liu";
        String filename = video.getOriginalFilename();
        String newFileName = new Date().getTime() + "-" + filename;
        String objectName = "video/" + newFileName;
        String strings = AliyunOSSUtil.strings(newFileName);
        String name = strings + ".jpg";
        String coverName = "img/" + name;
        try {

            AliyunOSSUtil.localUpload(backeName, objectName, video);
            AliyunOSSUtil.getVideoImg(backeName, objectName, coverName);
        } catch (IOException e) {
            e.printStackTrace();
        }


       /* try {
            String realPath = request.getSession().getServletContext().getRealPath("/upload/cover");
            File file = new File(realPath);
            if(!file.exists()){
                file.mkdirs();
            }
            String coverPath=realPath+"/"+name;
            AliyunOSSUtil.fetchFrame("http://yingxue-liu.oss-cn-beijing.aliyuncs.com/"+objectName,coverPath);
            AliyunOSSUtil.uplocalfiles(backeName,coverPath,coverName);
            File file2 = new File(realPath, name);
            if(file2.exists()&&file2.isFile()){
                file2.delete();
            }

            } catch (Exception e) {
            e.printStackTrace();
        }*/
        Video video1 = new Video();
        video1.setId(id);
        video1.setVideoPath("http://yingxue-liu.oss-cn-beijing.aliyuncs.com/" + objectName);
        video1.setCoverPath("http://yingxue-liu.oss-cn-beijing.aliyuncs.com/" + coverName);
        videoDao.updateByPrimaryKeySelective(video1);

    }

    @Override
    public HashMap<String, Object> queryByPage(Integer page, Integer rows) {
        HashMap<String, Object> map = new HashMap<>();
        VideoExample videoExample = new VideoExample();
        //当前页
        map.put("page", page);
        int count = videoDao.selectCountByExample(videoExample);
        //总条数
        map.put("records", count);
        int total = count % rows == 0 ? count / rows : count / rows + 1;
        //总页数
        map.put("total", total);
        List<Video> videos = videoDao.selectByExampleAndRowBounds(videoExample, new RowBounds((page - 1)
                * rows, rows));
        //展示数据
        map.put("rows", videos);
        return map;
    }


}
