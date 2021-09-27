package com.liuhg.entity;

import java.util.List;

public class ChinaData {
    private String sex;
    private List<City> citys;

    @Override
    public String toString() {
        return "ChinaData{" +
                "sex='" + sex + '\'' +
                ", citys=" + citys +
                '}';
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public List<City> getCitys() {
        return citys;
    }

    public void setCitys(List<City> citys) {
        this.citys = citys;
    }

    public ChinaData() {
    }

    public ChinaData(String sex, List<City> citys) {
        this.sex = sex;
        this.citys = citys;
    }
}
