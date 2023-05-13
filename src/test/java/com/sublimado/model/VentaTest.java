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
        double precioEsperado = 1000.0;
        assertEquals(precioEsperado, precioObtenido);
    }

    @Test
    void testLanzarExceptionCuandoElResultadoEsNegativo() throws Exception {
        assertThrows(Exception.class, () -> venta.descontarPrecio(3000));
    }

    @Test
    void testSumarPorcentajePrecio() {
        double precioObtenido = venta.sumarPorcentajePrecio(0.5);
        double precioEsperado = 2250.0;
        assertEquals(precioEsperado, precioObtenido);
    }
}