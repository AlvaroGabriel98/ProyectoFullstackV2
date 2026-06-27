package com.ms_catalogo.repository;
import com.ms_catalogo.model.Consola;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ConsolaRepository {
    private List<Consola> listaConsolas = new ArrayList<>();

    public ConsolaRepository() {
        listaConsolas.add(new Consola());
    }

    public List<Consola> obtenerConsolas() {
        return listaConsolas;
    }

    public Consola buscarPorId(Integer idConsola) {
        for (Consola consola : listaConsolas) {
            if (consola.getIdConsola().equals(idConsola)) {
                return consola;
            }
        }
        return null;
    }

    public Consola guardar(Consola consola) {
        if (buscarPorId(consola.getIdConsola()) == null) {
            listaConsolas.add(consola);
            return consola;
        }
        return null;
    }

    public Consola actualizar(Consola consola) {
        Integer id = 0;
        Integer idPosicion = 0;

        for (Integer i = 0; i < listaConsolas.size(); i++) {
            if (listaConsolas.get(i).getIdConsola().equals(consola.getIdConsola())) {
                id = consola.getIdConsola();
                idPosicion = i;
            }
        }
        if (id!=0) {
            Consola consola1 = new Consola();
            consola1.setIdConsola(consola.getIdConsola());
            consola1.setNombre(consola.getNombre());
            consola1.setMarca(consola.getMarca());
            consola1.setDescripcion(consola.getDescripcion());
            consola1.setPrecio(consola.getPrecio());
            consola1.setCapacidadAlmacenamiento(consola.getCapacidadAlmacenamiento());
            listaConsolas.set(idPosicion, consola1);
            return consola1;
        }
        return null;
    }

    public void eliminar(Integer idConsola) {
        listaConsolas.remove(buscarPorId(idConsola));
    }

    public Integer totalConsolas() {
        return listaConsolas.size();
    }
}

