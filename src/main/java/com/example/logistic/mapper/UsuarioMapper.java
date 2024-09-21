package com.example.logistic.mapper;

import com.example.logistic.model.dtos.*;
import com.example.logistic.model.roles.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {
    @Autowired
    private VendedorMapper vendedorMapper;
    @Autowired
    private DriverMapper driverMapper;
    @Autowired
    private OperadorDepositoMapper operadorDepositoMapper;
    @Autowired
    private AdminMapper adminMapper;
    public UsuarioDTO toDTO (Usuario usuario, String role) {
        switch (role){
        case "vendedor":
        VendedorDTO vendedorDTO = vendedorMapper.toDTO((Vendedor) usuario);
            return vendedorDTO;
            case "driver":
                DriverDTO driverDTO = driverMapper.toDTO((Driver) usuario);
                return driverDTO;
                case "operadorDeposito":
                    OperadorDepositoDTO operadorDepositoDTO = operadorDepositoMapper.toDTO((OperadorDeposito) usuario);
                    return operadorDepositoDTO;
                    case "admin":
                        AdminDTO adminDTO = adminMapper.toDTO((Admin) usuario);
                        return adminDTO;
                        default:
                            return null;
    }
    return null;
}
}
