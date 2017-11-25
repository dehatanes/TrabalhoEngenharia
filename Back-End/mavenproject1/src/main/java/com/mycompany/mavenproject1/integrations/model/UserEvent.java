/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.integrations.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author danie
 */
@Entity
@Table(name = "MP_USER_EVENT")
@NamedQueries({
    @NamedQuery(name = "UserEvent.findAll", query = "SELECT u FROM UserEvent u")})
public class UserEvent implements Serializable {

    @Size(max = 50)
    @Column(name = "USER_PERMISSION")
    private String userPermission;
    @JoinColumn(name = "ID_EVENT", referencedColumnName = "ID_EVENT", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Event event;
    @JoinColumn(name = "ID_USER", referencedColumnName = "ID_USER", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserEventPK userEventPK;

    public UserEvent() {
    }

    public UserEvent(UserEventPK userEventPK) {
        this.userEventPK = userEventPK;
    }

    public UserEvent(int idEvent, int idUser) {
        this.userEventPK = new UserEventPK(idEvent, idUser);
    }

    public UserEventPK getUserEventPK() {
        return userEventPK;
    }

    public void setUserEventPK(UserEventPK userEventPK) {
        this.userEventPK = userEventPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userEventPK != null ? userEventPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserEvent)) {
            return false;
        }
        UserEvent other = (UserEvent) object;
        if ((this.userEventPK == null && other.userEventPK != null) || (this.userEventPK != null && !this.userEventPK.equals(other.userEventPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject1.integrations.model.UserEvent[ userEventPK=" + userEventPK + " ]";
    }

    public String getUserPermission() {
        return userPermission;
    }

    public void setUserPermission(String userPermission) {
        this.userPermission = userPermission;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
}
