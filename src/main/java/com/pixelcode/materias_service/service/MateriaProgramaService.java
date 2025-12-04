package com.pixelcode.materias_service.service;

import com.pixelcode.materias_service.dto.MateriaProgramaDTO;
import com.pixelcode.materias_service.entity.MateriaEntity;
import com.pixelcode.materias_service.entity.MateriaProgramaEducativo;
import com.pixelcode.materias_service.repository.MateriaRepository;
import com.pixelcode.materias_service.repository.MateriaProgramaEducativoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MateriaProgramaService {

    @Autowired
    private MateriaProgramaEducativoRepository materiaProgramaRepository;
    
    @Autowired
    private MateriaRepository materiaRepository;

    /**
     * Assign a materia to a programa educativo
     */
    @Transactional
    public MateriaProgramaDTO asignarMateriaAPrograma(Integer materiaId, Long programaEducativoId) {
        // Verify materia exists in this service
        MateriaEntity materia = materiaRepository.findById(materiaId)
                .orElseThrow(() -> new RuntimeException("Materia no encontrada con id: " + materiaId));
        
        // Check if relationship already exists
        if (materiaProgramaRepository.existsByMateriaIdAndProgramaEducativoId(materiaId, programaEducativoId)) {
            throw new RuntimeException("La materia ya está asignada a este programa educativo");
        }
        
        // Create relationship
        MateriaProgramaEducativo relacion = new MateriaProgramaEducativo();
        relacion.setMateriaId(materiaId);
        relacion.setProgramaEducativoId(programaEducativoId);
        relacion.setNombreMateria(materia.getNombre());
        relacion.setActivo(true);
        
        // NOTE: In a real implementation, you would call division-service API
        // to get programa educativo name. For now, we leave it null or set a placeholder
        // relacion.setNombreProgramaEducativo(getProgramaEducativoName(programaEducativoId));
        
        MateriaProgramaEducativo saved = materiaProgramaRepository.save(relacion);
        
        return convertToDTO(saved);
    }

    /**
     * Get all materias for a programa educativo
     */
    public List<MateriaProgramaDTO> getMateriasDelPrograma(Long programaEducativoId) {
        List<MateriaProgramaEducativo> relaciones = 
                materiaProgramaRepository.findByProgramaEducativoIdAndActivoTrue(programaEducativoId);
        
        return relaciones.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Get all programas educativos for a materia
     */
    public List<MateriaProgramaDTO> getProgramasDeLaMateria(Integer materiaId) {
        List<MateriaProgramaEducativo> relaciones = 
                materiaProgramaRepository.findByMateriaIdAndActivoTrue(materiaId);
        
        return relaciones.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Get all relationships
     */
    public List<MateriaProgramaDTO> getAllRelaciones() {
        return materiaProgramaRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Get only active relationships
     */
    public List<MateriaProgramaDTO> getRelacionesActivas() {
        return materiaProgramaRepository.findByActivoTrue().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Remove a materia from a programa (soft delete)
     */
    @Transactional
    public void removerMateriaDePrograma(Integer materiaId, Long programaEducativoId) {
        MateriaProgramaEducativo relacion = materiaProgramaRepository
                .findByMateriaIdAndProgramaEducativoId(materiaId, programaEducativoId)
                .orElseThrow(() -> new RuntimeException("Relación no encontrada"));
        
        relacion.setActivo(false);
        materiaProgramaRepository.save(relacion);
    }

    /**
     * Delete relationship permanently
     */
    @Transactional
    public void eliminarRelacionPermanente(Integer materiaId, Long programaEducativoId) {
        if (!materiaProgramaRepository.existsByMateriaIdAndProgramaEducativoId(materiaId, programaEducativoId)) {
            throw new RuntimeException("Relación no encontrada");
        }
        materiaProgramaRepository.deleteByMateriaIdAndProgramaEducativoId(materiaId, programaEducativoId);
    }

    /**
     * Update materia names in junction table (call after updating materia)
     */
    @Transactional
    public void actualizarNombreMateria(Integer materiaId, String nuevoNombre) {
        List<MateriaProgramaEducativo> relaciones = 
                materiaProgramaRepository.findByMateriaId(materiaId);
        
        relaciones.forEach(r -> {
            r.setNombreMateria(nuevoNombre);
            materiaProgramaRepository.save(r);
        });
    }

    /**
     * Convert entity to DTO
     */
    private MateriaProgramaDTO convertToDTO(MateriaProgramaEducativo entity) {
        MateriaProgramaDTO dto = new MateriaProgramaDTO();
        dto.setId(entity.getId());
        dto.setMateriaId(entity.getMateriaId());
        dto.setProgramaEducativoId(entity.getProgramaEducativoId());
        dto.setNombreMateria(entity.getNombreMateria());
        dto.setNombreProgramaEducativo(entity.getNombreProgramaEducativo());
        dto.setActivo(entity.getActivo());
        return dto;
    }
}