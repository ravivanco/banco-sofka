package com.banco.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.banco.api.model.Cuenta;
import com.banco.api.service.CuentaService;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    private final CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @GetMapping
    public List<Cuenta> listarCuentas() {
        return cuentaService.listarTodas();
    }

    @PostMapping("/cliente/{clienteId}")
    public Cuenta crearCuenta(
            @PathVariable Long clienteId,
            @RequestBody Cuenta cuenta
    ) {
        return cuentaService.crearCuenta(clienteId, cuenta);
    }
}
