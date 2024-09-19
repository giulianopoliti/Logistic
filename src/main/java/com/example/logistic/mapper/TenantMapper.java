package com.example.logistic.mapper;

import com.example.logistic.model.dtos.TenantDTO;
import com.example.logistic.model.roles.Tenant;
import org.mapstruct.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TenantMapper {

    TenantMapper INSTANCE = Mappers.getMapper(TenantMapper.class);

    Tenant toEntity(TenantDTO dto);

    TenantDTO toDto(Tenant entity);

    List<TenantDTO> toDtoList(List<Tenant> tenants);

    List<Tenant> toEntityList(List<TenantDTO> tenantDTOs);
}

