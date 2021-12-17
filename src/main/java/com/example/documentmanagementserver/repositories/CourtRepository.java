package com.example.documentmanagementserver.repositories;

import com.example.documentmanagementserver.models.Court;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourtRepository extends JpaRepository<Court, Integer> {
    @Query(
            value = "SELECT * FROM COURT c WHERE c.case_signature = ?1 OR c.department = ?1 " +
                    "OR c.electronic_address_for_delivery = ?1 OR c.email_address = ?1 " +
                    "OR c.phone_number = ?1 OR c.type = ?1 OR c.unit_type = ?1",
            nativeQuery = true)
    List<Court> findAllByAnything(String input);
}
