package com.liuhg.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import org.apache.commons.math3.geometry.euclidean.threed.NotARotationMatrixException;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Table(name = "yx_category")
public class Category {
    @Id
    @Excel(name = "ID", needMerge = true)
    private String id;
    @Column(name = "cate_name")
    @Excel(name = "级别名", needMerge = true)
    private String cateName;
    @Excel(name = "级别", needMerge = true)
    private Integer levels;
    @Excel(name = "父类ID")
    @Column(name = "parent_id")
    private String parentId;
    @ExcelCollection(name = "二级类别")
    private List<Categorys> categories;

    public List<Categorys> getCategories() {
        return categories;
    }

    public void setCategories(List<Categorys> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "Category{" +
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

    public Category() {
    }

    public Category(String id, String cateName, Integer levels, String parentId) {
        this.id = id;
        this.cateName = cateName;
        this.levels = levels;
        this.parentId = parentId;
    }
}