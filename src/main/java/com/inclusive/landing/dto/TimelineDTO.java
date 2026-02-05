package com.inclusive.landing.dto;

import java.util.List;

import com.inclusive.landing.model.TimeLine;

public class TimelineDTO {

    private String schoolFullName;
    private String city;
    private String department;
    private List<TimelineEntry> timelineEntries;

    public TimelineDTO(String schoolFullName, String city, String department, List<TimelineEntry> timelineEntries) {
        this.schoolFullName = schoolFullName;
        this.city = city;
        this.department = department;
        this.timelineEntries = timelineEntries;
    }

    public static class TimelineEntry {
        private Integer year;
        private String title;
        private String description;

        public TimelineEntry(TimeLine timeline) {
            this.year = timeline.getYear();
            this.title = timeline.getTitle();
            this.description = timeline.getDescription();
        }

        public Integer getYear() {
            return year;
        }

        public void setYear(Integer year) {
            this.year = year;
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
    }

    public String getSchoolFullName() {
        return schoolFullName;
    }

    public void setSchoolFullName(String schoolFullName) {
        this.schoolFullName = schoolFullName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<TimelineEntry> getTimelineEntries() {
        return timelineEntries;
    }

    public void setTimelineEntries(List<TimelineEntry> timelineEntries) {
        this.timelineEntries = timelineEntries;
    }

}
