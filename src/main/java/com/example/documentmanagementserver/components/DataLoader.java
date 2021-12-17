package com.example.documentmanagementserver.components;

import com.example.documentmanagementserver.models.*;
import com.example.documentmanagementserver.repositories.CaseRepository;
import com.example.documentmanagementserver.repositories.DocumentRepository;
import com.example.documentmanagementserver.repositories.RoleRepository;
import com.example.documentmanagementserver.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class DataLoader implements ApplicationRunner {

    private final PasswordEncoder passwordEncoder;
    private final CaseRepository caseRepository;
    private final DocumentRepository documentRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public void run(ApplicationArguments args) {
        Role role1 = new Role("ROLE_ADMIN", "Administrator");
        Role role2 = new Role("ROLE_USER", "Użytkownik");
        roleRepository.save(role1);
        roleRepository.save(role2);

        User user1 = new User("Michał Dunat", "michu@gmail.com", passwordEncoder.encode("Useruseruser1"), role1);
        User user2 = new User("Aleksandra Dunat", "ola@gmail.com", passwordEncoder.encode("Useruseruser1"), role2);
        userRepository.save(user1);
        userRepository.save(user2);

        Address address = new Address("Tychy", "43-100", "Budowlanych", "170");
        Client client = new Client("Michał Dunat", "721000000",
                "email@gmail.com", address);
        client.setPesel("99080000000");
        Address address1 = new Address("Tychy", "43-110", "Stoczniowców", "60");
        Entity entity = new Entity("Anna Maria", "Sędzia");
        Entity entity1 = new Entity("Maria Anna", "Ławnik");
        List<Entity> entities = new ArrayList<>();
        entities.add(entity);
        entities.add(entity1);
        Court court = new Court("Rejonowy", address1, "4", "123456789",
                "o2g50b0uoy", "court@examole.com", "IP AX/Cd 670", entities, "Wydział zamiejscowy");
        court.addCourtToAllEntities();
        Proceeding proceeding = new Proceeding("Brak", true);

        Address address9 = new Address("Białystok", "11-100", "Majorska", "12");
        Address address10 = new Address("Zabrze", "22-110", "Rejsowa", "11");

        AdversePartyAttorney adversePartyAttorney1 = new AdversePartyAttorney("Michał Rosa",
                "666533256","haha@XDDD.com", address9, "Adwokat");
        AdverseParty adverseParty1 = new AdverseParty("Zuzanna Las","666256533",
                "example@o2.com", address10, adversePartyAttorney1);
        adverseParty1.setPesel("95444440000");
        Case aCase = new Case(client, adverseParty1, proceeding, court);
        aCase.setStatus("Zakończona");
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

        Client client1 = new Client("Maspex","666000000",
                "email@example.com", address2);
        client1.setNip("122-00-00-166");
        client1.setRegon("135467528");
        client1.setKrs("0000154123");
        client1.setMailingAddress(address3);

        Entity entity2 = new Entity("Jan Kowalski", "Sędzia");
        Entity entity3 = new Entity("Tomasz Przedziałowy", "Ławnik");
        Entity entity4 = new Entity("Adam Tkacz","Ławnik");
        Entity entity5 = new Entity("Matylda Ernel", "Ławnik");
        List<Entity> judges1 = new ArrayList<>();
        judges1.add(entity2);
        judges1.add(entity3);
        judges1.add(entity4);
        judges1.add(entity5);
        Court court1 = new Court("Okręgowy", address4, "2", "583065334",
                "5oy0bo2g0u", "example@court.com", "ŁI ŁE/Ło 69", judges1, "Siedziba");
        court1.addCourtToAllEntities();

        Proceeding proceeding1 = new Proceeding("2000", false);

        AdversePartyAttorney adversePartyAttorney = new AdversePartyAttorney("Elżbieta Górnaś", "666256000", "XDD@XDDD.com", address5, "Radca prawny");
        adversePartyAttorney.setMailingAddress(address6);
        AdverseParty adverseParty = new AdverseParty("Joachim Mały", "700200100",
                "example@gmail.com", address7, adversePartyAttorney);
        adverseParty.setPesel("92111000000");
        adverseParty.setMailingAddress(address8);

        Case aCase1 = new Case(client1, adverseParty, proceeding1, court1);
        caseRepository.save(aCase1);
        byte[] fileByteArray = null;
        byte[] fileByteArray1 = null;
        try {
            fileByteArray = Files.readAllBytes(Paths.get("src/main/resources/Specjalnosciowe_1_slajdy_dysk.pdf"));
            fileByteArray1 = Files.readAllBytes(Paths.get("src/main/resources/Specjalnosciowe_1_tekst_dysk.pdf"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Document document = new Document("Wniosek o wszczęcie postępowania nieprocesowego", "Maspex",
                false, Date.valueOf("2021-11-15"), Date.valueOf("2021-11-15"), fileByteArray, false, null,
                "Specjalnosciowe_1_slajdy_dysk.pdf");
        Document document1 = new Document("Pozew", "Michał Dunat",
                true, Date.valueOf("2021-11-16"), Date.valueOf("2021-11-16"), fileByteArray1, true,
                Date.valueOf("2021-11-20"), "Specjalnosciowe_1_tekst_dysk.pdf");
        document1.setMethodOfReceipt("Poczta Polska");
        document.setDateOfDelivery(Date.valueOf("2021-11-18"));
        document.setDocumentCase(aCase);
        document1.setDocumentCase(aCase);
        document.setSender(user1);
        document1.setSender(user2);

        documentRepository.save(document);
        documentRepository.save(document1);


    }
}
