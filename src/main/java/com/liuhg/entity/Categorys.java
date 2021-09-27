package com.liuhg.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "yx_category")
public class Categorys {
    @Id
    @Excel(name = "ID")
    private String id;
    @Column(name = "cate_name")
    @Excel(name = "级别名")
    private String cateName;
    @Excel(name = "级别")
    private Integer levels;
    @Excel(name = "父类ID")
    @Column(name = "parent_id")
    private String parentId;

    @Override
    public String toString() {
        return "Categorys{" +
                "id='" + id + '\'' +
                ", cateName='" + cateName + '\'' +
                ", levels=" + levels +
                ", parentId='" + parentId + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public Integer getLevels() {
        return levels;
    }

    public void setLevels(Integer levels) {
        this.levels = levels;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Categorys() {
    }

    public Categorys(String id, String cateName, Integer levels, String parentId) {
        this.id = id;
        this.cateName = cateName;
        this.levels = levels;
        this.parentId = parentId;
    }
}
