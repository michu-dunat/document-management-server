package com.example.documentmanagementserver.components;

import com.example.documentmanagementserver.models.*;
import com.example.documentmanagementserver.repositories.CaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private CaseRepository caseRepository;

    public void run(ApplicationArguments args) {
        Address address = new Address("Tychy", "43-100", "Budowlanych", "170");
        Client client = new Client("Michał Dunat", "99080000000",
                "106-00-00-062", "123456785", "0000133156", "721000000",
                "email@gmail.com", address);
        Address address1 = new Address("Tychy", "43-110", "Stoczniowców", "60");
        Judge judge = new Judge("Anna Maria");
        Judge judge1 = new Judge("Maria Anna");
        List<Judge> judges = new ArrayList<>();
        judges.add(judge);
        judges.add(judge1);
        Court court = new Court("Rejonowy", address1, "4", "123456789", "o2g50b0uoy", judges);
        court.addCourtToAllJudges();
        ProceedingsSubject proceedingsSubject = new ProceedingsSubject("1000ZŁ", true);
        Date date = Date.valueOf("2021-11-15");
        proceedingsSubject.setClaimReceiptDate(date);


        Address address9 = new Address("Białystok", "11-100", "Majorska", "12");
        Address address10 = new Address("Zabrze", "22-110", "Rejsowa", "11");

        AdversePartyAttorney adversePartyAttorney1 = new AdversePartyAttorney("Michał Rosa", "666533256", address9, true, "Adwokat");
        AdverseParty adverseParty1 = new AdverseParty("Zuzanna Las", "95444440000",
                "100-00-00-762", "567458123", "0013001563", "720340100",
                "example@o2.com", address10, adversePartyAttorney1);
        Case aCase = new Case(client, adverseParty1, proceedingsSubject, court);
        caseRepository.save(aCase);

        Address address2 = new Address("Wrocław", "50-100", "Łabędzia", "59");
        Address address3 = new Address("Katowice", "23-110", "Różana", "23");
        address3.setApartmentNumber("12");
        Address address4 = new Address("Warszawa", "34-110", "Miła", "57");
        Address address5 = new Address("Gdańsk", "57-110", "Szybka", "14");
        address5.setApartmentNumber("32");
        Address address6 = new Address("Tychy", "89-110", "Plac Powstańców", "7");
        Address address7 = new Address("Bieruń", "61-110", "Górzysta", "5");
        Address address8 = new Address("Brzeg", "21-110", "Brzegowa", "2a");
        address8.setApartmentNumber("11a");

        Client client1 = new Client("Jakub Jerzy", "95040200120",
                "122-00-00-166", "135467528", "0000154123", "666000000",
                "email@example.com", address2);
        client1.setMailingAddress(address3);

        Judge judge2 = new Judge("Jan Kowalski");
        Judge judge3 = new Judge("Tomasz Przedziałowy");
        Judge judge4 = new Judge("Adam Tkacz");
        Judge judge5 = new Judge("Matylda Ernel");
        List<Judge> judges1 = new ArrayList<>();
        judges1.add(judge2);
        judges1.add(judge3);
        judges1.add(judge4);
        judges1.add(judge5);
        Court court1 = new Court("Okręgowy", address4, "2", "583065334", "5oy0bo2g0u", judges1);
        court1.addCourtToAllJudges();

        ProceedingsSubject proceedingsSubject1 = new ProceedingsSubject("2000ZŁ", false);
        Date date1 = Date.valueOf("2021-11-14");
        proceedingsSubject1.setFillingDate(date1);

        AdversePartyAttorney adversePartyAttorney = new AdversePartyAttorney("Elżbieta Górnaś", "666256000", address5, true, "Radca prawny");
        adversePartyAttorney.setMailingAddress(address6);
        AdverseParty adverseParty = new AdverseParty("Joachim Mały", "92111000000",
                "100-00-00-562", "567812345", "0013300156", "700200100",
                "example@gmail.com", address7, adversePartyAttorney);
        adverseParty.setMailingAddress(address8);

        Case aCase1 = new Case(client1, adverseParty, proceedingsSubject1, court1);
        caseRepository.save(aCase1);
    }
}
