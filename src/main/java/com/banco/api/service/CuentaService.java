package com.banco.api.service;

import com.banco.api.exception.RecursoNoEncontradoException;
import com.banco.api.model.Cliente;
import com.banco.api.model.Cuenta;
import com.banco.api.repository.ClienteRepository;
import com.banco.api.repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentaService {
    
    @Autowired
    private CuentaRepository cuentaRepository;
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    public List<Cuenta> obtenerTodas() {
        return cuentaRepository.findAll();
    }
    
    public Cuenta obtenerPorId(Long id) {
        return cuentaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Cuenta no encontrada con id: " + id));
    }
    
    public Cuenta crear(Cuenta cuenta) {
        Cliente cliente = clienteRepository.findById(cuenta.getCliente().getClienteId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Cliente no encontrado"));
        cuenta.setCliente(cliente);
        return cuentaRepository.save(cuenta);
    }
    
    public Cuenta actualizar(Long id, Cuenta cuentaActualizada) {
        Cuenta cuenta = obtenerPorId(id);
        cuenta.setNumeroCuenta(cuentaActualizada.getNumeroCuenta());
        cuenta.setTipoCuenta(cuentaActualizada.getTipoCuenta());
        cuenta.setSaldoInicial(cuentaActualizada.getSaldoInicial());
        cuenta.setEstado(cuentaActualizada.getEstado());
        return cuentaRepository.save(cuenta);
    }
    
    public void eliminar(Long id) {
        Cuenta cuenta = obtenerPorId(id);
        cuentaRepository.delete(cuenta);
    }
}
