package com.pixelcode.materias_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Entidad Materia
 * Representa una asignatura/materia del catálogo académico
 * 
 * NOTE: In microservices architecture, we don't use JPA relationships
 * across services. ProgramaEducativo is in division-service (port 8085).
 * The relationship is managed through MateriaProgramaEducativo join table.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "materia")
public class MateriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false, length = 200)
    private String nombre;
    
    @Column(length = 500)
    private String descripcion;
    
    @Column(nullable = false)
    private Boolean activo = true;
    
    @Column(name = "creditos")
    private Integer creditos;
    
    @Column(name = "horas_semanales")
    private Integer horasSemanales;
    
    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion;
    
    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;
    
    @PrePersist
    protected void onCreate() {
        fechaCreacion = LocalDateTime.now();
        fechaActualizacion = LocalDateTime.now();
        if (activo == null) {
            activo = true;
        }
    }
    
    @PreUpdate
    protected void onUpdate() {
        fechaActualizacion = LocalDateTime.now();
    }
}