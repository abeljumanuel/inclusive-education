package com.inclusive.landing.dto;

import com.inclusive.landing.model.Finding;

public class FindingDTO {

    private Long id;
    private String iconTitle;
    private String title;
    private String description;
    private String modalTitle;
    private String modalDescription;
    private String modalImagePath;

    public FindingDTO() {
    }

    public FindingDTO(Finding finding) {
        this.id = finding.getId();
        this.iconTitle = finding.getIconTitle();
        this.title = finding.getTitle();
        this.description = finding.getDescription();
        if (finding.getModalFinding() != null) {
            this.modalTitle = finding.getModalFinding().getTitle();
            this.modalDescription = finding.getModalFinding().getDescription();
            this.modalImagePath = finding.getModalFinding().getImagePath();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModalTitle() {
        return modalTitle;
    }

    public void setModalTitle(String modalTitle) {
        this.modalTitle = modalTitle;
    }

    public String getModalDescription() {
        return modalDescription;
    }

    public void setModalDescription(String modalDescription) {
        this.modalDescription = modalDescription;
    }

    public String getModalImagePath() {
        return modalImagePath;
    }

    public void setModalImagePath(String modalImagePath) {
        this.modalImagePath = modalImagePath;
    }

    
}
