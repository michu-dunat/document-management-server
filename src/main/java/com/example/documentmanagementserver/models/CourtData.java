package com.example.documentmanagementserver.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
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

    @OneToOne(mappedBy = "courtData")
    private CaseData caseData;

    private String electronicAddressForDelivery;

}
