package com.sublimado.service;

import com.sublimado.model.Cliente;

import java.util.List;

public interface IClienteService {

    void guardarCliente(Cliente cliente) throws Exception;
    Cliente traerCliente(Long idCliente) throws Exception;
    List<Cliente> traerClientes();
    void actualizarCliente(Cliente cliente) throws Exception;
    void eliminarCliente(Long idCliente);
}
