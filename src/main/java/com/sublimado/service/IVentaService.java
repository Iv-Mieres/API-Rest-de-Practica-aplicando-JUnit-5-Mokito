package com.sublimado.service;

import com.sublimado.model.Venta;

import java.util.List;
import java.util.Optional;

public interface IVentaService {

    void saveVenta(Venta venta) throws Exception;
    Venta traerVenta(Long idVenta) throws Exception;
    List<Venta> traerVentas();
    void updateVenta(Venta venta) throws Exception;
    void deleteVenta(Long idVenta);
}
