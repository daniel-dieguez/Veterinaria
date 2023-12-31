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
@Table(name = "mascota") //Para dirigir la tabla
@Entity //para mapear las columnas de las tablas
public class Mascota implements Serializable {
    @Id
    @Column(name = "dueno")
    private String dueno;
    @Column(name = "nombre_mascota")
    private String nombre_mascota ;
    @Column(name = "tipo_mascota")
    private String tipo_mascota ;
    @Column(name = "anos")
    private String  anos;
    @Column(name = "vacunado" )
    private String vacunado ;
    @Column(name = "zona_vivienda")
    private String  zona_vivienda;
    @Column(name = "numero_dueno")
    private String numero_dueno ;
}
