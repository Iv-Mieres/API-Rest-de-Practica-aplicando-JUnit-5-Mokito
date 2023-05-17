package com.sublimado.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idVenta;
    private LocalDate fecha;
    private double precio;
    @ManyToOne
    @JoinColumn(name = "fkCliente")
    @JsonIgnoreProperties("ventas")
    private Cliente cliente;

    public double descontarPrecio(double descuento) throws Exception {
        double resultado = this.precio -= descuento;
        if(resultado < 0) {
            throw new Exception("El valor no puede ser menor a 0");
        }
        return resultado;
    }

    public double sumarPorcentajePrecio(double porcentaje){
        double resultado = this.precio * porcentaje;
        return this.precio += resultado;
    }

}
