package com.example.logistic.service;

import com.example.logistic.model.roles.Local;
import com.example.logistic.repository.LocalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LocalService {
    @Autowired
    private LocalRepository localRepository;
    public Local findByUUID (UUID uuid) {
        return localRepository.getReferenceById(uuid);
    }
}
