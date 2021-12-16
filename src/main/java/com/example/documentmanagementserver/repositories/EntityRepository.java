package com.example.documentmanagementserver.repositories;

import com.example.documentmanagementserver.models.Court;
import com.example.documentmanagementserver.models.Entity;
import com.example.documentmanagementserver.models.Proceeding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EntityRepository extends JpaRepository<Entity, Integer> {
    @Transactional
    void deleteAllByCourt(Court court);

    @Transactional
    void deleteAllByProceeding(Proceeding proceeding);

    List<Entity> findAllByFirstNameLastName(String firstnameAndLastName);
}
