package com.example.veterinaria.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MascotaDTO implements Serializable {
    @NotEmpty(message = "el campo no debe de ser vacio")
    private String mascota;
}
