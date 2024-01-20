package org.iesvdm.ventas_spring_tarea3.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Pedido {
    private int id;
    private double total;
    private Date fecha;
    //Al poner el objeto cliente y comercial, la consulta en bdd tiene que traérselos todos porque en bbdd tiene solo sus ids, no cliente/comercial enteros
    private Cliente Cliente;
    private Comercial comercial;
}
