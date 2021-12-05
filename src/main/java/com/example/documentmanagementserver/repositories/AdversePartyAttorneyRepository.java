package com.example.documentmanagementserver.repositories;

import com.example.documentmanagementserver.models.AdversePartyAttorney;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdversePartyAttorneyRepository extends JpaRepository<AdversePartyAttorney, Integer> {
    @Query(
            value = "SELECT * FROM ADVERSE_PARTY_ATTORNEY a WHERE a.first_name_last_name = ?1 OR a.job_title = ?1 " +
                    "OR a.phone_number = ?1",
            nativeQuery = true)
    List<AdversePartyAttorney> findAllByAnything(String input);
}
