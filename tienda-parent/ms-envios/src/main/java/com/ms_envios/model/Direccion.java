package com.ms_envios.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "direcciones")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_direccion")
    private Long idDireccion;

    @NotBlank(message = "El nombre del cliente es obligatorio")
    @Column(nullable = false, length = 120)
    private String nombreCliente;

    @NotBlank(message = "La calle es obligatoria")
    @Size(max = 150, message = "La calle no puede superar los 150 caracteres")
    @Column(nullable = false, length = 150)
    private String calle;

    @NotBlank(message = "La ciudad es obligatoria")
    @Size(max = 100, message = "La ciudad no puede superar los 100 caracteres")
    @Column(nullable = false, length = 100)
    private String ciudad;

    @NotBlank(message = "La region es obligatoria")
    @Size(max = 100, message = "La region no puede superar los 100 caracteres")
    @Column(nullable = false, length = 100)
    private String region;

    @NotBlank(message = "El pais es obligatorio")
    @Size(max = 100, message = "El pais no puede superar los 100 caracteres")
    @Column(nullable = false, length = 100)
    private String pais;
}
