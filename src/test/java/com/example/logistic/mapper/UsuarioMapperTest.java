package com.example.logistic.mapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.logistic.mapper.*;
import com.example.logistic.model.dtos.*;
import com.example.logistic.model.roles.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class UsuarioMapperTest {

    @Mock
    private VendedorMapper vendedorMapper;
    @Mock
    private DriverMapper driverMapper;
    @Mock
    private OperadorDepositoMapper operadorDepositoMapper;
    @Mock
    private AdminMapper adminMapper;

    @InjectMocks
    private UsuarioMapper usuarioMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void toDTO_VendedorRole_ReturnsVendedorDTO() {
        Vendedor vendedor = new Vendedor();
        VendedorDTO vendedorDTO = new VendedorDTO();
        when(vendedorMapper.toDTO(vendedor)).thenReturn(vendedorDTO);

        UsuarioDTO result = usuarioMapper.toDTO(vendedor, "vendedor");

        assertEquals(vendedorDTO, result);
    }

    @Test
    void toDTO_DriverRole_ReturnsDriverDTO() {
        Driver driver = new Driver();
        DriverDTO driverDTO = new DriverDTO();
        when(driverMapper.toDTO(driver)).thenReturn(driverDTO);

        UsuarioDTO result = usuarioMapper.toDTO(driver, "driver");

        assertEquals(driverDTO, result);
    }

    @Test
    void toDTO_OperadorDepositoRole_ReturnsOperadorDepositoDTO() {
        OperadorDeposito operadorDeposito = new OperadorDeposito();
        OperadorDepositoDTO operadorDepositoDTO = new OperadorDepositoDTO();
        when(operadorDepositoMapper.toDTO(operadorDeposito)).thenReturn(operadorDepositoDTO);

        UsuarioDTO result = usuarioMapper.toDTO(operadorDeposito, "operadorDeposito");

        assertEquals(operadorDepositoDTO, result);
    }

    @Test
    void toDTO_AdminRole_ReturnsAdminDTO() {
        Admin admin = new Admin();
        AdminDTO adminDTO = new AdminDTO();
        when(adminMapper.toDTO(admin)).thenReturn(adminDTO);

        UsuarioDTO result = usuarioMapper.toDTO(admin, "admin");

        assertEquals(adminDTO, result);
    }


}
