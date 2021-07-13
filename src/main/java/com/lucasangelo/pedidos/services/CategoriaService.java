package com.lucasangelo.pedidos.services;

import java.util.Optional;

import com.lucasangelo.pedidos.domain.Categoria;
import com.lucasangelo.pedidos.repositories.CategoriaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
    
    @Autowired
    private CategoriaRepository repo;

    public Categoria buscar(Integer id) {
        Optional<Categoria> obj = repo.findById(id); 
        return obj.orElse(null);
    }

}
