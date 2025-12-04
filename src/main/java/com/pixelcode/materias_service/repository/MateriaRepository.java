package com.pixelcode.materias_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pixelcode.materias_service.entity.MateriaEntity;

public interface MateriaRepository extends JpaRepository<MateriaEntity, Integer> {

}
