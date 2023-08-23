package com.example.veterinaria.dao.services;

import com.example.veterinaria.models.entitis.Duenos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IDuenoServices {

    public List<Duenos> findAll();
    public Page<Duenos>findAll(Pageable pageable);
    public Duenos findById(String duenoId);
    public Duenos save(Duenos duenos);
    public void delete (Duenos duenos);
}
