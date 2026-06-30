package com.ms_envios.model;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Envio {

     @Column(nullable = false)
     private Long idEnvio;

     @Column(nullable = false)
     private String idCliente;

     @Column(nullable = false)
     private String Direccion;

     @Column(nullable = false)
     private String telefono;

     @Column(nullable = false)
     private Integer numeroSeguimiento;

}
