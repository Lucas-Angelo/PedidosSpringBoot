package com.lucasangelo.pedidos.repositories;

import com.lucasangelo.pedidos.domain.ItemPedido;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {
    
}
