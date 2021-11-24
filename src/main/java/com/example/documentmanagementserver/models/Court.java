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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Court {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @EqualsAndHashCode.Include
    @NotNull
    private String type;
    @ManyToOne(cascade = {CascadeType.ALL})
    @EqualsAndHashCode.Include
    @NotNull
    private Address address;
    @EqualsAndHashCode.Include
    @NotNull
    private String department;
    @EqualsAndHashCode.Include
    @NotNull
    private String phoneNumber;
    @EqualsAndHashCode.Include
    @NotNull
    private String electronicAddressForDelivery;

    @OneToMany(cascade = {CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "court")
    private List<Judge> judgingPanel;

    @OneToMany(mappedBy = "court")
    @ToString.Exclude
    private List<Case> cases;

    public Court(String type, Address address, String department, String phoneNumber, String electronicAddressForDelivery, List<Judge> judgingPanel) {
        this.type = type;
        this.address = address;
        this.department = department;
        this.phoneNumber = phoneNumber;
        this.electronicAddressForDelivery = electronicAddressForDelivery;
        this.judgingPanel = judgingPanel;
    }
}
