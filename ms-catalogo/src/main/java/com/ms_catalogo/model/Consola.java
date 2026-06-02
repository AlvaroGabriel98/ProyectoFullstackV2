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
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private String codigoModelo;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private BigDecimal precio;

    @Column(nullable = false)
    private Integer capacidadAlmacenamiento; // Ej: 1024 (para 1TB)
}