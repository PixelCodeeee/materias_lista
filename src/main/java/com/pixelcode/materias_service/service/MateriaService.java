package com.pixelcode.materias_service.service;

import com.pixelcode.materias_service.entity.MateriaEntity;
import com.pixelcode.materias_service.repository.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MateriaService {

    @Autowired
    private MateriaRepository materiaRepository;

    /**
     * Obtener todas las materias
     */
    public List<MateriaEntity> getAllMaterias() {
        return materiaRepository.findAll();
    }

    /**
     * Obtener solo materias activas
     */
    public List<MateriaEntity> getMateriasActivas() {
        return materiaRepository.findAll().stream()
                .filter(MateriaEntity::getActivo)
                .collect(Collectors.toList());
    }

    /**
     * Obtener una materia por ID
     */
    public Optional<MateriaEntity> getMateriaById(Integer id) {
        return materiaRepository.findById(id);
    }

    /**
     * Crear una nueva materia
     */
    @Transactional
    public MateriaEntity createMateria(MateriaEntity materia) {
        // Asegurar que tenga valores por defecto
        if (materia.getActivo() == null) {
            materia.setActivo(true);
        }
        return materiaRepository.save(materia);
    }

    /**
     * Actualizar una materia existente
     */
    @Transactional
    public MateriaEntity updateMateria(Integer id, MateriaEntity materiaDetails) {
        MateriaEntity materia = materiaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Materia no encontrada con id: " + id));
        
        materia.setNombre(materiaDetails.getNombre());
        materia.setDescripcion(materiaDetails.getDescripcion());
        materia.setActivo(materiaDetails.getActivo() != null ? materiaDetails.getActivo() : materia.getActivo());
        materia.setCreditos(materiaDetails.getCreditos());
        materia.setHorasSemanales(materiaDetails.getHorasSemanales());
        
        return materiaRepository.save(materia);
    }

    /**
     * Eliminar lógicamente una materia (soft delete)
     */
    @Transactional
    public MateriaEntity deleteMateria(Integer id) {
        MateriaEntity materia = materiaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Materia no encontrada con id: " + id));
        
        materia.setActivo(false);
        return materiaRepository.save(materia);
    }

    /**
     * Reactivar una materia
     */
    @Transactional
    public MateriaEntity reactivarMateria(Integer id) {
        MateriaEntity materia = materiaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Materia no encontrada con id: " + id));
        
        materia.setActivo(true);
        return materiaRepository.save(materia);
    }

    /**
     * Eliminar físicamente una materia (hard delete)
     */
    @Transactional
    public void deleteMateriaPermanente(Integer id) {
        if (!materiaRepository.existsById(id)) {
            throw new RuntimeException("Materia no encontrada con id: " + id);
        }
        materiaRepository.deleteById(id);
    }
}