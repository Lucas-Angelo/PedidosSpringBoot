package com.lucasangelo.pedidos.repositories;

import com.lucasangelo.pedidos.domain.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    
}
