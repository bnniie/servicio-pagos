package co.edu.unbosque.servicio_pagos.repository;

import co.edu.unbosque.servicio_pagos.model.Tarjeta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarjetaRepository extends JpaRepository<Tarjeta, Long> {
    boolean existsByNumero(String numero);
}