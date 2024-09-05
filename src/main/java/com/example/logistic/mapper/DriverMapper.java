package com.example.logistic.mapper;

import com.example.logistic.model.dtos.DriverDTO;
import com.example.logistic.model.roles.Driver;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper (componentModel = "spring")
public interface DriverMapper {
    @Mapping(source = "vehiculo.id", target = "vehiculoId")
    DriverDTO toDTO (Driver driver);
    @Mapping(source = "vehiculoId", target = "vehiculo.id")
    Driver toEntity(DriverDTO driverDTO);
}
