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
import java.util.ArrayList;
import java.util.List;

public class GenericResponse {

    private List<GenericServiceBean> content;
    private PageMetadata pageMetadata;

    /**
     * @return the content
     */
    public List<GenericServiceBean> getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(List<GenericServiceBean> content) {
        this.content = content;
    }

    public void addContentItem(GenericServiceBean item) {
        if (content == null) {
            content = new ArrayList<>();
        }
        this.getContent().add(item);
    }

    public void addContent(List<GenericServiceBean> content) {
        if (this.content == null) {
            this.content = new ArrayList<>();
        }
        for (GenericServiceBean gsb : content) {
            this.addContentItem(gsb);
        }
    }

    /**
     * @return the pageMetadata
     */
    public PageMetadata getPageMetadata() {
        return pageMetadata;
    }

    /**
     * @param pageMetadata the pageMetadata to set
     */
    public void setPageMetadata(PageMetadata pageMetadata) {
        this.pageMetadata = pageMetadata;
    }

    public PageMetadata setupPageMetadata() {
        if (pageMetadata == null) {
            this.pageMetadata = new PageMetadata();
        }
        return pageMetadata;
    }
}
