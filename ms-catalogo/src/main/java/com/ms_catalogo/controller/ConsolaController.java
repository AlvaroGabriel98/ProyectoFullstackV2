package com.ms_catalogo.controller;
import com.example.ms_catalogo.model.Consola;
import com.example.ms_catalogo.repository.ConsolaRepository;
import com.example.ms_catalogo.service.ConsolaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/consolas")
public class ConsolaController {

    @Autowired
    private ConsolaService consolaService;

    @GetMapping
    public List<Consola> listarConsolas(){
        return consolaService.getConsolas();
    }

    @PostMapping
    public Consola agregarConsola(@RequestBody Consola consola){
        return consolaService.saveConsola(consola);
    }

    @GetMapping("{id}")
    public Consola buscarConsola(@PathVariable Integer id){
        return consolaService.getConsolaId(id);
    }

    @PutMapping("{id}")
    public Consola actualizarConsola(@PathVariable Integer id, @RequestBody Consola consola){
        return consolaService.updateConsola(consola);
    }

    @DeleteMapping("{id}")
    public String eliminarConsola(@PathVariable Integer id){
        return consolaService.deleteConsola(id);
    }

    @GetMapping("/total")
    public Integer totalConsolas(){
        return consolaService.totalConsolas();
    }
}