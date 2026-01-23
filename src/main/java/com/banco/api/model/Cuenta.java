package com.banco.api.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cuentas")
public class Cuenta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true, length = 20)
    private String numeroCuenta;
    
    @Column(nullable = false, length = 20)
    private String tipoCuenta;
    
    @Column(nullable = false)
    private Double saldoInicial;
    
    @Column(nullable = false)
    private Boolean estado = true;
    
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;
}
