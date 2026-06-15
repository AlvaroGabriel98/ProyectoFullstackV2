package com.ms_catalogo.repository;
import com.example.ms_catalogo.model.Consola;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ConsolaRepository {
    private List<Consola> listaConsolas = new ArrayList<>();

    public ConsolaRepository() {
        listaConsolas.add(new Consola(1,"Playstation 5","Sony","1TB", 599000,20));
    }

    public List<Consola> obtenerConsolas() {
        return listaConsolas;
    }

    public Consola buscarPorId(Integer id) {
        for (Consola consola : listaConsolas) {
            if (consola.getId().equals(id)) {
                return consola;
            }
        }
        return null;
    }

    public Consola guardar(Consola consola) {
        if (buscarPorId(consola.getId()) == null) {
            listaConsolas.add(consola);
            return consola;
        }
        return null;
    }

    public Consola actualizar(Consola consola) {
        Integer id = 0;
        Integer idPosicion = 0;

        for (Integer i = 0; i < listaConsolas.size(); i++) {
            if (listaConsolas.get(i).getId().equals(consola.getId())) {
                id = consola.getId();
                idPosicion = i;
            }
        }
        if (id!=0) {
            Consola consola1 = new Consola();
            consola1.setId(id);
            consola1.setNombre(consola.getNombre());
            consola1.setFabricante(consola.getFabricante());
            consola1.setAlmacenamiento(consola.getAlmacenamiento());
            consola1.setPrecio(consola.getPrecio());
            listaConsolas.set(idPosicion, consola1);
            return consola1;
        }
        return null;
    }

    public void eliminar(Integer id) {
        listaConsolas.remove(buscarPorId(id));
    }

    public Integer totalConsolas() {
        return listaConsolas.size();
    }


