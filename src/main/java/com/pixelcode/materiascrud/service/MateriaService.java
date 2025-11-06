package com.pixelcode.materiascrud.service;

import java.util.List;
import java.util.Optional;

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

    public Optional<MateriaEntity> getMateriaById(Integer id) {
        return materiaRepository.findById(id);
    }

    public MateriaEntity createMateria(MateriaEntity materia) {
        return materiaRepository.save(materia);
    }

    public MateriaEntity updateMateria(Integer id, MateriaEntity materiaDetails) {
        MateriaEntity materia = materiaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Materia no encontrada con id: " + id));

        materia.setNombre(materiaDetails.getNombre());
        materia.setCodigo(materiaDetails.getCodigo());
        materia.setCuatrimestre(materiaDetails.getCuatrimestre());
        materia.setActivo(materiaDetails.isActivo());

        return materiaRepository.save(materia);
    }
}
