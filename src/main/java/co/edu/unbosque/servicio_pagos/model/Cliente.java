package co.edu.unbosque.servicio_pagos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String identificacion;
    
    @Column(nullable = false)
    private String email;
    
    @Column(nullable = false)
    private String nombre;
    
    @Column(nullable = false)
    private String estado = "ACTIVO";

    public Cliente() {}

    public Cliente(String identificacion, String email, String nombre, String estado) {
        this.identificacion = identificacion;
        this.email = email;
        this.nombre = nombre;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", identificacion='" + identificacion + '\'' +
                ", email='" + email + '\'' +
                ", nombre='" + nombre + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}