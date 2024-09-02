package com.example.logistic.mapper;

import com.example.logistic.model.dtos.DriverDTO;
import com.example.logistic.model.roles.Driver;
import org.mapstruct.Mapper;

@Mapper (componentModel = "spring")
public interface DriverMapper {
    DriverDTO toDTO (Driver driver);
    Driver toEntity(DriverDTO driverDTO);
}
