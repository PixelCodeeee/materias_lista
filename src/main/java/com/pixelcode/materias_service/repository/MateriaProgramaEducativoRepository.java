package com.pixelcode.materias_service.repository;

import com.pixelcode.materias_service.entity.MateriaProgramaEducativo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MateriaProgramaEducativoRepository extends JpaRepository<MateriaProgramaEducativo, Integer> {
    
    /**
     * Find all materias for a specific programa educativo
     */
    List<MateriaProgramaEducativo> findByProgramaEducativoId(Long programaEducativoId);
    
    /**
     * Find all programas educativos for a specific materia
     */
    List<MateriaProgramaEducativo> findByMateriaId(Integer materiaId);
    
    /**
     * Find specific relationship
     */
    Optional<MateriaProgramaEducativo> findByMateriaIdAndProgramaEducativoId(Integer materiaId, Long programaEducativoId);
    
    /**
     * Check if relationship exists
     */
    boolean existsByMateriaIdAndProgramaEducativoId(Integer materiaId, Long programaEducativoId);
    
    /**
     * Delete specific relationship
     */
    void deleteByMateriaIdAndProgramaEducativoId(Integer materiaId, Long programaEducativoId);
    
    /**
     * Find only active relationships
     */
    List<MateriaProgramaEducativo> findByActivoTrue();
    
    /**
     * Find active relationships for a programa
     */
    List<MateriaProgramaEducativo> findByProgramaEducativoIdAndActivoTrue(Long programaEducativoId);
    
    /**
     * Find active relationships for a materia
     */
    List<MateriaProgramaEducativo> findByMateriaIdAndActivoTrue(Integer materiaId);
}