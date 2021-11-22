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

    @ManyToOne
    private AdverseParty adverseParty;
    @ManyToOne
    private Client client;
    @ManyToOne
    private Court court;
    @OneToOne
    private ProceedingsSubject proceedingsSubject;

}
