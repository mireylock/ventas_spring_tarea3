package org.iesvdm.ventas_spring_tarea3.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.iesvdm.ventas_spring_tarea3.domain.Cliente;
import org.iesvdm.ventas_spring_tarea3.domain.Comercial;

import java.util.Map;

@Data
@AllArgsConstructor
public class ClienteDTO {
    private int id;
    private String nombre;
    private String apellido;
    private String apellido2;
    private String ciudad;
    private int categoria;

    //El DTO añade
    //la lista de comerciales asociados junto con el conteo de pedidos por comercial,
    // número de pedidos realizados  en el último trimestre, semestre, año, y lustro para el cliente en cuestión.
    private Map<Comercial, Long> comercialesConteoPedidos;
    private Long pedidosTrimestre;
    private Long pedidosSemestre;
    private Long pedidosAnio;
    private Long pedidosLustro;

}
