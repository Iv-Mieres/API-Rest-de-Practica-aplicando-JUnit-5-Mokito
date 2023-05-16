package com.sublimado.service;

import com.sublimado.model.Venta;

import java.util.List;

public interface IVentaService {

    void saveVenta(Venta venta) throws Exception;
    Venta getVenta(Long idVenta) throws Exception;
    List<Venta> ventas();
    void updateVenta(Venta venta) throws Exception;
    void deleteVenta(Long idVenta);
}
