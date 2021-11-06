package com.example.documentmanagementserver.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ProceedingsSubject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String value;
    private String fillingDate;
    private String claimReceiptDate;
    private Boolean isMediationPossible;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getFillingDate() {
        return fillingDate;
    }

    public void setFillingDate(String fillingDate) {
        this.fillingDate = fillingDate;
    }

    public String getClaimReceiptDate() {
        return claimReceiptDate;
    }

    public void setClaimReceiptDate(String claimReceiptDate) {
        this.claimReceiptDate = claimReceiptDate;
    }

    public Boolean getMediationPossible() {
        return isMediationPossible;
    }

    public void setMediationPossible(Boolean mediationPossible) {
        isMediationPossible = mediationPossible;
    }

    public ProceedingsSubject() {
    }

    public ProceedingsSubject(int id, String value, String fillingDate, String claimReceiptDate, Boolean isMediationPossible) {
        this.id = id;
        this.value = value;
        this.fillingDate = fillingDate;
        this.claimReceiptDate = claimReceiptDate;
        this.isMediationPossible = isMediationPossible;
    }
}
