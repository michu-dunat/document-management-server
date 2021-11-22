package com.example.documentmanagementserver.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class AdverseParty extends Client {

    @OneToOne
    private AdversePartyAttorney adversePartyAttorney;

    @OneToOne(mappedBy = "adverseParty")
    private Case aCase;
}
