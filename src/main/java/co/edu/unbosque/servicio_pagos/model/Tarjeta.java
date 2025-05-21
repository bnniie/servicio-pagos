package co.edu.unbosque.servicio_pagos.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@Table(name = "tarjetas")
public class Tarjeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String numero;

    @Column(nullable = false)
    private String fechaVencimiento;

    @Column(nullable = false)
    private String franquicia = "PENDIENTE";

    @Column(nullable = false)
    private String estado = "ACTIVO";

    @Column(nullable = false)
    private Double cupoTotal;

    @Column(nullable = false)
    private Double cupoDisponible;

    @Column(nullable = false)
    private Double cupoUtilizado;

@ManyToOne
@JoinColumn(name = "cliente_id")
@JsonIgnoreProperties("tarjetas")   // ← evita la recursión
private Cliente cliente;
    public Tarjeta() {}

    // Getters y Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public String getFechaVencimiento() { return fechaVencimiento; }
    public void setFechaVencimiento(String fechaVencimiento) { this.fechaVencimiento = fechaVencimiento; }

    public String getFranquicia() { return franquicia; }
    public void setFranquicia(String franquicia) { this.franquicia = franquicia; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Double getCupoTotal() { return cupoTotal; }
    public void setCupoTotal(Double cupoTotal) { this.cupoTotal = cupoTotal; }

    public Double getCupoDisponible() { return cupoDisponible; }
    public void setCupoDisponible(Double cupoDisponible) { this.cupoDisponible = cupoDisponible; }

    public Double getCupoUtilizado() { return cupoUtilizado; }
    public void setCupoUtilizado(Double cupoUtilizado) { this.cupoUtilizado = cupoUtilizado; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
}
