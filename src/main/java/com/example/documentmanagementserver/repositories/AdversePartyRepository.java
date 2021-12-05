package com.example.documentmanagementserver.repositories;

import com.example.documentmanagementserver.models.AdverseParty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdversePartyRepository extends JpaRepository<AdverseParty, Integer> {
    @Query(
            value = "SELECT * FROM ADVERSE_PARTY co WHERE co.phone_number = ?1 OR co.krs = ?1 OR co.nip = ?1 OR co.pesel = ?1" +
                    " OR co.regon = ?1 OR co.email_address = ?1 OR co.first_name_last_name_company_name = ?1",
            nativeQuery = true)
    List<AdverseParty> findAllByAnything(String input);
}
