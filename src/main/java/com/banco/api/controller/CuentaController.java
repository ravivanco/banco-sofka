package com.banco.api.controller;

import com.banco.api.model.Cuenta;
import com.banco.api.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {
    
    @Autowired
    private CuentaService cuentaService;
    
    @GetMapping
    public ResponseEntity<List<Cuenta>> obtenerTodas() {
        return ResponseEntity.ok(cuentaService.obtenerTodas());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Cuenta> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(cuentaService.obtenerPorId(id));
    }
    
    @PostMapping
    public ResponseEntity<Cuenta> crear(@RequestBody Cuenta cuenta) {
        return new ResponseEntity<>(cuentaService.crear(cuenta), HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Cuenta> actualizar(@PathVariable Long id, @RequestBody Cuenta cuenta) {
        return ResponseEntity.ok(cuentaService.actualizar(id, cuenta));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        cuentaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
