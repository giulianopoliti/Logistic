package com.example.logistic.mapper;

import com.example.logistic.model.dtos.AdminDTO;
import com.example.logistic.model.roles.Admin;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdminMapper {
    AdminDTO toDTO(Admin admin);
    Admin toEntity(AdminDTO adminDTO);
}
