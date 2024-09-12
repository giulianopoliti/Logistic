package com.example.logistic.model.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoMeliDTO extends PedidoDTO{
    private String orderId; // ID pedido mercado libre
    private String sellerId; // ID vendedor MELI
}
