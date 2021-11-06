package com.example.documentmanagementserver.models;

import javax.persistence.*;
import java.util.ArrayList;
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
    @OneToMany
    @JoinColumn(name = "COURT_ID", referencedColumnName = "id")
    private List<Judge> judgingPanel;
    private String electronicAddressForDelivery;

}
