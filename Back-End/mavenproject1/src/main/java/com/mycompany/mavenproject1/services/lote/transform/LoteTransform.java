/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.services.lote.transform;

import com.mycompany.mavenproject1.integrations.model.Lote;
import com.mycompany.mavenproject1.services.common.GenericServiceBean;
import com.mycompany.mavenproject1.services.common.GenericTransform;
import com.mycompany.mavenproject1.services.lote.bean.LoteBean;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class LoteTransform extends GenericTransform<Lote> {

    public GenericServiceBean entityToBean(Lote lote) {
        LoteBean bean = new LoteBean();
        bean.setIdLote(lote.getIdLote());
        bean.setNomeLote(lote.getNomeLote());
        bean.setNomeEvent(lote.getIdEvent().getNameEvent());
        bean.setQtdIngressosVendidos(lote.getQtdIngressosVendidos());
        bean.setQtdMaxIngressos(lote.getQtdMaxIngressos());
        bean.setValorIngresso(lote.getValorIngresso());

        return bean;
    }

    public List<GenericServiceBean> listEntityToListBean(List<Lote> lotes) {
        List<GenericServiceBean> beans = new ArrayList<>();

        for (Lote lote : lotes) {
            beans.add(this.entityToBean(lote));
        }
        return beans;
    }

}
