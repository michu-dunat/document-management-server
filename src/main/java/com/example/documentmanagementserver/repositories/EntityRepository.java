package com.example.documentmanagementserver.repositories;

import com.example.documentmanagementserver.models.Court;
import com.example.documentmanagementserver.models.Entity;
import com.example.documentmanagementserver.models.Proceeding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EntityRepository extends JpaRepository<Entity, Integer> {
    @Transactional
    void deleteAllByCourt(Court court);

    @Transactional
    void deleteAllByProceeding(Proceeding proceeding);

    @Query(
            value = "SELECT * FROM ENTITY e WHERE e.first_name_last_name = ?1 OR e.position = ?1",
            nativeQuery = true)
    List<Entity> findAllByAnything(String input);
}
