/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.integrations.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author danie
 */
@Entity
@Table(name = "MP_EVENT")
@NamedQueries({
    @NamedQuery(name = "Event.findAll", query = "SELECT e FROM Event e")})
public class Event implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEvent")
    private List<Lote> loteList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "event")
    private List<UserEvent> userEventList;

    @Size(max = 200)
    @Column(name = "CAPACITY_EVENT")
    private String capacityEvent;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EVENT_PROFIT")
    private int eventProfit;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEvent")
    private List<Despesas> despesasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEvent")
    private List<SalesHistory> salesHistoryList;

    @ManyToMany(mappedBy = "eventList")
    private List<User> userList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_EVENT")
    private Integer idEvent;
    @Basic(optional = false)
    @Column(name = "NAME_EVENT")
    private String nameEvent;
    @Basic(optional = false)
    @Column(name = "DESC_EVENT")
    private String descEvent;
    @Basic(optional = false)
    @Column(name = "DATE_EVENT")
    @Temporal(TemporalType.DATE)
    private Date dateEvent;
    @Column(name = "IMAGE_EVENT")
    private String imageEvent;

    public Event() {
    }

    public Event(Integer idEvent) {
        this.idEvent = idEvent;
    }

    public Event(Integer idEvent, String nameEvent, String descEvent, Date dateEvent) {
        this.idEvent = idEvent;
        this.nameEvent = nameEvent;
        this.descEvent = descEvent;
        this.dateEvent = dateEvent;
    }

    public Integer getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(Integer idEvent) {
        this.idEvent = idEvent;
    }

    public String getNameEvent() {
        return nameEvent;
    }

    public void setNameEvent(String nameEvent) {
        this.nameEvent = nameEvent;
    }

    public String getDescEvent() {
        return descEvent;
    }

    public void setDescEvent(String descEvent) {
        this.descEvent = descEvent;
    }

    public Date getDateEvent() {
        return dateEvent;
    }

    public void setDateEvent(Date dateEvent) {
        this.dateEvent = dateEvent;
    }

    public String getImageEvent() {
        return imageEvent;
    }

    public void setImageEvent(String imageEvent) {
        this.imageEvent = imageEvent;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEvent != null ? idEvent.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Event)) {
            return false;
        }
        Event other = (Event) object;
        if ((this.idEvent == null && other.idEvent != null) || (this.idEvent != null && !this.idEvent.equals(other.idEvent))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject1.integrations.model.Event[ idEvent=" + idEvent + " ]";
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public String getCapacityEvent() {
        return capacityEvent;
    }

    public void setCapacityEvent(String capacityEvent) {
        this.capacityEvent = capacityEvent;
    }

    public int getEventProfit() {
        return eventProfit;
    }

    public void setEventProfit(int eventProfit) {
        this.eventProfit = eventProfit;
    }

    public List<Despesas> getDespesasList() {
        return despesasList;
    }

    public void setDespesasList(List<Despesas> despesasList) {
        this.despesasList = despesasList;
    }

    public List<SalesHistory> getSalesHistoryList() {
        return salesHistoryList;
    }

    public void setSalesHistoryList(List<SalesHistory> salesHistoryList) {
        this.salesHistoryList = salesHistoryList;
    }

    public List<UserEvent> getUserEventList() {
        return userEventList;
    }

    public void setUserEventList(List<UserEvent> userEventList) {
        this.userEventList = userEventList;
    }

    public List<Lote> getLoteList() {
        return loteList;
    }

    public void setLoteList(List<Lote> loteList) {
        this.loteList = loteList;
    }

}
