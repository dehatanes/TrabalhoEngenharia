/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.services.despesas.transform;

import com.mycompany.mavenproject1.integrations.model.Despesas;
import com.mycompany.mavenproject1.services.common.GenericServiceBean;
import com.mycompany.mavenproject1.services.common.GenericTransform;
import com.mycompany.mavenproject1.services.despesas.bean.DespesaBean;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Daniel
 */
@Component
public class DespesaTransform extends GenericTransform<Despesas> {

    public GenericServiceBean entityToBean(Despesas despesa) {
        DespesaBean bean = new DespesaBean();
        bean.setIdDespesa(despesa.getIdDespesa());
        bean.setNomeItem(despesa.getNomeItem());
        bean.setDescItem(despesa.getDescItem());
        bean.setQtd(despesa.getQtd());
        bean.setStatus(despesa.getStatus());
        bean.setValorUnitario(despesa.getValorUnitario());

        return bean;
    }

    public List<GenericServiceBean> listEntityToListBean(List<Despesas> despesas) {
        List<GenericServiceBean> beans = new ArrayList<>();

        for (Despesas despesa : despesas) {
            beans.add(this.entityToBean(despesa));
        }
        return beans;
    }

//    public GenericServiceBean entityToBeanWithUsers(Despesas despesa) {
// DespesaBean bean = new DespesaBean();
//        bean.setIdDespesa(despesa.getIdDespesa());
//        bean.setNomeItem(despesa.getNomeItem());
//        bean.setDescItem(despesa.getDescItem());
//        bean.setQtd(despesa.getQtd());
//        bean.setStatus(despesa.getStatus());
//        bean.setValorUnitario(despesa.getValorUnitario());
//        if (despesa.getUserList() != null) {
//            bean.setUsers(transform.listEntityToListBeanWithoutDespesas(despesa.getUserList()));
//        }
//        return bean;
//    }
}
