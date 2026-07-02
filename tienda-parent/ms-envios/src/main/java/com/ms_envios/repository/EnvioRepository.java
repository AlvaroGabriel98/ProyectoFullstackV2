package com.ms_envios.repository;

import com.ms_envios.model.Envio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnvioRepository extends JpaRepository<Envio, Long> {
    List<Envio> findByIdCliente(Long idCliente);
    List<Envio> findByEstadoIgnoreCase(String estado);
}
