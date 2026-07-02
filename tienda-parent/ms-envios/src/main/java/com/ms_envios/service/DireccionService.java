package com.ms_envios.service;

import com.ms_envios.exception.ResourceNotFoundException;
import com.ms_envios.model.Direccion;
import com.ms_envios.repository.DireccionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DireccionService {

    private final DireccionRepository direccionRepository;

    public List<Direccion> listar() {
        return direccionRepository.findAll();
    }

    public Direccion crear(Direccion direccion) {
        direccion.setIdDireccion(null);
        return direccionRepository.save(direccion);
    }

    public Direccion buscarPorId(Long id) {
        return direccionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontro la direccion con ID: " + id));
    }

    public void eliminar(Long id) {
        direccionRepository.delete(buscarPorId(id));
    }
}
