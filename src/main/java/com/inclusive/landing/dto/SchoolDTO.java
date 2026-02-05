package com.inclusive.landing.dto;

import com.inclusive.landing.model.School;

public class SchoolDTO {

    private Long id;
    private String fullName;
    private String shortName;
    private String city;
    private String department;

    public SchoolDTO(School school) {
        this.id = school.getId();
        this.fullName = school.getFullName();
        this.shortName = school.getShortName();
        this.city = school.getCity();
        this.department = school.getDepartment();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
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

    
}
