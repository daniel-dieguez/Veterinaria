package com.example.veterinaria.dao.services;

import com.example.veterinaria.models.entitis.Mascota;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IMascotasServices {
    public List<Mascota> findAll(); //mostrara todos los registros
    public Page<Mascota>findAll(Pageable pageable);            //Mostrara todas las paginas
    public Mascota findById(String mascotaId); //buscar por id
    public Mascota save (Mascota mascota);      //para guardar
    public void delete(Mascota mascota);        //Elimina




}
