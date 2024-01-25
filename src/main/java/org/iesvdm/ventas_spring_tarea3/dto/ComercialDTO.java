package org.iesvdm.ventas_spring_tarea3.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.iesvdm.ventas_spring_tarea3.domain.Cliente;
import org.iesvdm.ventas_spring_tarea3.domain.Pedido;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
    private Pedido pedidoMaximoComercial;
    private Pedido pedidoMinimoComercial;
    private Map<Cliente, Double> totalPorClienteOrdenado;

}
