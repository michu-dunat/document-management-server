package com.example.documentmanagementserver.models;

import javax.persistence.*;
import java.util.List;


@Entity
public class CourtData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String type;
    private String town;
    private String address;
    private String department;
    private String phoneNumber;

    @OneToMany(mappedBy = "courtData")
    private List<Judge> judgingPanel;

    @OneToOne(mappedBy="courtData")
    private CaseData caseData;

    private String electronicAddressForDelivery;

    public CourtData(int id, String type, String town, String address, String department, String phoneNumber, List<Judge> judgingPanel, String electronicAddressForDelivery) {
        this.id = id;
        this.type = type;
        this.town = town;
        this.address = address;
        this.department = department;
        this.phoneNumber = phoneNumber;
        this.judgingPanel = judgingPanel;
        this.electronicAddressForDelivery = electronicAddressForDelivery;
    }

    public CourtData() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Judge> getJudgingPanel() {
        return judgingPanel;
    }

    public void setJudgingPanel(List<Judge> judgingPanel) {
        this.judgingPanel = judgingPanel;
    }

    public String getElectronicAddressForDelivery() {
        return electronicAddressForDelivery;
    }

    public void setElectronicAddressForDelivery(String electronicAddressForDelivery) {
        this.electronicAddressForDelivery = electronicAddressForDelivery;
    }
}
