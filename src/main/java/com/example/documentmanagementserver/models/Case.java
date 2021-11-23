package com.example.documentmanagementserver.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
    @NotNull
    private Client client;
    @ManyToOne(cascade = {CascadeType.ALL})
    @NotNull
    private Court court;
    @OneToOne(cascade = {CascadeType.ALL})
    @NotNull
    private ProceedingsSubject proceedingsSubject;
    @ManyToOne(cascade = {CascadeType.ALL})
    private AdverseParty adverseParty;

}
