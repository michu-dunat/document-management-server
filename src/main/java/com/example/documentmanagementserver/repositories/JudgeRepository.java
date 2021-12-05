package com.example.documentmanagementserver.repositories;

import com.example.documentmanagementserver.models.Court;
import com.example.documentmanagementserver.models.Judge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface JudgeRepository extends JpaRepository<Judge, Integer> {
    @Transactional
    void deleteAllByCourt(Court court);

    List<Judge> findAllByFirstNameLastName(String firstnameAndLastName);
}
