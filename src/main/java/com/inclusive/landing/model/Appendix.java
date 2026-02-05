package com.inclusive.landing.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "appendix")
public class Appendix {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(name = "icon_title")
    private String iconTitle;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "icon_description")
    private String iconDescription;

    @Column(name = "function_title")
    private String functionTitle;

    @Column(name = "function_url")
    private String functionUrl;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIconTitle() {
        return iconTitle;
    }

    public void setIconTitle(String iconTitle) {
        this.iconTitle = iconTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIconDescription() {
        return iconDescription;
    }

    public void setIconDescription(String iconDescription) {
        this.iconDescription = iconDescription;
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
