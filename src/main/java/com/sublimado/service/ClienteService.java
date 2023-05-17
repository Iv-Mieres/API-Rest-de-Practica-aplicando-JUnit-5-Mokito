package com.sublimado.service;

import com.sublimado.model.Cliente;
import com.sublimado.repository.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    private IClienteRepository clienteRepository;

    @Override
    public void guardarCliente(Cliente cliente) throws Exception {
        if (clienteRepository.existsByEmail(cliente.getEmail())){
            throw new Exception("El email ingresado ya se encuentra registrado. Ingrese un nuevo email");
        }
        if (clienteRepository.existsByDni(cliente.getDni())){
            throw new Exception("El dni ingresado ya se encuentra registrado. Ingrese un nuevo dni");
        }
        clienteRepository.save(cliente);
    }

    @Override
    public Cliente traerCliente(Long idCliente) throws Exception {
        return clienteRepository.findById(idCliente)
                .orElseThrow(() -> new Exception("El id ingresado no se encuentra registrado."));
    }

    @Override
    public List<Cliente> traerClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public void actualizarCliente(Cliente cliente) throws Exception {
        var clienteBd = this.traerCliente(cliente.getIdCliente());
        if (clienteBd.getEmail().equals(cliente.getEmail())){
            clienteRepository.save(cliente);
        }
        else{
            throw new Exception("El email no puede ser modificado");
        }
    }

    @Override
    public void eliminarCliente(Long idCliente) {
        clienteRepository.deleteById(idCliente);
    }
}
