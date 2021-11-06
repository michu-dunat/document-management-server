package com.example.documentmanagementserver.models;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class ClientData {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;

    private String firstnameAndLastnameOrCompanyName;
    private String PESEL;
    private String NIP;
    private String REGON;
    private String KRS;
    private String phoneNumber;
    private String emailAddress;
    private String residenceOrHeadquartersNumber;
    private String mailingAddress;

    public ClientData() {
    }

    public ClientData(int id, String firstnameAndLastnameOrCompanyName, String PESEL, String NIP, String REGON, String KRS, String phoneNumber, String emailAddress, String residenceOrHeadquartersNumber, String mailingAddress) {
        this.id = id;
        this.firstnameAndLastnameOrCompanyName = firstnameAndLastnameOrCompanyName;
        this.PESEL = PESEL;
        this.NIP = NIP;
        this.REGON = REGON;
        this.KRS = KRS;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.residenceOrHeadquartersNumber = residenceOrHeadquartersNumber;
        this.mailingAddress = mailingAddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstnameAndLastnameOrCompanyName() {
        return firstnameAndLastnameOrCompanyName;
    }

    public void setFirstnameAndLastnameOrCompanyName(String firstnameAndLastnameOrCompanyName) {
        this.firstnameAndLastnameOrCompanyName = firstnameAndLastnameOrCompanyName;
    }

    public String getPESEL() {
        return PESEL;
    }

    public void setPESEL(String PESEL) {
        this.PESEL = PESEL;
    }

    public String getNIP() {
        return NIP;
    }

    public void setNIP(String NIP) {
        this.NIP = NIP;
    }

    public String getREGON() {
        return REGON;
    }

    public void setREGON(String REGON) {
        this.REGON = REGON;
    }

    public String getKRS() {
        return KRS;
    }

    public void setKRS(String KRS) {
        this.KRS = KRS;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getResidenceOrHeadquartersNumber() {
        return residenceOrHeadquartersNumber;
    }

    public void setResidenceOrHeadquartersNumber(String residenceOrHeadquartersNumber) {
        this.residenceOrHeadquartersNumber = residenceOrHeadquartersNumber;
    }

    public String getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(String mailingAddress) {
        this.mailingAddress = mailingAddress;
    }
}
