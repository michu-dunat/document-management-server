package com.example.documentmanagementserver.repositories;

import com.example.documentmanagementserver.models.Case;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaseRepository extends JpaRepository<Case, Integer> {
}
