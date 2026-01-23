package com.banco.api.controller;

import com.banco.api.model.Movimiento;
import com.banco.api.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {
    
    @Autowired
    private MovimientoService movimientoService;
    
    @GetMapping
    public ResponseEntity<List<Movimiento>> obtenerTodos() {
        return ResponseEntity.ok(movimientoService.obtenerTodos());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Movimiento> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(movimientoService.obtenerPorId(id));
    }
    
    @PostMapping
    public ResponseEntity<Movimiento> crear(@RequestBody Movimiento movimiento) {
        return new ResponseEntity<>(movimientoService.crear(movimiento), HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Movimiento> actualizar(@PathVariable Long id, @RequestBody Movimiento movimiento) {
        return ResponseEntity.ok(movimientoService.actualizar(id, movimiento));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        movimientoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
