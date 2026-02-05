package com.inclusive.landing.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "interview")
public class Interview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "interviewee_name")
    private String intervieweeName;

    @Column(name = "interviewee_position")
    private String intervieweePosition;

    private String quote;

    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;

    @Column(name = "url_audio")
    private String urlAudio;

    @Column(name = "url_file")
    private String urlFile;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public String getIntervieweeName() {
        return intervieweeName;
    }

    public void setIntervieweeName(String intervieweeName) {
        this.intervieweeName = intervieweeName;
    }

    public String getIntervieweePosition() {
        return intervieweePosition;
    }

    public void setIntervieweePosition(String intervieweePosition) {
        this.intervieweePosition = intervieweePosition;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public String getUrlAudio() {
        return urlAudio;
    }

    public void setUrlAudio(String urlAudio) {
        this.urlAudio = urlAudio;
    }

    public String getUrlFile() {
        return urlFile;
    }

    public void setUrlFile(String urlFile) {
        this.urlFile = urlFile;
    }

    

}
