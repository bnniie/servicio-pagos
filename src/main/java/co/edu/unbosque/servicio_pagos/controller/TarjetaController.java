package co.edu.unbosque.servicio_pagos.controller;

import co.edu.unbosque.servicio_pagos.model.Tarjeta;
import co.edu.unbosque.servicio_pagos.repository.TarjetaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200") // angular
@RestController
@RequestMapping("/api/tarjetas")
public class TarjetaController {

    private final TarjetaRepository tarjetaRepository;

    public TarjetaController(TarjetaRepository tarjetaRepository) {
        this.tarjetaRepository = tarjetaRepository;
    }

    // PATCH - Cambiar estado a INACTIVO
    @PatchMapping("/{id}/estado")
    public ResponseEntity<?> cambiarEstado(@PathVariable Long id, @RequestBody EstadoRequest request) {
        Optional<Tarjeta> tarjetaOptional = tarjetaRepository.findById(id);
        if (tarjetaOptional.isEmpty()) return ResponseEntity.notFound().build();

        Tarjeta tarjeta = tarjetaOptional.get();
        tarjeta.setEstado(request.getEstado()); // ACTIVO / INACTIVO
        tarjetaRepository.save(tarjeta);
        return ResponseEntity.ok().build();
    }

    // PATCH - Editar cupo total
    @PatchMapping("/{id}/cupoTotal")
    public ResponseEntity<?> editarCupoTotal(@PathVariable Long id, @RequestBody CupoTotalRequest request) {
        Optional<Tarjeta> tarjetaOptional = tarjetaRepository.findById(id);
        if (tarjetaOptional.isEmpty()) return ResponseEntity.notFound().build();

        Tarjeta tarjeta = tarjetaOptional.get();
        tarjeta.setCupoTotal(request.getCupo_total()); // actualiza también cupoUtilizado
        tarjetaRepository.save(tarjeta);
        return ResponseEntity.ok().build();
    }

    // DTO interno para recibir "estado"
    public static class EstadoRequest {
        private String estado;
        public String getEstado() { return estado; }
        public void setEstado(String estado) { this.estado = estado; }
    }

    // DTO interno para recibir "cupo_total"
    public static class CupoTotalRequest {
        private Double cupo_total;
        public Double getCupo_total() { return cupo_total; }
        public void setCupo_total(Double cupo_total) { this.cupo_total = cupo_total; }
    }

    @GetMapping("/{id}")
public ResponseEntity<Tarjeta> obtenerTarjeta(@PathVariable Long id) {
    Optional<Tarjeta> tarjetaOptional = tarjetaRepository.findById(id);
    return tarjetaOptional.map(ResponseEntity::ok)
                          .orElseGet(() -> ResponseEntity.notFound().build());
}
}