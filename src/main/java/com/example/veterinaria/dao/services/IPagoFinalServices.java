package com.example.veterinaria.dao.services;

import com.example.veterinaria.models.entitis.PagoFinal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPagoFinalServices {

    public List<PagoFinal> findAll();
    public Page<PagoFinal> findAll(Pageable pageable);
    public PagoFinal findById(String pagofinalId);
    public PagoFinal save(PagoFinal pagoFinal);
    public void delete(PagoFinal pagoFinal);
}
