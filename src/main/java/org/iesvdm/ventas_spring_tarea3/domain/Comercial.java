package org.iesvdm.ventas_spring_tarea3.domain;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comercial {
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

}
