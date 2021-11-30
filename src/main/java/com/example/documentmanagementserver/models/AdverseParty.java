package com.example.documentmanagementserver.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class AdverseParty extends Client {

    @OneToOne(cascade = {CascadeType.ALL})
    @NotNull
    private AdversePartyAttorney adversePartyAttorney;

    @JsonIgnore
    @OneToOne(mappedBy = "adverseParty")
    @ToString.Exclude
    private Case aCase;

    public AdverseParty(String firstnameAndLastNameOrCompanyName, String pesel, String nip, String regon, String krs, String phoneNumber, String emailAddress, Address residenceOrRegisteredOfficeAddress, AdversePartyAttorney adversePartyAttorney) {
        super(firstnameAndLastNameOrCompanyName, pesel, nip, regon, krs, phoneNumber, emailAddress, residenceOrRegisteredOfficeAddress);
        this.adversePartyAttorney = adversePartyAttorney;
    }
}
