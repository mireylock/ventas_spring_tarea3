package org.iesvdm.ventas_spring_tarea3.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.iesvdm.ventas_spring_tarea3.domain.Cliente;
import org.iesvdm.ventas_spring_tarea3.domain.Pedido;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class ComercialDTO {
    //Atributos de Comercial
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
    @DecimalMin(value="0.256", message = "{msg.valid.min.value}")
    @DecimalMax(value="0.946", message = "{msg.valid.max.value}")
    private BigDecimal comision;

    //El DTO añade
    private long totalPedidosComercial;
    private double mediaPedidosComercial;
    private Pedido pedidoMaximoComercial;
    private Pedido pedidoMinimoComercial;
    private List<Pedido> listaPedidosComercial;
    private Map<Cliente, Double> totalPorClienteOrdenado;

}
