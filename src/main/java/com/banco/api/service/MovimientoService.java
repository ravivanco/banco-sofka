package com.banco.api.service;

import com.banco.api.exception.RecursoNoEncontradoException;
import com.banco.api.exception.SaldoNoDisponibleException;
import com.banco.api.model.Cuenta;
import com.banco.api.model.Movimiento;
import com.banco.api.repository.CuentaRepository;
import com.banco.api.repository.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class MovimientoService {
    
    @Autowired
    private MovimientoRepository movimientoRepository;
    
    @Autowired
    private CuentaRepository cuentaRepository;
    
    public List<Movimiento> obtenerTodos() {
        return movimientoRepository.findAll();
    }
    
    public Movimiento obtenerPorId(Long id) {
        return movimientoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Movimiento no encontrado con id: " + id));
    }
    
    @Transactional
    public Movimiento crear(Movimiento movimiento) {
        Cuenta cuenta = cuentaRepository.findById(movimiento.getCuenta().getId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Cuenta no encontrada"));
        
        // Calcular saldo actual de la cuenta
        List<Movimiento> movimientos = movimientoRepository.findAll();
        double saldoActual = cuenta.getSaldoInicial();
        
        for (Movimiento m : movimientos) {
            if (m.getCuenta().getId().equals(cuenta.getId())) {
                saldoActual = m.getSaldo();
            }
        }
        
        // Calcular nuevo saldo
        double nuevoSaldo = saldoActual + movimiento.getValor();
        
        // Validar saldo disponible (F3)
        if (nuevoSaldo < 0) {
            throw new SaldoNoDisponibleException("Saldo no disponible");
        }
        
        // Configurar movimiento
        movimiento.setCuenta(cuenta);
        movimiento.setFecha(LocalDate.now());
        movimiento.setSaldo(nuevoSaldo);
        
        // Determinar tipo de movimiento
        if (movimiento.getValor() > 0) {
            movimiento.setTipoMovimiento("Deposito de " + movimiento.getValor());
        } else {
            movimiento.setTipoMovimiento("Retiro de " + Math.abs(movimiento.getValor()));
        }
        
        return movimientoRepository.save(movimiento);
    }
    
    public Movimiento actualizar(Long id, Movimiento movimientoActualizado) {
        Movimiento movimiento = obtenerPorId(id);
        movimiento.setFecha(movimientoActualizado.getFecha());
        movimiento.setTipoMovimiento(movimientoActualizado.getTipoMovimiento());
        movimiento.setValor(movimientoActualizado.getValor());
        movimiento.setSaldo(movimientoActualizado.getSaldo());
        return movimientoRepository.save(movimiento);
    }
    
    public void eliminar(Long id) {
        Movimiento movimiento = obtenerPorId(id);
        movimientoRepository.delete(movimiento);
    }
}
