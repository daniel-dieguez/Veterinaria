package com.example.veterinaria.dao;

import com.example.veterinaria.models.entitis.Duenos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDuenoDao extends JpaRepository<Duenos, String> {
}
