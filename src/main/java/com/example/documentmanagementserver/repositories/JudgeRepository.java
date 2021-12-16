package com.example.documentmanagementserver.repositories;

import com.example.documentmanagementserver.models.Court;
import com.example.documentmanagementserver.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface JudgeRepository extends JpaRepository<Subject, Integer> {
    @Transactional
    void deleteAllByCourt(Court court);

    List<Subject> findAllByFirstNameLastName(String firstnameAndLastName);
}
