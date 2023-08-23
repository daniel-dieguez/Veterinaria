package com.example.veterinaria.models.entitis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.implementation.bind.annotation.Empty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "pagofinal" )
@Entity
public class PagoFinal implements Serializable {

    @Id
    @Column(name = "tarjeta")
    private String tarjeta;
    @Column(name = "monto")
    private String monto;
    @Column(name = "cobro")
    private String cobro;
    @Column(name = "medicamento")
    private String medicamento;
    @Column(name = "dueno")
    private String dueno;
}
