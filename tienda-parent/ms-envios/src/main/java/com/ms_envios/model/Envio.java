package com.ms_envios.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "envios")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Envio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_envio")
    private Long idEnvio;

    @NotNull(message = "El ID del cliente es obligatorio")
    @Column(name = "id_cliente", nullable = false)
    private Long idCliente;

    @NotBlank(message = "La direccion es obligatoria")
    @Size(max = 255, message = "La direccion no puede superar los 255 caracteres")
    @Column(nullable = false, length = 255)
    private String direccion;

    @NotBlank(message = "El telefono es obligatorio")
    @Size(max = 30, message = "El telefono no puede superar los 30 caracteres")
    @Column(nullable = false, length = 30)
    private String telefono;

    @NotNull(message = "El numero de seguimiento es obligatorio")
    @Column(name = "numero_seguimiento", nullable = false, unique = true)
    private Integer numeroSeguimiento;

    @NotBlank(message = "El estado del envio es obligatorio")
    @Size(max = 50, message = "El estado no puede superar los 50 caracteres")
    @Column(nullable = false, length = 50)
    private String estado;
}
