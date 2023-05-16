package com.sublimado.service;

import com.sublimado.model.Cliente;
import com.sublimado.model.Venta;
import com.sublimado.repository.IVentaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
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

}