/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.services.saleshistory.bean;

import com.mycompany.mavenproject1.services.common.GenericServiceBean;
import java.util.Date;

/**
 *
 * @author Daniel
 */
public class SalesHistoryBean extends GenericServiceBean {

    private Integer idSaleHistory;

    private Date saleDate;
    private String nomeEvent;
    private String nomeLote;
    private Integer qtdSold;

    public SalesHistoryBean() {
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

    public String getNomeEvent() {
        return nomeEvent;
    }

    public void setNomeEvent(String nomeEvent) {
        this.nomeEvent = nomeEvent;
    }

    public String getNomeLote() {
        return nomeLote;
    }

    public void setNomeLote(String nomeLote) {
        this.nomeLote = nomeLote;
    }

    public Integer getQtdSold() {
        return qtdSold;
    }

    public void setQtdSold(Integer qtdSold) {
        this.qtdSold = qtdSold;
    }

}
