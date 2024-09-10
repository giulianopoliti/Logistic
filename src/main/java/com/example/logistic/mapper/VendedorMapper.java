package com.example.logistic.mapper;

import com.example.logistic.model.dtos.ClienteDTO;
import com.example.logistic.model.dtos.VendedorDTO;
import com.example.logistic.model.roles.Cliente;
import com.example.logistic.model.roles.Vendedor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VendedorMapper {
    @Mapping(source = "tenant.id", target = "tenantId")
    VendedorDTO toDTO(Vendedor vendedor);
    @Mapping(source = "tenantId", target = "tenant.id")
    Vendedor toEntity(VendedorDTO vendedorDTO);
}
