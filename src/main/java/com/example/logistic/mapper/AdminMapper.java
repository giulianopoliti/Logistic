package com.example.logistic.mapper;

import com.example.logistic.model.dtos.AdminDTO;
import com.example.logistic.model.roles.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AdminMapper {
    @Mapping(source = "tenant.id", target = "tenantId")
    AdminDTO toDTO(Admin admin);
    @Mapping(source = "tenantId", target = "tenant.id")
    Admin toEntity(AdminDTO adminDTO);
}
