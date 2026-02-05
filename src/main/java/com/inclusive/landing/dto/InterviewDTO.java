package com.inclusive.landing.dto;

import com.inclusive.landing.model.Interview;

public class InterviewDTO {
    private String intervieweeName;
    private String intervieweePosition;
    private String quote;
    private String schoolName;
    private String urlAudio;
    private String urlFile;

    public InterviewDTO(
        Interview interview
    ){
        this.intervieweeName = interview.getIntervieweeName();
        this.intervieweePosition = interview.getIntervieweePosition();
        this.quote = interview.getQuote();
        this.schoolName = interview.getSchool() != null ? interview.getSchool().getShortName() : null;
        this.urlAudio = interview.getUrlAudio();
        this.urlFile = interview.getUrlFile();
    }

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

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
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
