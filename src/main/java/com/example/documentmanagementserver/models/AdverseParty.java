package com.example.documentmanagementserver.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper=true)
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
