package com.ms_catalogo.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
    private Long idConsola;

    @NotBlank(message = "El nombre de la consola es obligatorio")
    @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nombre;

    @NotBlank(message = "La marca es obligatoria")
    @Size(max = 80, message = "La marca no puede superar los 80 caracteres")
    @Column(nullable = false, length = 80)
    private String marca;

    @Size(max = 255, message = "La descripcion no puede superar los 255 caracteres")
    @Column(length = 255)
    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private BigDecimal precio;

    @Column(nullable = false)
    private Integer capacidadAlmacenamiento; // Ej: 1024 (para 1TB)
}