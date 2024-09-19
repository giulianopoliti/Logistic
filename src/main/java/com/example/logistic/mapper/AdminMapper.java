package com.example.logistic.mapper;

import com.example.logistic.model.dtos.AdminDTO;
import com.example.logistic.model.roles.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper (componentModel = "spring")
public interface AdminMapper {
    @Mapping(source = "tenant.uuid", target = "tenantUuid")
    AdminDTO toDTO(Admin admin);
    @Mapping(source = "tenantUuid", target = "tenant.uuid")
    Admin toEntity(AdminDTO adminDTO);
}
