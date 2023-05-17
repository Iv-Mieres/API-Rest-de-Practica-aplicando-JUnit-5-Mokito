package com.sublimado.service;

import com.sublimado.model.Cliente;
import com.sublimado.model.Venta;
import com.sublimado.repository.IVentaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VentaServiceTest {

    @InjectMocks
    private VentaService ventaService;

    @Mock
    private IVentaRepository ventaRepository;

    @Test
    void testGuardarVenta() throws Exception {
        Cliente cliente = new Cliente();
        Venta venta = Venta.builder()
                .precio(1200.0)
                .fecha(LocalDate.now())
                .cliente(cliente)
                .build();

        when(ventaRepository.save(venta)).thenReturn(venta);
        ventaService.saveVenta(venta);
        verify(ventaRepository).save(venta);
    }

    @Test
    void verificarQueAlGuardarLaVentaElClienteNoSeaNull(){
        Venta venta = new Venta();
        Exception ex = assertThrows(Exception.class, () -> ventaService.saveVenta(venta));
        assertEquals("La venta no puede estar vacia", ex.getMessage());
    }

    @Test
    void verificarQueTraigaLaVenta() throws Exception {
        Venta venta = Venta.builder()
                .idVenta(1L)
                .precio(1200.0)
                .fecha(LocalDate.now())
                .build();
        when(ventaRepository.findById(1L)).thenReturn(Optional.ofNullable(venta));
        ventaService.traerVenta(1L);
        assertEquals(venta,  ventaService.traerVenta(1L));
    }

    @Test
    void verificarQueLanzeLaExceptionSiElIdNoExiste() throws Exception {
        when(ventaRepository.findById(1L)).thenReturn(null);
        assertThrows(Exception.class, () -> ventaService.traerVenta(1L));
    }

    @Test
    void verificarQueMeMuestreLaLista(){
        Venta venta = Venta.builder()
                .idVenta(1L)
                .precio(1200.0)
                .fecha(LocalDate.now())
                .build();
        Venta venta2 = Venta.builder()
                .idVenta(2L)
                .precio(1400.0)
                .fecha(LocalDate.now())
                .build();
        var ventas = new ArrayList<Venta>();
        ventas.add(venta);
        ventas.add(venta2);

        when(ventaRepository.findAll()).thenReturn(List.of(venta, venta2));
        assertEquals(ventas,  ventaService.traerVentas());

    }

    @Test
    void verificarQueActualiceLaVentaSiElIdExiste() throws Exception {
        Venta ventaActualizada = Venta.builder()
                .idVenta(1L)
                .precio(1300.0)
                .fecha(LocalDate.now())
                .build();
        when(ventaRepository.save(ventaActualizada)).thenReturn(ventaActualizada);
        when(ventaRepository.existsById(ventaActualizada.getIdVenta())).thenReturn(true);
        ventaService.updateVenta(ventaActualizada);
        verify(ventaRepository).save(ventaActualizada);
    }

    @Test
    void verificarQueLanceUnaExceptionSiElIdNoExiste() throws Exception {
        Venta ventaActualizada = Venta.builder()
                .idVenta(1L)
                .precio(1300.0)
                .fecha(LocalDate.now())
                .build();
        when(ventaRepository.existsById(ventaActualizada.getIdVenta())).thenReturn(false);
        assertThrows(Exception.class, () -> ventaService.updateVenta(ventaActualizada));
    }


}