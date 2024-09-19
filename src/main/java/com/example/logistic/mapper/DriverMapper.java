package com.example.logistic.mapper;

import com.example.logistic.model.dtos.DriverDTO;
import com.example.logistic.model.roles.Driver;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper (componentModel = "spring")
public interface DriverMapper {
    @Mapping(source = "vehiculo.uuid", target = "vehiculoUuid")
    @Mapping(source = "tenant.uuid", target = "tenantUuid")
    DriverDTO toDTO (Driver driver);
    @Mapping(source = "vehiculoUuid", target = "vehiculo.uuid")
    @Mapping(source = "tenantUuid", target = "tenant.uuid")
    Driver toEntity(DriverDTO driverDTO);
}
