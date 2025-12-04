package com.pixelcode.materias_service.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pixelcode.materias_service.entity.MateriaEntity;
import com.pixelcode.materias_service.service.MateriaService;

@RestController
@RequestMapping("/api/materias")
@CrossOrigin(origins = "*")
public class MateriaController {

    @Autowired
    private MateriaService materiaService;

    /**
     * Obtener todas las materias
     * GET /api/materias
     */
    @GetMapping
    public ResponseEntity<List<MateriaEntity>> getAllMaterias() {
        try {
            List<MateriaEntity> materias = materiaService.getAllMaterias();
            if (materias.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(materias, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Obtener solo materias activas
     * GET /api/materias/activas
     */
    @GetMapping("/activas")
    public ResponseEntity<List<MateriaEntity>> getMateriasActivas() {
        try {
            List<MateriaEntity> materias = materiaService.getMateriasActivas();
            if (materias.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(materias, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Obtener una materia por ID
     * GET /api/materias/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<MateriaEntity> getMateriaById(@PathVariable Integer id) {
        try {
            Optional<MateriaEntity> materia = materiaService.getMateriaById(id);
            return materia.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Crear una nueva materia
     * POST /api/materias
     */
    @PostMapping
    public ResponseEntity<MateriaEntity> createMateria(@RequestBody MateriaEntity materia) {
        try {
            MateriaEntity nuevaMateria = materiaService.createMateria(materia);
            return new ResponseEntity<>(nuevaMateria, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Actualizar una materia existente
     * PUT /api/materias/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<MateriaEntity> updateMateria(
            @PathVariable Integer id, 
            @RequestBody MateriaEntity materiaDetails) {
        try {
            MateriaEntity materiaActualizada = materiaService.updateMateria(id, materiaDetails);
            return new ResponseEntity<>(materiaActualizada, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Eliminar lógicamente una materia (soft delete)
     * DELETE /api/materias/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<MateriaEntity> deleteMateria(@PathVariable Integer id) {
        try {
            MateriaEntity materiaEliminada = materiaService.deleteMateria(id);
            return new ResponseEntity<>(materiaEliminada, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Reactivar una materia
     * PATCH /api/materias/{id}/reactivar
     */
    @PatchMapping("/{id}/reactivar")
    public ResponseEntity<MateriaEntity> reactivarMateria(@PathVariable Integer id) {
        try {
            MateriaEntity materiaReactivada = materiaService.reactivarMateria(id);
            return new ResponseEntity<>(materiaReactivada, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Eliminar permanentemente una materia (hard delete)
     * DELETE /api/materias/{id}/permanente
     */
    @DeleteMapping("/{id}/permanente")
    public ResponseEntity<HttpStatus> deleteMateriaPermanente(@PathVariable Integer id) {
        try {
            materiaService.deleteMateriaPermanente(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}