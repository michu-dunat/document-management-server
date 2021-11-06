package com.example.documentmanagementserver.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AdversePartyAttorneyData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstnameAndLastname;
    private String phoneNumber;
    private String residenceOrHeadquartersNumber;
    private Boolean isAttorneyProfessional;
    private String jobTitle;

    public AdversePartyAttorneyData() {
    }

    public AdversePartyAttorneyData(int id, String firstnameAndLastname, String phoneNumber, String residenceOrHeadquartersNumber, Boolean isAttorneyProfessional, String jobTitle) {
        this.id = id;
        this.firstnameAndLastname = firstnameAndLastname;
        this.phoneNumber = phoneNumber;
        this.residenceOrHeadquartersNumber = residenceOrHeadquartersNumber;
        this.isAttorneyProfessional = isAttorneyProfessional;
        this.jobTitle = jobTitle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstnameAndLastname() {
        return firstnameAndLastname;
    }

    public void setFirstnameAndLastname(String firstnameAndLastname) {
        this.firstnameAndLastname = firstnameAndLastname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getResidenceOrHeadquartersNumber() {
        return residenceOrHeadquartersNumber;
    }

    public void setResidenceOrHeadquartersNumber(String residenceOrHeadquartersNumber) {
        this.residenceOrHeadquartersNumber = residenceOrHeadquartersNumber;
    }

    public Boolean getAttorneyProfessional() {
        return isAttorneyProfessional;
    }

    public void setAttorneyProfessional(Boolean attorneyProfessional) {
        isAttorneyProfessional = attorneyProfessional;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
}
