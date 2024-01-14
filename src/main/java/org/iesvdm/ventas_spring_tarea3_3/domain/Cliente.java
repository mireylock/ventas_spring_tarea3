package org.iesvdm.ventas_spring_tarea3_3.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    private int id;
    private String nombre;
    private String apellido;
    private String apellido2;
    private String ciudad;
    private int categoria;
}
