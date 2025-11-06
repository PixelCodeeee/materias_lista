package com.pixelcode.materiascrud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pixelcode.materiascrud.entity.MateriaEntity;
import com.pixelcode.materiascrud.service.MateriaService;

@RestController
@RequestMapping("/api/materias")
public class MateriaController {

     @Autowired
    private MateriaService materiaService;

    // Obtener todas las materias activas
    @GetMapping
    public ResponseEntity<List<MateriaEntity>> getAllMaterias() {
        List<MateriaEntity> materias = materiaService.getAllMaterias();
        return ResponseEntity.ok(materias);
    }

}
