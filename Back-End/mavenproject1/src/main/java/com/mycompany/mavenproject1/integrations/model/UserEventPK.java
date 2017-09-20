/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.integrations.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author danie
 */
@Embeddable
public class UserEventPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "ID_EVENT")
    private int idEvent;
    @Basic(optional = false)
    @Column(name = "ID_USER")
    private int idUser;

    public UserEventPK() {
    }

    public UserEventPK(int idEvent, int idUser) {
        this.idEvent = idEvent;
        this.idUser = idUser;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idEvent;
        hash += (int) idUser;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserEventPK)) {
            return false;
        }
        UserEventPK other = (UserEventPK) object;
        if (this.idEvent != other.idEvent) {
            return false;
        }
        if (this.idUser != other.idUser) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject1.integrations.model.UserEventPK[ idEvent=" + idEvent + ", idUser=" + idUser + " ]";
    }
    
}
