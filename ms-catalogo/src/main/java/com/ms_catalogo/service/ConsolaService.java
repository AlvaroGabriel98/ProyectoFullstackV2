package com.ms_catalogo.service;
import com.ms_catalogo.model.Consola;
import com.ms_catalogo.repository.ConsolaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsolaService {
    @Autowired
    private ConsolaRepository consolaRepository;

    public List<Consola> getConsolas() {
        return consolaRepository.obtenerConsolas();
    }

    public Consola saveConsola(Consola consola){
        return consolaRepository.guardar(consola);
    }

    public Consola getConsolaId(Integer id){
        return consolaRepository.buscarPorId(id);
    }

    public Consola updateConsola(Consola consola){
        return consolaRepository.actualizar(consola);
    }

    public String deleteConsola(Integer id){
        consolaRepository.eliminar(id);
        return "Consola eliminada";
    }

    public Integer totalConsolas(){
        return consolaRepository.obtenerConsolas().size();
    }
}