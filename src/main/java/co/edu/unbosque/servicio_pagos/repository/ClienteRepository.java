package co.edu.unbosque.servicio_pagos.repository;

import co.edu.unbosque.servicio_pagos.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
