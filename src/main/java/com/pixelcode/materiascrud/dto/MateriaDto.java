package com.pixelcode.materiascrud.dto;

import lombok.Data;

@Data
public class MateriaDto {

    private Integer id;
    private String nombre;
    private String codigo;
    private boolean activo;
    private String cuatrimestre;
    private Long programaId;
    private String programaNombre;

}
