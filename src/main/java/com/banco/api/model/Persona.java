package com.banco.api.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@MappedSuperclass
public class Persona {
    
    @Column(nullable = false, length = 100)
    private String nombre;
    
    @Column(nullable = false, length = 10)
    private String genero;
    
    @Column(nullable = false)
    private Integer edad;
    
    @Column(nullable = false, unique = true, length = 20)
    private String identificacion;
    
    @Column(length = 200)
    private String direccion;
    
    @Column(length = 20)
    private String telefono;
}
