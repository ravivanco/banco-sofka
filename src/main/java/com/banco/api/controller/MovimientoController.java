package com.banco.api.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.banco.api.model.Movimiento;
import com.banco.api.service.MovimientoService;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    private final MovimientoService movimientoService;

    public MovimientoController(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @PostMapping("/cuenta/{cuentaId}")
    public Movimiento registrarMovimiento(
            @PathVariable Long cuentaId,
            @RequestBody Movimiento movimiento) {
        return movimientoService.registrarMovimiento(cuentaId, movimiento);
    }

    @GetMapping("/cuenta/{cuentaId}")
    public List<Movimiento> obtenerMovimientosPorCuenta(
            @PathVariable Long cuentaId) {
        return movimientoService.listarPorCuenta(cuentaId);
    }
}
