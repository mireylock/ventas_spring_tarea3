package org.iesvdm.ventas_spring_tarea3_3.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Pedido {
    private int id;
    private double total;
    private Date fecha;
}
