package com.example.documentmanagementserver.models;

import javax.persistence.*;

@Entity
public class Judge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private CourtData courtData;

    private String firstnameAndLastname;

    public Judge(int id, CourtData courtData, String firstnameAndLastname) {
        this.id = id;
        this.courtData = courtData;
        this.firstnameAndLastname = firstnameAndLastname;
    }

    public Judge() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CourtData getCourtData() {
        return courtData;
    }

    public void setCourtData(CourtData courtData) {
        this.courtData = courtData;
    }

    public String getFirstnameAndLastname() {
        return firstnameAndLastname;
    }

    public void setFirstnameAndLastname(String firstnameAndLastname) {
        this.firstnameAndLastname = firstnameAndLastname;
    }
}
