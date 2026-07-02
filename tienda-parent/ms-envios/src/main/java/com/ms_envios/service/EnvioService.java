package com.ms_envios.service;

import com.ms_envios.exception.ResourceNotFoundException;
import com.ms_envios.model.Envio;
import com.ms_envios.repository.EnvioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnvioService {

    private final EnvioRepository envioRepository;

    public List<Envio> getEnvios() {
        return envioRepository.findAll();
    }

    public Envio saveEnvio(Envio envio) {
        envio.setIdEnvio(null);
        if (envio.getEstado() == null || envio.getEstado().isBlank()) {
            envio.setEstado("PENDIENTE");
        }
        return envioRepository.save(envio);
    }

    public Envio getEnvioId(Long id) {
        return envioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontro el envio con ID: " + id));
    }

    public List<Envio> getEnviosPorCliente(Long idCliente) {
        return envioRepository.findByIdCliente(idCliente);
    }

    public List<Envio> getEnviosPorEstado(String estado) {
        return envioRepository.findByEstadoIgnoreCase(estado);
    }

    public Envio updateEnvio(Long id, Envio envio) {
        Envio existente = getEnvioId(id);
        existente.setIdCliente(envio.getIdCliente());
        existente.setDireccion(envio.getDireccion());
        existente.setTelefono(envio.getTelefono());
        existente.setNumeroSeguimiento(envio.getNumeroSeguimiento());
        existente.setEstado(envio.getEstado());
        return envioRepository.save(existente);
    }

    public Envio cambiarEstado(Long id, String estado) {
        Envio existente = getEnvioId(id);
        existente.setEstado(estado.toUpperCase());
        return envioRepository.save(existente);
    }

    public void deleteEnvio(Long id) {
        Envio existente = getEnvioId(id);
        envioRepository.delete(existente);
    }

    public long totalEnvios() {
        return envioRepository.count();
    }
}
