package com.example.documentmanagementserver.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;


@javax.persistence.Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Court {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Column(length = 50)
    private String type;
    @OneToOne(cascade = {CascadeType.ALL})
    @NotNull
    private Address address;
    @NotNull
    @Column(length = 50)
    private String department;
    @NotNull
    @Column(length = 20)
    private String phoneNumber;
    @NotNull
    @Column(length = 50)
    private String electronicAddressForDelivery;
    @NotNull
    private String emailAddress;
    @NotNull
    private String caseSignature;
    @NotNull
    @Column(length = 50)
    private String unitType;
    @Column(length = 10000)
    private String comments;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "court")
    @NotNull
    private List<Entity> entities;

    @JsonIgnore
    @OneToOne(mappedBy = "court")
    @ToString.Exclude
    private Case aCase;

    public Court(String type, Address address, String department, String phoneNumber,
                 String electronicAddressForDelivery, String emailAddress, String caseSignature,
                 List<Entity> entities, String unitType) {
        this.type = type;
        this.address = address;
        this.department = department;
        this.phoneNumber = phoneNumber;
        this.electronicAddressForDelivery = electronicAddressForDelivery;
        this.emailAddress = emailAddress;
        this.caseSignature = caseSignature;
        this.entities = entities;
        this.unitType = unitType;
    }

    public void addCourtToAllEntities() {
        for (Entity entity : this.entities
        ) {
            entity.setCourt(this);
        }
    }
}
