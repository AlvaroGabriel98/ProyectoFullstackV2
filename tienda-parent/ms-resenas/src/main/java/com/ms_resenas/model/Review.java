package com.ms_resenas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "resenas")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resena")
    private Integer id;

    @NotBlank(message = "El titulo es obligatorio")
    @Size(max = 120, message = "El titulo no puede superar los 120 caracteres")
    @Column(nullable = false, length = 120)
    private String titulo;

    @NotBlank(message = "El comentario es obligatorio")
    @Size(max = 500, message = "El comentario no puede superar los 500 caracteres")
    @Column(nullable = false, length = 500)
    private String comentario;

    @Min(value = 1, message = "La puntuacion minima es 1")
    @Max(value = 5, message = "La puntuacion maxima es 5")
    @Column(nullable = false)
    private Integer puntuacion;
}
