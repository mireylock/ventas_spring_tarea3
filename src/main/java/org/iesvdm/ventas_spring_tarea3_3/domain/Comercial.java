package org.iesvdm.ventas_spring_tarea3_3.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Comercial {
    private int id;
    private String nombre;
    private String apellido;
    private String apellido2;
    private  float comision;

}
