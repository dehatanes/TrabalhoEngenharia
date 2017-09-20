/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.services.user.transform;

import com.mycompany.mavenproject1.integrations.model.User;
import com.mycompany.mavenproject1.services.common.GenericServiceBean;
import com.mycompany.mavenproject1.services.user.beans.UserBean;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author danie
 */
@Component
public class UserTransform {

    public GenericServiceBean entityToBean(User user) {
        UserBean bean = new UserBean();
        bean.setId(user.getIdUser());
        bean.setName(user.getNameUser());
        bean.setEmail(user.getEmailUser());
        bean.setProfile(user.getUserProfile());
        if (user.getImageUser() != null && user.getImageUser().trim().length() > 0) {
            bean.setImage(user.getImageUser());
        }
        return bean;
    }

    public List<GenericServiceBean> listEntityToListBean(List<User> users) {
        List<GenericServiceBean> beans = new ArrayList<>();

        for (User user : users) {
            beans.add(this.entityToBean(user));
        }
        return beans;
    }
}
