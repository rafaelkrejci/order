package br.com.krejci.orders.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigInteger;


@Data
@Entity(name = "pedidos")
public class Pedido implements Domain{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Double valor;
    @Column(name = "ordem_id")
    private String ordemId;

    public Pedido(){}
}
