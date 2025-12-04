package com.pixelcode.materias_service.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Entidad MateriaProgramaEducativo
 * Junction table para relación Many-to-Many entre Materia y ProgramaEducativo
 * 
 * MICROSERVICES PATTERN:
 * - This service owns the relationship table
 * - Stores IDs from both services (materias-service and division-service)
 * - No JPA @ManyToOne relationships - just foreign key IDs
 * - To get full data, make REST calls to division-service
 */
// ✅ CORRECT - Manages the many-to-many relationship
@Data
@Entity
@Table(name = "materia_programa_educativo")
public class MateriaProgramaEducativo {
  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    // ✅ Just the ID - no JPA relationship
    @Column(name = "materia_id", nullable = false)
    private Integer materiaId;
    
    // ✅ Just the ID - references division-service but no JPA relationship
    @Column(name = "programa_educativo_id", nullable = false)
    private Long programaEducativoId;
    
    // Cached names for display
    @Column(name = "nombre_materia", length = 200)
    private String nombreMateria;
    
    @Column(name = "nombre_programa_educativo", length = 200)
    private String nombreProgramaEducativo;
    
    @Column(name = "activo")
    private Boolean activo = true;
    
    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion;
    
    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;
}