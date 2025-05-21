package co.edu.unbosque.servicio_pagos.service;

import co.edu.unbosque.servicio_pagos.model.Cliente;
import co.edu.unbosque.servicio_pagos.model.Tarjeta;
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
    if (cliente.getTarjetas() != null) {
        for (Tarjeta t : cliente.getTarjetas()) {
            t.setCliente(cliente);                                 // relación inversa
            t.setFranquicia(detectarFranquicia(t.getNumero()));    // franquicia
            t.setCupoUtilizado(t.getCupoTotal() - t.getCupoDisponible());
            t.setEstado("ACTIVO");
        }
    }
    return repository.save(cliente);
}

private String detectarFranquicia(String numero) {
    if (numero == null) return "DESCONOCIDA";

    if (numero.length() == 16) {
        if (numero.startsWith("4")) return "VISA";
        int pref = Integer.parseInt(numero.substring(0, 2));
        if (pref >= 51 && pref <= 55) return "MASTERCARD";
    } else if (numero.length() == 15) {
        String pre2 = numero.substring(0, 2);
        if ("34".equals(pre2) || "37".equals(pre2)) return "AMEX";
    }
    return "DESCONOCIDA";
}


    public List<Cliente> listarClientes() {
        return repository.findAll();
    }
}