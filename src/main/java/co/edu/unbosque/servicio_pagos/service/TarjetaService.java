package co.edu.unbosque.servicio_pagos.service;

import co.edu.unbosque.servicio_pagos.model.Tarjeta;
import co.edu.unbosque.servicio_pagos.repository.TarjetaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TarjetaService {

    private final TarjetaRepository tarjetaRepository;

    public TarjetaService(TarjetaRepository tarjetaRepository) {
        this.tarjetaRepository = tarjetaRepository;
    }

    public Optional<Tarjeta> getById(Long id) {
        return tarjetaRepository.findById(id);
    }

    public Tarjeta actualizarCupoTotal(Long id, Double nuevoCupoTotal) {
        Tarjeta tarjeta = tarjetaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarjeta no encontrada"));

        tarjeta.setCupoTotal(nuevoCupoTotal);
        return tarjetaRepository.save(tarjeta);
    }

    public Tarjeta cambiarEstado(Long id, String nuevoEstado) {
        Tarjeta tarjeta = tarjetaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarjeta no encontrada"));

        tarjeta.setEstado(nuevoEstado);
        return tarjetaRepository.save(tarjeta);
    }
}