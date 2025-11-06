package com.pixelcode.materiascrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pixelcode.materiascrud.entity.MateriaEntity;

public interface MateriaRepository extends JpaRepository<MateriaEntity, Integer> {

}
