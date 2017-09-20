/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.services.user.beans;

import com.mycompany.mavenproject1.services.common.GenericServiceBean;
import java.util.List;

/**
 *
 * @author danie
 */
public class UserBean extends GenericServiceBean {

    private Integer id;
    private String name;
    private String email;
    private String image;
    private String profile;
    private List<GenericServiceBean> events;

    public UserBean() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public List<GenericServiceBean> getEvents() {
        return events;
    }

    public void setEvents(List<GenericServiceBean> events) {
        this.events = events;
    }

}
