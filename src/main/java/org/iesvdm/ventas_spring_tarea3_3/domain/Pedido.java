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
    //si le pones el objeto cliente y comercial, la consulta en bdd tiene que traérselños todos porque tiene id solo, no cliente entero
    private Cliente cliente;
    private Comercial comercial;
}
