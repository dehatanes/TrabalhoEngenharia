/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.services.events.beans;

import com.mycompany.mavenproject1.services.common.GenericServiceBean;
import java.util.Date;
import java.util.List;

/**
 *
 * @author danie
 */
public class EventsBean extends GenericServiceBean {

    private Integer id;
    private String name;
    private String description;
    private String image;
    private Date date;
    private Integer capacity;
    private Integer profit;

    public EventsBean() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    private List<GenericServiceBean> users;

    public List<GenericServiceBean> getUsers() {
        return users;
    }

    public void setUsers(List<GenericServiceBean> users) {
        this.users = users;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getProfit() {
        return profit;
    }

    public void setProfit(Integer profit) {
        this.profit = profit;
    }

}