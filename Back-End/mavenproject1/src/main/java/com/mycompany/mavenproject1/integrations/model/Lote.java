/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.integrations.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Daniel
 */
@Entity
@Table(name = "MP_LOTE")
public class Lote implements Serializable {

    @JoinColumn(name = "ID_EVENT", referencedColumnName = "ID_EVENT")
    @ManyToOne(optional = false)
    private Event idEvent;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_LOTE")
    private Integer idLote;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOME_LOTE")
    private String nomeLote;
    @Basic(optional = false)
    @NotNull
    @Column(name = "QTD_MAX_INGRESSOS")
    private int qtdMaxIngressos;
    @Column(name = "QTD_INGRESSOS_VENDIDOS")
    private Integer qtdIngressosVendidos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR_INGRESSO")
    private int valorIngresso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idLote")
    private List<SalesHistory> salesHistoryList;

    public Lote() {
    }

    public Lote(Integer idLote) {
        this.idLote = idLote;
    }

    public Lote(Integer idLote, String nomeLote, int qtdMaxIngressos, int valorIngresso) {
        this.idLote = idLote;
        this.nomeLote = nomeLote;

        this.qtdMaxIngressos = qtdMaxIngressos;
        this.valorIngresso = valorIngresso;
    }

    public Integer getIdLote() {
        return idLote;
    }

    public void setIdLote(Integer idLote) {
        this.idLote = idLote;
    }

    public String getNomeLote() {
        return nomeLote;
    }

    public void setNomeLote(String nomeLote) {
        this.nomeLote = nomeLote;
    }

    public int getQtdMaxIngressos() {
        return qtdMaxIngressos;
    }

    public void setQtdMaxIngressos(int qtdMaxIngressos) {
        this.qtdMaxIngressos = qtdMaxIngressos;
    }

    public Integer getQtdIngressosVendidos() {
        return qtdIngressosVendidos;
    }

    public void setQtdIngressosVendidos(Integer qtdIngressosVendidos) {
        this.qtdIngressosVendidos = qtdIngressosVendidos;
    }

    public int getValorIngresso() {
        return valorIngresso;
    }

    public void setValorIngresso(int valorIngresso) {
        this.valorIngresso = valorIngresso;
    }

    public List<SalesHistory> getSalesHistoryList() {
        return salesHistoryList;
    }

    public void setSalesHistoryList(List<SalesHistory> salesHistoryList) {
        this.salesHistoryList = salesHistoryList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLote != null ? idLote.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lote)) {
            return false;
        }
        Lote other = (Lote) object;
        if ((this.idLote == null && other.idLote != null) || (this.idLote != null && !this.idLote.equals(other.idLote))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject1.integrations.model.Lote[ idLote=" + idLote + " ]";
    }

    public Event getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(Event idEvent) {
        this.idEvent = idEvent;
    }

}
