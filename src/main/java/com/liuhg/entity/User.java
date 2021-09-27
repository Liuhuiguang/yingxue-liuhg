package com.liuhg.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "yx_user")
public class User implements Serializable {
    @Id
    private String id;

    private String photo;
    @Column(name = "head_img")
    private String headImg;

    private String name;

    private String sign;

    private String wechat;

    private String status;
    @JsonFormat
    @Column(name = "regist_time")
    private Date registTime;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", photo='" + photo + '\'' +
                ", headImg='" + headImg + '\'' +
                ", name='" + name + '\'' +
                ", sign='" + sign + '\'' +
                ", wechat='" + wechat + '\'' +
                ", status='" + status + '\'' +
                ", registTime=" + registTime +
                '}';
    }

    public User(String id, String photo, String headImg, String name, String sign, String wechat, String status, Date registTime) {
        this.id = id;
        this.photo = photo;
        this.headImg = headImg;
        this.name = name;
        this.sign = sign;
        this.wechat = wechat;
        this.status = status;
        this.registTime = registTime;
    }

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo == null ? null : photo.trim();
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg == null ? null : headImg.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign == null ? null : sign.trim();
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat == null ? null : wechat.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getRegistTime() {
        return registTime;
    }

    public void setRegistTime(Date registTime) {
        this.registTime = registTime;
    }
}