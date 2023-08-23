package com.example.veterinaria.dao.services;

import com.example.veterinaria.dao.IDuenoDao;
import com.example.veterinaria.dao.IPagoFinalDao;
import com.example.veterinaria.models.entitis.PagoFinal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagoFinalServicesImpl implements IPagoFinalServices{

    @Autowired
    private IPagoFinalDao iPagoFinalDao;

    @Override
    public List<PagoFinal> findAll() {
        return this.iPagoFinalDao.findAll();
    }

    @Override
    public Page<PagoFinal> findAll(Pageable pageable) {
        return this.iPagoFinalDao.findAll(pageable);
    }

    @Override
    public PagoFinal findById(String pagofinalId) {
        return this.iPagoFinalDao.findById(pagofinalId).orElse(null);
    }

    @Override
    public PagoFinal save(PagoFinal pagoFinal) {
        return this.iPagoFinalDao.save(pagoFinal);
    }

    @Override
    public void delete(PagoFinal pagoFinal) {
        this.iPagoFinalDao.delete(pagoFinal);

    }
}
