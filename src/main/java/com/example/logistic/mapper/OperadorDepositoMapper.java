package com.example.logistic.mapper;

import com.example.logistic.model.dtos.AdminDTO;
import com.example.logistic.model.dtos.OperadorDepositoDTO;
import com.example.logistic.model.roles.Admin;
import com.example.logistic.model.roles.OperadorDeposito;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")

public interface OperadorDepositoMapper {
    @Mapping(source = "tenant.uuid", target = "tenantUuid")
    OperadorDepositoDTO toDTO(OperadorDeposito operadorDeposito);
    @Mapping(source = "tenantUuid", target = "tenant.uuid")
    OperadorDeposito toEntity(OperadorDepositoDTO operadorDepositoDTO);
}
