package com.example.logistic.mapper;

import com.example.logistic.model.dtos.PedidoDTO;
import com.example.logistic.model.dtos.RutaDTO;
import com.example.logistic.model.ruta.Ruta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RutaMapper {
    @Autowired
    private PedidoMapper pedidoMapper;
    public RutaDTO toDTO (Ruta ruta) {
        RutaDTO rutaDTO = new RutaDTO();
        rutaDTO.setDriverUuid(ruta.getDriver().getAuthId());
        rutaDTO.setUuid(ruta.getUuid());
        rutaDTO.setDate(ruta.getDate());
        rutaDTO.setCompletada(ruta.isCompletada());
        for (int i = 0; i < ruta.getPedidos().size(); i++) {

            PedidoDTO pedidoDTO = pedidoMapper.toDTO(ruta.getPedidos().get(i));
            rutaDTO.addPedidoDTO(pedidoDTO);
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
