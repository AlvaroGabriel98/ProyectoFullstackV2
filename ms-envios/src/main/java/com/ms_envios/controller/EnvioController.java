package com.ms_envios.controller;

import com.ms_envios.model.Envio;
import com.ms_envios.service.EnvioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/envios")
public class EnvioController {

    @Autowired
    private EnvioService envioService;

    @GetMapping
    public List<Envio> listarEnvios() {
        return envioService.getEnvios();
    }

    @PostMapping
    public Envio agregarEnvio(@RequestBody Envio envio) {
        return envioService.saveEnvio(envio);
    }

    @GetMapping("{envioId}")
    public Envio buscarEnvio(@PathVariable Long envioId) {
        return envioService.getEnvioId(envioId);
    }

    @DeleteMapping("{envioId}")
    public String eliminarEnvio(@PathVariable Long envioId) {
        return envioService.deleteEnvio(envioId);
    }

    @GetMapping("/total")
    public Integer obtenerTotalEnvios() {
        return envioService.totalEnvios();
    }


}
