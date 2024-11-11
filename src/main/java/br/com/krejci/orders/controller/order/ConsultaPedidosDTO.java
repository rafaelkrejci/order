package br.com.krejci.orders.controller.order;

import lombok.Data;

import java.util.List;

@Data
public class ConsultaPedidosDTO {

    private List<PedidoDTO> pedidos;
    private Double total;
}
