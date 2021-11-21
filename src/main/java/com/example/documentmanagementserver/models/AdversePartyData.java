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
public class AdversePartyData extends ClientData {

    @OneToOne(mappedBy="adversePartyData")
    private AdversePartyAttorneyData adversePartyAttorneyData;

    @OneToOne(mappedBy="adversePartyData")
    private CaseData caseData;
}
