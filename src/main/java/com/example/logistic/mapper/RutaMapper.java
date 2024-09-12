package com.example.logistic.mapper;

import com.example.logistic.model.dtos.RutaDTO;
import com.example.logistic.model.ruta.Ruta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RutaMapper {
    @Autowired
    private PedidoMapper pedidoMapper;
    public RutaDTO toDTO (Ruta ruta) {
        RutaDTO rutaDTO = new RutaDTO();
        rutaDTO.setDriverId(ruta.getDriver().getId());
        for (int i = 0; i < ruta.getPedidos().size(); i++) {
            rutaDTO.getPedidoDTOS().add(pedidoMapper.toDTO(ruta.getPedidos().get(i)));
        }
        return rutaDTO;
    }
    public Ruta toEntity (RutaDTO rutaDTO) {
        Ruta ruta = new Ruta();
        for (int i = 0; i < rutaDTO.getPedidoDTOS().size(); i++) {
            return null;
        }
        return ruta;
    }

}
