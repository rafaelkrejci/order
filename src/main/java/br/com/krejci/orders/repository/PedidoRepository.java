package br.com.krejci.orders.repository;

import br.com.krejci.orders.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByOrdemId(String numeroPedido);

    Optional<Pedido> findByNome(String nome);
}
