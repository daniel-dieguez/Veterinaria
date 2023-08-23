package com.example.veterinaria.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.implementation.bind.annotation.Empty;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class DuenosDTO implements Serializable {
    @NotEmpty(message = "Recuerda que el campo debe de estar lleno ")
    private String dueno;

}
