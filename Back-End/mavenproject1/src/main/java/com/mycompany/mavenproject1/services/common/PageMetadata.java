/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.services.common;

/**
 *
 * @author danie
 */
public class PageMetadata {

    private Integer size;
    private Long totalElements;
    private Integer totalPages;

    /**
     * @return the size
     */
    public Integer getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(Integer size) {
        this.size = size;
    }

    /**
     * @return the totalElements
     */
    public Long getTotalElements() {
        return totalElements;
    }

    /**
     * @param totalElements the totalElements to set
     */
    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    /**
     * @return the totalPages
     */
    public Integer getTotalPages() {
        return totalPages;
    }

    /**
     * @param totalPages the totalPages to set
     */
    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

}
