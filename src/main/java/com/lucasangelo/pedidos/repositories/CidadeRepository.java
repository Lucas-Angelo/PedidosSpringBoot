package com.lucasangelo.pedidos.repositories;

import com.lucasangelo.pedidos.domain.Cidade;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {
    
}
