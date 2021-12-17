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
    @NotNull
    private String emailAddress;
    @NotNull
    private String caseSignature;
    @NotNull
    private String unitType;

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
