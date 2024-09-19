package com.example.logistic.service;

import com.example.logistic.repository.OperadorDepositoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class OperadorDepositoService {
    @Autowired
    private OperadorDepositoRepository operadorDepositoRepository;
}
