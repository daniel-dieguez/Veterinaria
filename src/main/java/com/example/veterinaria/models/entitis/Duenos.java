package com.example.veterinaria.models.entitis;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor //no dar un valor a una instancia
@Data   //Generar metodos
@Table(name = "duenos") //Para dirigir la tabla
@Entity //para mapear las columnas de las tablas
public class Duenos implements Serializable {

    @Id
    @Column(name = "dueno")
    private String duenos;
    @Column(name = "anos")
    private String anos ;
    @Column(name = "zona_vivienda")
    private String zona_vivienda;
    @Column(name = "numero_dueno")
    private String numero_dueno;
    @Column(name = "nombre_mascota")
    private String nombre_mascota;

}
