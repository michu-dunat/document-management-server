package com.example.documentmanagementserver.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "t_case")
public class Case {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = {CascadeType.ALL})
    private AdverseParty adverseParty;
    @ManyToOne(cascade = {CascadeType.ALL})
    private Client client;
    @ManyToOne(cascade = {CascadeType.ALL})
    private Court court;
    @OneToOne(cascade = {CascadeType.ALL})
    private ProceedingsSubject proceedingsSubject;

}
