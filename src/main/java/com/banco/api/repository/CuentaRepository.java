package com.banco.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.banco.api.model.Cuenta;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
}
