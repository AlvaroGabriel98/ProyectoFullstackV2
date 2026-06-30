package com.ms_envios.service;

import com.ms_envios.model.Envio;
import com.ms_envios.repository.EnvioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnvioService {

    @Autowired
    private EnvioRepository envioRepository;

    public List<Envio> getEnvios() {
        return envioRepository.obtenerEnvios();
    }

    public Envio saveEnvio(Envio envio) {
        return envioRepository.guardarEnvio(envio);
    }

    public Envio getEnvioId(Long envioId) {
        return envioRepository.buscarPorIdEnvio(envioId);
    }

    public String deleteEnvio(Long envioId) {
        envioRepository.borrarEnvio(envioId);
        return "Envio Borrado";
    }

    public Integer totalEnvios() {
        return envioRepository.obtenerEnvios().size();
    }
}
