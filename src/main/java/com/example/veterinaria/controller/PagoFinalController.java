package com.example.veterinaria.controller;


import com.example.veterinaria.dao.services.IPagoFinalServices;
import com.example.veterinaria.models.entitis.PagoFinal;
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

@RestController
@RequestMapping(value = "/vaterinaria/v3/pagofinal")
public class PagoFinalController {

    @Autowired
    private IPagoFinalServices iPagoFinalServices;

    private Logger logger = LoggerFactory.getLogger(PagoFinalController.class);

    @GetMapping
    public ResponseEntity<?> ListaPagoFinal(){
        Map<String, Object> response = new HashMap<>();
        this.logger.debug("Se esta iniciando el proceso de lectura");
        try{
            List<PagoFinal> pagoFinals = this.iPagoFinalServices.findAll();
            if(pagoFinals == null && pagoFinals.isEmpty()){
                logger.warn("No existe una conexxion a la entidad de pago final");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }else{
                logger.info("Se ejecutara");
                return new ResponseEntity<List<PagoFinal>>(pagoFinals, HttpStatus.OK);
            }

        }catch(CannotCreateTransactionException e){
            logger.error("Error al momento de comunicarse con la base de datos");
            response.put("mensajes", "error al momento de contextarse a la base de datos");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);
        }catch (DataAccessException e){
            logger.error("Error al momento de comunicarse con la base de datos");
            response.put("mensajes", "error al momento de contextarse a la base de datos");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);

        }
    }

}
