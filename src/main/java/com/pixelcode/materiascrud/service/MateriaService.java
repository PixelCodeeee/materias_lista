package com.pixelcode.materiascrud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pixelcode.materiascrud.entity.MateriaEntity;
import com.pixelcode.materiascrud.repository.MateriaRepository;

@Service
public class MateriaService {

    @Autowired
    private MateriaRepository materiaRepository;

    public List<MateriaEntity> getAllMaterias() {
        return materiaRepository.findAll();
    }

}
