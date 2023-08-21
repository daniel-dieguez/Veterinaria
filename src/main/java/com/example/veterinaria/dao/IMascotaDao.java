package com.example.veterinaria.dao;

import com.example.veterinaria.models.entitis.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMascotaDao extends JpaRepository<Mascota, String> {

}
