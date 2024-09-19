package com.example.logistic.mapper;

import com.example.logistic.model.dtos.VendedorDTO;
import com.example.logistic.model.roles.Vendedor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VendedorMapper {
    @Mapping(source = "tenant.uuid", target = "tenantUuid")
    VendedorDTO toDTO(Vendedor vendedor);
    @Mapping(source = "tenantUuid", target = "tenant.uuid")
    Vendedor toEntity(VendedorDTO vendedorDTO);
}
