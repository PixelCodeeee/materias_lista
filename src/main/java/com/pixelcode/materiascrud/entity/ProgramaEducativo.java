package com.pixelcode.materiascrud.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "programa_educativo")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ProgramaEducativo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;
    private Boolean activo;

    @OneToMany(mappedBy = "programaEducativo")
    @JsonIgnoreProperties("programaEducativo")
    private List<MateriaEntity> materias;
    
}
