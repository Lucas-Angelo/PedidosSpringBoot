package com.lucasangelo.pedidos.services;

import java.util.Optional;

import com.lucasangelo.pedidos.domain.Categoria;
import com.lucasangelo.pedidos.repositories.CategoriaRepository;
import com.lucasangelo.pedidos.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
    
    @Autowired
    private CategoriaRepository repo;

    public Categoria buscar(Integer id) {
        Optional<Categoria> obj = repo.findById(id); 
        return obj.orElseThrow(() -> new ObjectNotFoundException(
            "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName())
        ); 
    }

    public Categoria insert(Categoria obj) {
        obj.setId(null); // Pois se vir com id, vai atualizar ao invés de criar um novo
        return repo.save(obj);
    }

}
