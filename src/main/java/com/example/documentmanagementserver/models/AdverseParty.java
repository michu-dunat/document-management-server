package com.example.documentmanagementserver.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;

@javax.persistence.Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class AdverseParty extends Client {

    @OneToOne(cascade = {CascadeType.ALL}, orphanRemoval = true)
    private AdversePartyAttorney adversePartyAttorney;

    @JsonIgnore
    @OneToOne(mappedBy = "adverseParty")
    @ToString.Exclude
    private Case bCase;

    public AdverseParty(String firstnameAndLastNameOrCompanyName, String phoneNumber, String emailAddress,
                        Address residenceOrRegisteredOfficeAddress, AdversePartyAttorney adversePartyAttorney) {
        super(firstnameAndLastNameOrCompanyName, phoneNumber, emailAddress, residenceOrRegisteredOfficeAddress);
        this.adversePartyAttorney = adversePartyAttorney;
    }
}
