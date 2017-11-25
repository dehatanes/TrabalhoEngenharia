/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.services.lote.bean;

import com.mycompany.mavenproject1.services.common.GenericServiceBean;

/**
 *
 * @author Daniel
 */
public class LoteBean extends GenericServiceBean {

    private Integer idLote;
    private String nomeEvent;
    private String nomeLote;
    private Integer qtdMaxIngressos;
    private Integer qtdIngressosVendidos;
    private Integer valorIngresso;
    //falta historico de vendas 

    public LoteBean() {
    }

    public Integer getIdLote() {
        return idLote;
    }

    public void setIdLote(Integer idLote) {
        this.idLote = idLote;
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

    public Integer getQtdMaxIngressos() {
        return qtdMaxIngressos;
    }

    public void setQtdMaxIngressos(Integer qtdMaxIngressos) {
        this.qtdMaxIngressos = qtdMaxIngressos;
    }

    public Integer getQtdIngressosVendidos() {
        return qtdIngressosVendidos;
    }

    public void setQtdIngressosVendidos(Integer qtdIngressosVendidos) {
        this.qtdIngressosVendidos = qtdIngressosVendidos;
    }

    public Integer getValorIngresso() {
        return valorIngresso;
    }

    public void setValorIngresso(Integer valorIngresso) {
        this.valorIngresso = valorIngresso;
    }

}
