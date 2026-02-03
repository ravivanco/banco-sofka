package com.banco.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.banco.api.model.Movimiento;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {

    List<Movimiento> findByCuentaId(Long cuentaId);
}
