package com.example.veterinaria.dao.services;

import com.example.veterinaria.dao.IDuenoDao;
import com.example.veterinaria.models.entitis.Duenos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DuenoServiceImp implements IDuenoServices{

    @Autowired
    private IDuenoDao iDuenoDao;

    @Override
    public List<Duenos> findAll() {
        return this.iDuenoDao.findAll();
    }

    @Override
    public Page<Duenos> findAll(Pageable pageable) {
        return this.iDuenoDao.findAll(pageable);
    }

    @Override
    public Duenos findById(String duenoId) {
        return this.iDuenoDao.findById(duenoId).orElse(null);
    }

    @Override
    public Duenos save(Duenos duenos) {
        return this.iDuenoDao.save(duenos);
    }

    @Override
    public void delete(Duenos duenos) {
        this.iDuenoDao.delete(duenos);

    }
}
