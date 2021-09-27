package com.liuhg.entity;

import java.io.Serializable;

public class City implements Serializable {
    private String name;
    private Integer value;

    public City(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public City() {
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
