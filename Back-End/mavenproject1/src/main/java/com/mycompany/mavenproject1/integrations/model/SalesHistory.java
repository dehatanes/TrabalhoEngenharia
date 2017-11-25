/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.integrations.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Daniel
 */
@Entity
@Table(name = "MP_SALES_HISTORY")
@NamedQueries({
    @NamedQuery(name = "SalesHistory.findAll", query = "SELECT s FROM SalesHistory s")})
public class SalesHistory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_SALE_HISTORY")
    private Integer idSaleHistory;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SALE_DATE")
    @Temporal(TemporalType.DATE)
    private Date saleDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "QTD_SOLD")
    private int qtdSold;
    @JoinColumn(name = "ID_EVENT", referencedColumnName = "ID_EVENT")
    @ManyToOne(optional = false)
    private Event idEvent;
    @JoinColumn(name = "ID_LOTE", referencedColumnName = "ID_LOTE")
    @ManyToOne(optional = false)
    private Lote idLote;

    public SalesHistory() {
    }

    public SalesHistory(Integer idSaleHistory) {
        this.idSaleHistory = idSaleHistory;
    }

    public SalesHistory(Integer idSaleHistory, Date saleDate, int qtdSold) {
        this.idSaleHistory = idSaleHistory;
        this.saleDate = saleDate;
        this.qtdSold = qtdSold;
    }

    public Integer getIdSaleHistory() {
        return idSaleHistory;
    }

    public void setIdSaleHistory(Integer idSaleHistory) {
        this.idSaleHistory = idSaleHistory;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public int getQtdSold() {
        return qtdSold;
    }

    public void setQtdSold(int qtdSold) {
        this.qtdSold = qtdSold;
    }

    public Event getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(Event idEvent) {
        this.idEvent = idEvent;
    }

    public Lote getIdLote() {
        return idLote;
    }

    public void setIdLote(Lote idLote) {
        this.idLote = idLote;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSaleHistory != null ? idSaleHistory.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SalesHistory)) {
            return false;
        }
        SalesHistory other = (SalesHistory) object;
        if ((this.idSaleHistory == null && other.idSaleHistory != null) || (this.idSaleHistory != null && !this.idSaleHistory.equals(other.idSaleHistory))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject1.integrations.model.SalesHistory[ idSaleHistory=" + idSaleHistory + " ]";
    }
    
}
