package org.iesvdm.ventas_spring_tarea3.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteHasComercial {
    private Cliente cliente;
    private Comercial comercial;
    private LocalDate fechaAsociacion;
    private int prioridad;
}

