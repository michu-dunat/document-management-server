package com.example.documentmanagementserver.components;

import com.example.documentmanagementserver.models.*;
import com.example.documentmanagementserver.repositories.AddressRepository;
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
        Case aCase = new Case(client, court, proceedingsSubject);
        caseRepository.save(aCase);
    }
}
