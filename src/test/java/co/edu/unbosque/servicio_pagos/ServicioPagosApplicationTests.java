package co.edu.unbosque.servicio_pagos;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import co.edu.unbosque.servicio_pagos.model.Cliente;
import co.edu.unbosque.servicio_pagos.model.Tarjeta;

import java.util.List;

class ClienteTest {

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente("123456", "juan@example.com", "Juan Pérez");
    }

    @Test
    void testIdentificacion() {
        assertEquals("123456", cliente.getIdentificacion(), "La identificación debe coincidir");
    }

    @Test
    void testEmail() {
        assertEquals("juan@example.com", cliente.getEmail(), "El email debe coincidir");
    }

    @Test
    void testNombre() {
        assertEquals("Juan Pérez", cliente.getNombre(), "El nombre debe coincidir");
    }

    @Test
    void testEstadoPorDefecto() {
        assertEquals("ACTIVO", cliente.getEstado(), "El estado inicial debe ser ACTIVO");
    }

    @Test
    void testModificarEstado() {
        cliente.setEstado("INACTIVO");
        assertEquals("INACTIVO", cliente.getEstado(), "El estado debe actualizarse correctamente");
    }

    @Test
    void testTarjetasInicialmenteVacias() {
        assertTrue(cliente.getTarjetas().isEmpty(), "La lista de tarjetas debe estar vacía al inicio");
    }

    @Test
    void testAgregarTarjeta() {
        Tarjeta tarjeta = new Tarjeta();
        cliente.getTarjetas().add(tarjeta);
        assertFalse(cliente.getTarjetas().isEmpty(), "La lista de tarjetas no debe estar vacía después de agregar una");
    }
}