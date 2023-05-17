package com.sublimado.service;

import com.sublimado.model.Venta;
import com.sublimado.repository.IVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
public class VentaService implements IVentaService {

    @Autowired
    private IVentaRepository ventaRepository;

    @Override
    public void saveVenta(Venta venta) throws Exception {
        if(Objects.isNull(venta.getCliente())){
            throw new Exception("La venta debe contener un cliente");
        }
        ventaRepository.save(venta);
    }

    @Override
    public Venta traerVenta(Long idVenta) throws Exception {
        return ventaRepository.findById(idVenta)
                .orElseThrow(() -> new Exception("El id ingresado no ha sido encontrado"));
    }

    @Override
    public List<Venta> traerVentas() {
        return ventaRepository.findAll();
    }

    @Override
    public void updateVenta(Venta venta) throws Exception {
        if(!ventaRepository.existsById(venta.getIdVenta())){
            throw new Exception("Ingrese un id valido");
        }
        ventaRepository.save(venta);
    }

    @Override
    public void deleteVenta(Long idVenta) {
          ventaRepository.deleteById(idVenta);
    }
}
