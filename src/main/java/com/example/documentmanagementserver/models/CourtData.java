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
    @ManyToOne
    private Address address;
    private String department;
    private String phoneNumber;
    private String electronicAddressForDelivery;
    @OneToMany(mappedBy = "courtData")
    private List<Judge> judgingPanel;

    @OneToOne(mappedBy = "courtData")
    private CaseData caseData;


}
