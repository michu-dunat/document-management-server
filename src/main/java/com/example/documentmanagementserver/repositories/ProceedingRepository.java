package com.example.documentmanagementserver.repositories;

import com.example.documentmanagementserver.models.Proceeding;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProceedingRepository extends JpaRepository<Proceeding, Integer> {
//    @Query(
//            value = "SELECT * FROM PROCEEDINGS_SUBJECT p WHERE p.claim_receipt_date = ?1 OR p.filling_date = ?1",
//            nativeQuery = true)
//    List<ProceedingsSubject> findAllByAnyDate(String input);


    List<Proceeding> findAllByValue(String value);
}
