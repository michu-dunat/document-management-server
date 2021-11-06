package com.example.documentmanagementserver.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Judge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstnameAndLastname;

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

    public Judge() {
    }

    public Judge(int id, String firstnameAndLastname) {
        this.id = id;
        this.firstnameAndLastname = firstnameAndLastname;
    }
}
