package br.com.krejci.orders.service;

import br.com.krejci.orders.controller.order.PedidoDTO;
import br.com.krejci.orders.domain.Pedido;
import br.com.krejci.orders.repository.PedidoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class PedidoServiceTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private PedidoService pedidoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreatePedidoSuccess() {
        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.setNome("Pedido1");
        pedidoDTO.setValor(100.0);

        when(pedidoRepository.findByNome(anyString())).thenReturn(Optional.empty());

        UUID uuid = pedidoService.create(Arrays.asList(pedidoDTO));

        Assertions.assertNotNull(uuid);
    }

    @Test
    public void testCreatePedidoAlreadyExists() {
        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.setNome("Pedido1");
        pedidoDTO.setValor(100.0);

        Pedido existingPedido = new Pedido();
        existingPedido.setNome("Pedido1");

        when(pedidoRepository.findByNome(anyString())).thenReturn(Optional.of(existingPedido));

        assertThrows(IllegalArgumentException.class, () -> {
            pedidoService.create(Arrays.asList(pedidoDTO));
        });
    }

    @Test
    public void testFindByOrderId() {
        Pedido pedido = new Pedido();
        pedido.setNome("Pedido1");
        pedido.setValor(100.0);
        pedido.setOrdemId("1234");

        when(pedidoRepository.findByOrdemId(anyString())).thenReturn(Arrays.asList(pedido));

        var result = pedidoService.findByOrderId("1234");

        assertEquals(1, result.size());
        assertEquals("Pedido1", result.get(0).getNome());
        assertEquals(100.0, result.get(0).getValor());
    }
}