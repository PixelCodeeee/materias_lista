package com.pixelcode.materias_service.controller;

import com.pixelcode.materias_service.dto.MateriaProgramaDTO;
import com.pixelcode.materias_service.service.MateriaProgramaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/materias-programas")
@CrossOrigin(origins = "*")
public class MateriaProgramaController {

    @Autowired
    private MateriaProgramaService materiaProgramaService;

    /**
     * Assign a materia to a programa educativo
     * POST /api/materias-programas
     * Body: { "materiaId": 1, "programaEducativoId": 1 }
     */
    @PostMapping
    public ResponseEntity<MateriaProgramaDTO> asignarMateriaAPrograma(
            @RequestBody Map<String, Object> request) {
        try {
            Integer materiaId = (Integer) request.get("materiaId");
            Long programaEducativoId = ((Number) request.get("programaEducativoId")).longValue();
            
            MateriaProgramaDTO relacion = materiaProgramaService.asignarMateriaAPrograma(
                    materiaId, programaEducativoId);
            
            return new ResponseEntity<>(relacion, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get all relationships
     * GET /api/materias-programas
     */
    @GetMapping
    public ResponseEntity<List<MateriaProgramaDTO>> getAllRelaciones() {
        try {
            List<MateriaProgramaDTO> relaciones = materiaProgramaService.getAllRelaciones();
            if (relaciones.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(relaciones, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get only active relationships
     * GET /api/materias-programas/activas
     */
    @GetMapping("/activas")
    public ResponseEntity<List<MateriaProgramaDTO>> getRelacionesActivas() {
        try {
            List<MateriaProgramaDTO> relaciones = materiaProgramaService.getRelacionesActivas();
            if (relaciones.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(relaciones, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get all materias for a specific programa educativo
     * GET /api/materias-programas/programa/{programaId}
     */
    @GetMapping("/programa/{programaId}")
    public ResponseEntity<List<MateriaProgramaDTO>> getMateriasDelPrograma(
            @PathVariable Long programaId) {
        try {
            List<MateriaProgramaDTO> materias = 
                    materiaProgramaService.getMateriasDelPrograma(programaId);
            
            if (materias.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(materias, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get all programas educativos for a specific materia
     * GET /api/materias-programas/materia/{materiaId}
     */
    @GetMapping("/materia/{materiaId}")
    public ResponseEntity<List<MateriaProgramaDTO>> getProgramasDeLaMateria(
            @PathVariable Integer materiaId) {
        try {
            List<MateriaProgramaDTO> programas = 
                    materiaProgramaService.getProgramasDeLaMateria(materiaId);
            
            if (programas.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(programas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Remove a materia from a programa (soft delete)
     * DELETE /api/materias-programas/materia/{materiaId}/programa/{programaId}
     */
    @DeleteMapping("/materia/{materiaId}/programa/{programaId}")
    public ResponseEntity<HttpStatus> removerMateriaDePrograma(
            @PathVariable Integer materiaId,
            @PathVariable Long programaId) {
        try {
            materiaProgramaService.removerMateriaDePrograma(materiaId, programaId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Delete relationship permanently
     * DELETE /api/materias-programas/materia/{materiaId}/programa/{programaId}/permanente
     */
    @DeleteMapping("/materia/{materiaId}/programa/{programaId}/permanente")
    public ResponseEntity<HttpStatus> eliminarRelacionPermanente(
            @PathVariable Integer materiaId,
            @PathVariable Long programaId) {
        try {
            materiaProgramaService.eliminarRelacionPermanente(materiaId, programaId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}