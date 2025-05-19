package co.edu.unbosque.servicio_pagos.service;

import co.edu.unbosque.servicio_pagos.model.Cliente;
import co.edu.unbosque.servicio_pagos.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public Cliente registrarCliente(Cliente cliente) {
        return repository.save(cliente);
    }

    public List<Cliente> listarClientes() {
        return repository.findAll();
    }
}
