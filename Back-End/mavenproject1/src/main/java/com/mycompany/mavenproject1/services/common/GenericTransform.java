/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.services.common;

import java.util.List;

/**
 *
 * @author danie
 */
public abstract class GenericTransform<T> {

    public abstract GenericServiceBean entityToBean(T object) throws Exception;

    public abstract List<GenericServiceBean> listEntityToListBean(List<T> objects) throws Exception;

}
