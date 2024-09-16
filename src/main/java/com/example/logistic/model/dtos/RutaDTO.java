package com.example.logistic.model.dtos;

import com.example.logistic.model.ruta.Ubicacion;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class RutaDTO {
    private Long id;
    private Date date;
    private UUID driverId;
    private List<PedidoDTO> pedidoDTOS;
    private boolean completada;
    private Long tenantId;
    public RutaDTO () {
        this.pedidoDTOS = new ArrayList<>();
    }
    public void addPedidoDTO (PedidoDTO pedidoDTO) {
        this.pedidoDTOS.add(pedidoDTO);
    }
    public void removePedidoDTO (PedidoDTO pedidoDTO) {
        this.pedidoDTOS.remove(pedidoDTO);
    }
}
