package org.iesvdm.ventas_spring_tarea3.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.iesvdm.ventas_spring_tarea3.domain.Cliente;
import org.iesvdm.ventas_spring_tarea3.domain.Comercial;

import java.util.Map;

@Data
@AllArgsConstructor
public class ClienteDTO {
    private int id;

    @NotNull(message = "{msg.valid.not.null}")
    @NotBlank(message = "{msg.valid.not.empty}")
    @Size(max=30, message = "{msg.valid.max}")
    private String nombre;

    @NotNull(message = "{msg.valid.not.null}")
    @NotBlank(message = "{msg.valid.not.empty}")
    @Size (max=30, message = "{msg.valid.max}")
    private String apellido;

    private String apellido2;

    @NotNull(message = "{msg.valid.not.null}")
    @NotBlank(message = "{msg.valid.not.empty}")
    @Size(max=50, message = "{msg.valid.max}")
    private String ciudad;

    //TODO
    @NotNull(message = "{msg.valid.not.null}")
    @Min(value = 100, message = "{msg.valid.min.value}")
    @Max(value = 1000, message = "{msg.valid.max.value}")
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
