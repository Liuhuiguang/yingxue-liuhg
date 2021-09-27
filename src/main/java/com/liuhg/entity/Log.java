package com.liuhg.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Table(name = "yx_log")
public class Log {
    @Excel(name = "ID", width = 38)
    @Id
    private String id;
    @Excel(name = "管理员名称")
    @Column(name = "admin_name")
    private String adminName;
    @Column(name = "option_time")
    @Excel(name = "操作时间", format = "yyyy-MM-dd", width = 15)
    private Date optionTime;
    @Excel(name = "方法名称", width = 15)
    @Column(name = "method_name")
    private String methodName;
    @Excel(name = "状态")
    private String status;


    public Log() {
    }

    public Log(String id, String adminName, Date optionTime, String methodName, String status) {
        this.id = id;
        this.adminName = adminName;
        this.optionTime = optionTime;
        this.methodName = methodName;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id='" + id + '\'' +
                ", adminName='" + adminName + '\'' +
                ", optionTime=" + optionTime +
                ", methodName='" + methodName + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public Date getOptionTime() {
        return optionTime;
    }

    public void setOptionTime(Date optionTime) {
        this.optionTime = optionTime;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}