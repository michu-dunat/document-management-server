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
public class Court {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String type;
    @OneToOne
    private Address address;
    private String department;
    private String phoneNumber;
    private String electronicAddressForDelivery;

    @OneToMany(mappedBy = "court")
    private List<Judge> judgingPanel;

    @OneToOne(mappedBy = "court")
    private Case aCase;

}
