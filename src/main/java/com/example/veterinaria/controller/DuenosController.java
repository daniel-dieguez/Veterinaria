package com.example.veterinaria.controller;


import com.example.veterinaria.dao.services.DuenoServiceImp;
import com.example.veterinaria.dao.services.IDuenoServices;
import com.example.veterinaria.models.entitis.Duenos;
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
@RequestMapping(value = "/veterinaria/v2/dueno")
public class DuenosController {

    @Autowired
    private IDuenoServices iDuenoServices;

    private Logger logger = LoggerFactory.getLogger(DuenosController.class);

    @GetMapping
    public ResponseEntity<?> ListaDueno() {
        Map<String, Object> response = new HashMap<>();
        this.logger.debug("Se esta iniciando el proceso de lectura");
        try {
            List<Duenos> duenos = this.iDuenoServices.findAll();
            if (duenos == null && duenos.isEmpty()) {
                logger.warn("No existe registro para la entidad de la dueños");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                logger.info("se ejecuta la consultaa");
                return new ResponseEntity<List<Duenos>>(duenos, HttpStatus.OK);
            }

        } catch (CannotCreateTransactionException e) {
            logger.error("Error al momento de comunicarse con la base de datos");
            response.put("mensajes", "error al momento de contextarse a la base de datos");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (DataAccessException e) {
            logger.error("error al momento de ejecutar la consulta a la base de datos");
            response.put("mensaje", "error al momento de ejecutar la consulta");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}
