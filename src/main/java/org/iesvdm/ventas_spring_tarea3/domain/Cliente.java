package org.iesvdm.ventas_spring_tarea3.domain;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
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

    @Email(message = "{msg.valid.email}")
    private String email;

    @NotNull(message = "{msg.valid.not.null}")
    @NotBlank(message = "{msg.valid.not.empty}")
    @Size(max=50, message = "{msg.valid.max}")
    private String ciudad;

    @NotNull(message = "{msg.valid.not.null}")
    @Min(value = 100, message = "{msg.valid.min.value}")
    @Max(value = 1000, message = "{msg.valid.max.value}")
    private int categoria;


}
