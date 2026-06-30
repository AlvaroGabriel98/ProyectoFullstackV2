package com.ms_envios.repository;

import com.ms_envios.model.Envio;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EnvioRepository {
    private List<Envio> listaEnvios = new ArrayList<>();

    public EnvioRepository() {
        listaEnvios = new ArrayList<>();
    }

    public List<Envio> obtenerEnvios() {
        return listaEnvios;
    }

    public Envio buscarPorIdEnvio(Long idEnvio) {
        for (Envio envio : listaEnvios) {
            if (envio.getIdEnvio().equals(idEnvio)) {
                return envio;
            }
        }
        return null;
    }

    public Envio guardarEnvio(Envio envio) {
        if (buscarPorIdEnvio(envio.getIdEnvio()) == null) {
            listaEnvios.add(envio);
            return envio;
        }
        return null;
    }

    public void borrarEnvio(Long idEnvio) {
        listaEnvios.remove(buscarPorIdEnvio(idEnvio));
    }

    public Integer totalEnvios() {
        return listaEnvios.size();
    }


}
