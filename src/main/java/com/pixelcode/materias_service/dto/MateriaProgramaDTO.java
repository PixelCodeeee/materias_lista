package com.pixelcode.materias_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for assigning materias to programas educativos
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MateriaProgramaDTO {
    private Integer id;
    private Integer materiaId;
    private Long programaEducativoId;
    private String nombreMateria;
    private String nombreProgramaEducativo;
    private Boolean activo;
}