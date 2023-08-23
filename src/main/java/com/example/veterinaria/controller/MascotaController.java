package com.example.veterinaria.controller;


import com.example.veterinaria.dao.services.IMascotasServices;
import com.example.veterinaria.models.entitis.Mascota;
import org.hibernate.exception.DataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController //esto nos sirva para poder agregar, get, put, delete.
@RequestMapping(value ="/veterinaria/v1/mascota" ) //Aqui ponemos la buena practiva la version, seria ejemplo 'nombredelapi/No.version/tablaausar/'
public class MascotaController { //Este serive para ver toda la logica de envio de informacio

    @Autowired
    private IMascotasServices iMascotasServices;

    private Logger logger = LoggerFactory.getLogger(MascotaController.class); //Ojo con el Logger pro que viene de una libreria especial

    @GetMapping
    public ResponseEntity<?> ListarMascota(){    //Aqui haremos una clase que nos ayuda para los '101''202''303'404' etc
        Map<String, Object> response = new HashMap<>();
        this.logger.debug("Iniciando el proceso de consultas de mascotas"); //el debug nos ayuda a cambiar el Sout
        try{
                List<Mascota> mascota = this.iMascotasServices. findAll();
                if(mascota == null && mascota.isEmpty()){
                    logger.warn("No existe registro para la entidad de la mascota");
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }else{
                    logger.info("Se ejecuto la consulta");
                    return new ResponseEntity<List<Mascota>>(mascota, HttpStatus.OK);
                }
        }catch(CannotCreateTransactionException e){ //Este es para cuando el servidor no este funcionando, cuando una instancia no este conectada o una base de datos no este conecta
            logger.error("Error al momento de conectarse a la base de datos");
            response.put("mensajee","error al moneotno de contectarse a la");
            response.put("error",e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response,HttpStatus.SERVICE_UNAVAILABLE);
        }catch(DataAccessException e){
            logger.error("El error al momento de ejecutlar la consulta ea  la base d adatos");
            response.put("mensaje","Error al momenot de ejecutar ola consulta a la base de datos");
            response.put("error",e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response,HttpStatus.SERVICE_UNAVAILABLE);

        }

    }



}
