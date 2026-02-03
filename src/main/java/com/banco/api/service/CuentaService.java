package com.banco.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.banco.api.model.Cliente;
import com.banco.api.model.Cuenta;
import com.banco.api.repository.ClienteRepository;
import com.banco.api.repository.CuentaRepository;

@Service
public class CuentaService {

    private final CuentaRepository cuentaRepository;
    private final ClienteRepository clienteRepository;

    public CuentaService(CuentaRepository cuentaRepository, ClienteRepository clienteRepository) {
        this.cuentaRepository = cuentaRepository;
        this.clienteRepository = clienteRepository;
    }

    public List<Cuenta> listarTodas() {
        return cuentaRepository.findAll();
    }

    public Cuenta crearCuenta(Long clienteId, Cuenta cuenta) {
        Cliente cliente = clienteRepository.findById(clienteId)
            .orElseThrow(() -> new RuntimeException("Cliente no existe"));

        cuenta.setCliente(cliente);
        return cuentaRepository.save(cuenta);
    }
}
