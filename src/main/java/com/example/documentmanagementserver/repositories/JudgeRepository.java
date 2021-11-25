package com.example.documentmanagementserver.repositories;

import com.example.documentmanagementserver.models.Judge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface JudgeRepository extends JpaRepository<Judge, Integer> {

    @Modifying
    @Query("delete from Judge j where j.id = ?1")
    void delete(int judgeId);
}
