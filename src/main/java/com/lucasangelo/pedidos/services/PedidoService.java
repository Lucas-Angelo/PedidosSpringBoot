package com.lucasangelo.pedidos.services;

import java.util.Optional;

import com.lucasangelo.pedidos.domain.Pedido;
import com.lucasangelo.pedidos.repositories.PedidoRepository;
import com.lucasangelo.pedidos.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {
    
    @Autowired
    private PedidoRepository repo;

    public Pedido buscar(Integer id) {
        Optional<Pedido> obj = repo.findById(id); 
        return obj.orElseThrow(() -> new ObjectNotFoundException(
            "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName())
        ); 
    }

}
