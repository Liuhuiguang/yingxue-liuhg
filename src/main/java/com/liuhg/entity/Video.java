package com.liuhg.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "yx_video")
public class Video implements Serializable {
    @Id
    private String id;

    private String title;

    private String description;
    @Column(name = "video_path")
    private String videoPath;
    @Column(name = "cover_path")
    private String coverPath;
    @Column(name = "upload_time")
    private Date uploadTime;
    @Column(name = "like_count")
    private Integer likeCount;
    @Column(name = "play_count")
    private Integer playCount;
    @Column(name = "cate_id")
    private String cateId;
    @Column(name = "group_id")
    private String groupId;
    @Column(name = "user_id")
    private String userId;

    @Override
    public String toString() {
        return "Video{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", videoPath='" + videoPath + '\'' +
                ", coverPath='" + coverPath + '\'' +
                ", uploadTime=" + uploadTime +
                ", likeCount=" + likeCount +
                ", playCount=" + playCount +
                ", cateId='" + cateId + '\'' +
                ", groupId='" + groupId + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }

    public Video(String id, String title, String description, String videoPath, String coverPath, Date uploadTime, Integer likeCount, Integer playCount, String cateId, String groupId, String userId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.videoPath = videoPath;
        this.coverPath = coverPath;
        this.uploadTime = uploadTime;
        this.likeCount = likeCount;
        this.playCount = playCount;
        this.cateId = cateId;
        this.groupId = groupId;
        this.userId = userId;
    }

    public Video() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public String getCoverPath() {
        return coverPath;
    }

    public void setCoverPath(String coverPath) {
        this.coverPath = coverPath;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getPlayCount() {
        return playCount;
    }

    public void setPlayCount(Integer playCount) {
        this.playCount = playCount;
    }

    public String getCateId() {
        return cateId;
    }

    public void setCateId(String cateId) {
        this.cateId = cateId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}