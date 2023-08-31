package com.example.veterinaria.Test;

import com.example.veterinaria.models.entitis.Mascota;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MascotaTest {

    @Test
    public void testMascotaConstructor() {
        Mascota mascota = new Mascota();
        assertNotNull(mascota);
    }

    @Test
    public void testGettersAndSetters() {
        Mascota mascota = new Mascota();

        mascota.setDueno("John");
        mascota.setNombre_mascota("Fido");
        mascota.setTipo_mascota("Perro");
        mascota.setAnos("3");
        mascota.setVacunado("Sí");
        mascota.setZona_vivienda("Jardín");
        mascota.setNumero_dueno("123456789");

        assertEquals("John", mascota.getDueno());
        assertEquals("Fido", mascota.getNombre_mascota());
        assertEquals("Perro", mascota.getTipo_mascota());
        assertEquals("3", mascota.getAnos());
        assertEquals("Sí", mascota.getVacunado());
        assertEquals("Jardín", mascota.getZona_vivienda());
        assertEquals("123456789", mascota.getNumero_dueno());
    }



}