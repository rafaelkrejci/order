package br.com.krejci.orders.service;

import br.com.krejci.orders.controller.order.PedidoDTO;
import br.com.krejci.orders.domain.Pedido;
import br.com.krejci.orders.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    public UUID create(List<PedidoDTO> pedidos){

        List<Pedido> pedidosEntity = new ArrayList<>();
        UUID uuid = UUID.randomUUID();
        AtomicBoolean exists = new AtomicBoolean(false);
        pedidos.forEach( p-> {
              if(this.exist(p.getNome()).isPresent()){
               throw new IllegalArgumentException("Pedido já existe na base de dados");
            }
            Pedido pedido = new Pedido();
            pedido.setNome(p.getNome());
            pedido.setValor(p.getValor());
            pedido.setOrdemId(uuid.toString());
            pedidosEntity.add(pedido);
            pedidoRepository.saveAll(pedidosEntity);
        });
        return uuid;
    }

    public List<PedidoDTO> findByOrderId(String numeroPedido){

        List<PedidoDTO> pedidoDTOS = new ArrayList<>();
        List<Pedido> pedidosDomainList = pedidoRepository.findByOrdemId(numeroPedido);
        pedidosDomainList.forEach(p ->{

            PedidoDTO dto = new PedidoDTO();
            dto.setNome(p.getNome());
            dto.setValor(p.getValor());
            pedidoDTOS.add(dto);

        });
        return pedidoDTOS;
    }
    public Optional<Pedido> exist(String nome){
       return pedidoRepository.findByNome(nome);
    }
}
