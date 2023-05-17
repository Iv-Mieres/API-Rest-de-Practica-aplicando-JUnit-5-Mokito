package com.sublimado.service;

import com.sublimado.model.Cliente;
import com.sublimado.repository.IClienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private IClienteRepository clienteRepository;

    @Test
    void verificarQueElClienteHaSidoGuardado() throws Exception {
        Cliente cliente = Cliente.builder()
                .idCliente(1L)
                .email("cliente@mail.com")
                .dni("30494822")
                .build();
        when(clienteRepository.save(cliente)).thenReturn(cliente);
        clienteService.guardarCliente(cliente);
        verify(clienteRepository).save(cliente);
    }

    @Test
    void lanzarUnaExceptionSiElEmailIngresadoYaExisteEnLaBD() throws Exception {
        Cliente cliente = Cliente.builder()
                .idCliente(1L)
                .email("cliente@mail.com")
                .dni("30494822")
                .build();
        when(clienteRepository.existsByEmail(cliente.getEmail())).thenReturn(true);
        assertThrows(Exception.class, () ->  clienteService.guardarCliente(cliente));
    }

    @Test
    void lanzarUnaExceptionSiElDniIngresadoYaExisteEnLaBD() throws Exception {
        Cliente cliente = Cliente.builder()
                .idCliente(1L)
                .email("cliente@mail.com")
                .dni("30494822")
                .build();
        when(clienteRepository.existsByDni(cliente.getDni())).thenReturn(true);
        assertThrows(Exception.class, () ->  clienteService.guardarCliente(cliente));
    }

}