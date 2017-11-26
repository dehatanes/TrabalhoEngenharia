/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.services.saleshistory.transform;

import com.mycompany.mavenproject1.integrations.model.SalesHistory;
import com.mycompany.mavenproject1.services.common.GenericServiceBean;
import com.mycompany.mavenproject1.services.common.GenericTransform;
import com.mycompany.mavenproject1.services.saleshistory.bean.SalesHistoryBean;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Daniel
 */
@Component
public class SalesHistoryTransform extends GenericTransform<SalesHistory> {
    
    public GenericServiceBean entityToBean(SalesHistory salesHistory) {
        SalesHistoryBean bean = new SalesHistoryBean();
        bean.setIdSaleHistory(salesHistory.getIdSaleHistory());
        bean.setNomeEvent(salesHistory.getIdEvent().getNameEvent());
        bean.setNomeLote(salesHistory.getIdLote().getNomeLote());
        bean.setSaleDate(salesHistory.getSaleDate());
        bean.setQtdSold(salesHistory.getQtdSold());
        
        return bean;
    }
    
    public List<GenericServiceBean> listEntityToListBean(List<SalesHistory> salesHistories) {
        List<GenericServiceBean> beans = new ArrayList<>();
        
        for (SalesHistory salesHistory : salesHistories) {
            beans.add(this.entityToBean(salesHistory));
        }
        return beans;
    }
    
}
