package com.lucasangelo.pedidos.repositories;

import com.lucasangelo.pedidos.domain.Produto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    
}
