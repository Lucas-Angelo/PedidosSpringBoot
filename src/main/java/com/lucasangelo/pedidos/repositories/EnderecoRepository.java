package com.lucasangelo.pedidos.repositories;

import com.lucasangelo.pedidos.domain.Endereco;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
    
}
