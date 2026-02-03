package com.banco.api.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.banco.api.exception.SaldoNoDisponibleException;
import com.banco.api.model.Cuenta;
import com.banco.api.model.Movimiento;
import com.banco.api.repository.CuentaRepository;
import com.banco.api.repository.MovimientoRepository;

@Service
public class MovimientoService {

    private final MovimientoRepository movimientoRepository;
    private final CuentaRepository cuentaRepository;

    public MovimientoService(
            MovimientoRepository movimientoRepository,
            CuentaRepository cuentaRepository) {
        this.movimientoRepository = movimientoRepository;
        this.cuentaRepository = cuentaRepository;
    }

    public Movimiento registrarMovimiento(Long cuentaId, Movimiento movimiento) {

        Cuenta cuenta = cuentaRepository.findById(cuentaId)
                .orElseThrow(() -> new RuntimeException("Cuenta no existe"));

        double nuevoSaldo = cuenta.getSaldoInicial() + movimiento.getValor();

        // REGLA F3
        if (nuevoSaldo < 0) {
            throw new SaldoNoDisponibleException("Saldo no disponible");
        }

        // ✔ Actualizar saldo
        cuenta.setSaldoInicial(nuevoSaldo);
        cuentaRepository.save(cuenta);

        // ✔ Registrar movimiento
        movimiento.setFecha(LocalDate.now());
        movimiento.setSaldo(nuevoSaldo);
        movimiento.setCuenta(cuenta);

        return movimientoRepository.save(movimiento);
    }

    public List<Movimiento> listarPorCuenta(Long cuentaId) {
        return movimientoRepository.findByCuentaId(cuentaId);
    }
}
