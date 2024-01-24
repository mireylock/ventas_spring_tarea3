package org.iesvdm.ventas_spring_tarea3.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.iesvdm.ventas_spring_tarea3.domain.Pedido;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class ComercialDTO {
    //Atributos de Comercial
    private int id;
    private String nombre;
    private String apellido;
    private String apellido2;
    private float comision;

    //El DTO añade
    private long totalPedidosComercial;
    private double mediaPedidosComercial;
    private List<Pedido> pedidoMaximoComercial;
    private List<Pedido> pedidoMinimoComercial;
}
