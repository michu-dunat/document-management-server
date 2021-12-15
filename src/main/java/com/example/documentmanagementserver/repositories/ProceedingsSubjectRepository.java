package com.example.documentmanagementserver.repositories;

import com.example.documentmanagementserver.models.ProceedingsSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProceedingsSubjectRepository extends JpaRepository<ProceedingsSubject, Integer> {
//    @Query(
//            value = "SELECT * FROM PROCEEDINGS_SUBJECT p WHERE p.claim_receipt_date = ?1 OR p.filling_date = ?1",
//            nativeQuery = true)
//    List<ProceedingsSubject> findAllByAnyDate(String input);

    List<ProceedingsSubject> findAllByValue(String value);
}
