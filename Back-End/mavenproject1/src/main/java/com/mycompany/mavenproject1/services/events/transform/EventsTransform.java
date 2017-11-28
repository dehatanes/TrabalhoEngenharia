/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.services.events.transform;

import com.mycompany.mavenproject1.integrations.model.Event;
import com.mycompany.mavenproject1.services.common.GenericServiceBean;
import com.mycompany.mavenproject1.services.events.beans.EventsBean;
import com.mycompany.mavenproject1.services.user.transform.UserTransform;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author danie
 */
@Component
public class EventsTransform {

    @Autowired
    UserTransform transform;

    public GenericServiceBean entityToBean(Event event) {
        EventsBean bean = new EventsBean();
        bean.setId(event.getIdEvent());
        bean.setName(event.getNameEvent());
        bean.setDescription(event.getDescEvent());
        bean.setDate(event.getDateEvent());
        if (event.getImageEvent() != null && event.getImageEvent().trim().length() > 0) {
            bean.setImage(event.getImageEvent());
        }
        if (event.getCapacityEvent() != null && event.getCapacityEvent().trim().length() > 0) {
            bean.setCapacity(Integer.parseInt(event.getCapacityEvent()));
        }
        bean.setProfit(event.getEventProfit());
        return bean;
    }

    public List<GenericServiceBean> listEntityToListBean(List<Event> events) {
        List<GenericServiceBean> beans = new ArrayList<>();

        for (Event event : events) {
            beans.add(this.entityToBean(event));
        }
        return beans;
    }

    public GenericServiceBean entityToBeanWithUsers(Event event) {
        EventsBean bean = new EventsBean();
        bean.setId(event.getIdEvent());
        bean.setName(event.getNameEvent());
        bean.setDescription(event.getDescEvent());
        bean.setDate(event.getDateEvent());
        if (event.getImageEvent() != null && event.getImageEvent().trim().length() > 0) {
            bean.setImage(event.getImageEvent());
        }
        if (event.getUserList() != null) {
            bean.setUsers(transform.listEntityToListBeanWithoutEvent(event.getUserList()));
        }
        bean.setCapacity(Integer.parseInt(event.getCapacityEvent()));
        bean.setProfit(event.getEventProfit());
        return bean;
    }

}
