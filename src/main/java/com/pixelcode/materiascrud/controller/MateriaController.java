package com.pixelcode.materiascrud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pixelcode.materiascrud.entity.MateriaEntity;
import com.pixelcode.materiascrud.service.MateriaService;

@RestController
@RequestMapping("/api/materias")
public class MateriaController {

    @Autowired
    private MateriaService materiaService;

    // Obtener todas las materias
    @GetMapping
    public ResponseEntity<List<MateriaEntity>> getAllMaterias() {
        List<MateriaEntity> materias = materiaService.getAllMaterias();
        return ResponseEntity.ok(materias);
    }

    // Obtener una materia por ID
    @GetMapping("/{id}")
    public ResponseEntity<MateriaEntity> getMateriaById(@PathVariable Integer id) {
        Optional<MateriaEntity> materia = materiaService.getMateriaById(id);
        return materia.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear una nueva materia
    @PostMapping
    public ResponseEntity<MateriaEntity> createMateria(@RequestBody MateriaEntity materia) {
        MateriaEntity nuevaMateria = materiaService.createMateria(materia);
        return ResponseEntity.ok(nuevaMateria);
    }

    // Actualizar una materia existente
    @PutMapping("/{id}")
    public ResponseEntity<MateriaEntity> updateMateria(@PathVariable Integer id, 
                                                     @RequestBody MateriaEntity materiaDetails) {
        try {
            MateriaEntity materiaActualizada = materiaService.updateMateria(id, materiaDetails);
            return ResponseEntity.ok(materiaActualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar lógicamente una materia
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMateria(@PathVariable Integer id) {
        try {
            MateriaEntity materia = materiaService.getMateriaById(id)
                .orElseThrow(() -> new RuntimeException("Materia no encontrada con id: " + id));
            
            materia.setActivo(false);
            materiaService.createMateria(materia);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
