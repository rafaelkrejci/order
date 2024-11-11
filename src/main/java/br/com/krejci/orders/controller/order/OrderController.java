package br.com.krejci.orders.controller.order;

import br.com.krejci.orders.domain.Pedido;
import br.com.krejci.orders.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody List<PedidoDTO> pedidos){

        UUID numeroPedido  = pedidoService.create(pedidos);
        return new ResponseEntity(numeroPedido.toString(), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<?> find(String numeroPedido){

        List<PedidoDTO> pedidos = pedidoService.findByOrderId(numeroPedido);
        if(pedidos.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        AtomicReference<Double> total = new AtomicReference<>(Double.valueOf(0.0));
        pedidos.forEach(p ->{
            total.set(p.getValor() + total.get());

        });

        ConsultaPedidosDTO response = new ConsultaPedidosDTO();
        response.setPedidos(pedidos);
        response.setTotal(total.get());
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
