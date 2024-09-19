package com.example.logistic.repository;

import com.example.logistic.model.roles.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Repository
public interface LocalRepository extends JpaRepository<Local, UUID> {
}
