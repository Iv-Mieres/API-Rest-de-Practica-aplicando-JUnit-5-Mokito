package com.sublimado.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class VentaTest {

    private Venta venta;

    @BeforeEach
    void setUp() {
        venta = new Venta();
        venta.setIdVenta(1L);
        venta.setFecha(LocalDate.now());
        venta.setPrecio(1500.0);
    }

    @Test
    void testComprobarClaseNotNull(){
        Venta venta1 = new Venta();
        assertNotNull(venta1);
    }

    @Test
    void testDescontarPrecio() throws Exception {
        double precioObtenido = venta.descontarPrecio(500);
        assertEquals(1000, precioObtenido);
    }

    @Test
    void testLanzarExceptionCuandoElResultadoEsNegativo() throws Exception {
    }

    @Test
    void testSumarPorcentajePrecio() {
        double precioObtenido = venta.sumarPorcentajePrecio(0.5);
        assertEquals(2250, precioObtenido);
    }
}