package com.example.documentmanagementserver.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Court {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String type;
    @OneToOne(cascade = {CascadeType.ALL})
    @NotNull
    private Address address;
    @NotNull
    private String department;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String electronicAddressForDelivery;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "court")
    @NotNull
    private List<Judge> judgingPanel;

    @OneToOne(mappedBy = "court")
    @ToString.Exclude
    private Case aCase;

    public Court(String type, Address address, String department, String phoneNumber, String electronicAddressForDelivery, List<Judge> judgingPanel) {
        this.type = type;
        this.address = address;
        this.department = department;
        this.phoneNumber = phoneNumber;
        this.electronicAddressForDelivery = electronicAddressForDelivery;
        this.judgingPanel = judgingPanel;
    }

    public void addCourtToAllJudges() {
        for (Judge judge : this.judgingPanel
             ) {
            judge.setCourt(this);
        }
    }
}
