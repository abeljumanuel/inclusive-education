package com.inclusive.landing.dto;

import com.inclusive.landing.model.Appendix;

public class AppendixDTO {

    private String iconTitle;
    private String title;
    private String iconDescription;
    private String description;
    private String functionTitle;
    private String functionUrl;

    public AppendixDTO(Appendix appendix) {
        this.iconTitle = appendix.getIconTitle();
        this.title = appendix.getTitle();
        this.iconDescription = appendix.getIconDescription();
        this.description = appendix.getDescription();
        this.functionTitle = appendix.getFunctionTitle();
        this.functionUrl = appendix.getFunctionUrl();
    }

    public String getIconTitle() {
        return iconTitle;
    }
    public void setIconTitle(String iconTitle) {
        this.iconTitle = iconTitle;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getIconDescription() {
        return iconDescription;
    }
    public void setIconDescription(String iconDescription) {
        this.iconDescription = iconDescription;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getFunctionTitle() {
        return functionTitle;
    }
    public void setFunctionTitle(String functionTitle) {
        this.functionTitle = functionTitle;
    }
    public String getFunctionUrl() {
        return functionUrl;
    }
    public void setFunctionUrl(String functionUrl) {
        this.functionUrl = functionUrl;
    }
    
}
