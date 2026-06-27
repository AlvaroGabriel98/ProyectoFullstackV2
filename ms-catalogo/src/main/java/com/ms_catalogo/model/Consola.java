package com.ms_catalogo.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Consolas")

public class Consola {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_consola")
    private Integer idConsola;

    @NotNull(message = "El nombre de la consola es obligatorio")
    @Column(nullable = false, length = 100)
    private String nombre;

    @NotNull(message = "La marca es obligatoria")
    @Size(max = 80, message = "La marca no puede superar los 80 caracteres")
    @Column(nullable = false, length = 80)
    private String marca;

    @Size(max = 255, message = "La descripcion no puede superar los 255 caracteres")
    @NotNull(message = "La descripcion es obligatoria")
    private String descripcion;

    @Column(nullable = false)
    private BigDecimal precio;

    @Column(nullable = false)
    private Integer capacidadAlmacenamiento; // Ej: 1024 (para 1TB)
}