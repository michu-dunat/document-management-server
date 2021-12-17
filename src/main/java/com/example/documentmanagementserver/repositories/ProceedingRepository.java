package com.example.documentmanagementserver.repositories;

import com.example.documentmanagementserver.models.Proceeding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProceedingRepository extends JpaRepository<Proceeding, Integer> {
    @Query(
            value = "SELECT * FROM PROCEEDING p WHERE p.value = ?1 OR p.basis_for_mediation = ?1",
            nativeQuery = true)
    List<Proceeding> findAllByAnything(String input);
}
