package com.lazydevs.web.blog.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "states")
public class State {
    
    @Id
    @Column(name = "cid")
    private int id;

    @Column(name="cityName")
    private String cityName;

    public State(int id, String cityName) {
        this.id = id;
        this.cityName = cityName;
    }

    public State() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    
}
