package com.example.veterinaria.controller;


import com.example.veterinaria.dao.services.IMascotasServices;
import com.example.veterinaria.models.dtos.MascotaDTO;
import com.example.veterinaria.models.entitis.Mascota;
import org.hibernate.exception.DataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController //esto nos sirva para poder agregar, get, put, delete.
@RequestMapping(value ="/veterinaria/v1/mascota" ) //Aqui ponemos la buena practiva la version, seria ejemplo 'nombredelapi/No.version/tablaausar/'
public class MascotaController { //Este serive para ver toda la logica de envio de informacio

    @Autowired
    private IMascotasServices iMascotasServices;

    private Logger logger = LoggerFactory.getLogger(MascotaController.class); //Ojo con el Logger pro que viene de una libreria especial

    @GetMapping
    public ResponseEntity<?> ListarMascota() {    //Aqui haremos una clase que nos ayuda para los '101''202''303'404' etc
        Map<String, Object> response = new HashMap<>();
        this.logger.debug("Iniciando el proceso de consultas de mascotas"); //el debug nos ayuda a cambiar el Sout
        try {
            List<Mascota> mascota = this.iMascotasServices.findAll();
            if (mascota == null && mascota.isEmpty()) {
                logger.warn("No existe registro para la entidad de la mascota");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                logger.info("Se ejecuto la consulta");
                return new ResponseEntity<List<Mascota>>(mascota, HttpStatus.OK);
            }
        } catch (CannotCreateTransactionException e) { //Este es para cuando el servidor no este funcionando, cuando una instancia no este conectada o una base de datos no este conecta
                response = this.getTransactionExepcion(response, e);
            //Este block ayuda pero lo hare cambio para tener mejor eficioencia en el codigo
            /*  logger.error("Error al momento de conectarse a la base de datos");
            response.put("mensajee", "error al moneotno de contectarse a la");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));*/
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);
        } catch (DataAccessException e) {
            response = this.getDataAccessException(response, e);

            //Al igual aqui
            /*logger.error("El error al momento de ejecutlar la consulta ea  la base d adatos");
            response.put("mensaje", "Error al momenot de ejecutar ola consulta a la base de datos");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage())); */
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);

        }

    }

    @GetMapping("/page/{page}") //Aqui ya podremos seleccionar que url qureremos agregar
    public ResponseEntity<?> ListarMascotasByPage(@PathVariable Integer page){ //este nos servira para mandar una nueva direccion
        Map<String, Object> response = new HashMap<>();
        try{
            Pageable pageable = PageRequest.of(page, 5); //Que me traiga 5 obejtos
            Page<Mascota> mascotaPage = iMascotasServices.findAll(pageable);
            if(mascotaPage == null || mascotaPage.getSize()== 0){
                logger.warn("No existe registro de la tabla de mascotas");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }else{
                logger.info("sE PROCEDE LA CONSULTA EXITOSAMENTE");
                return  new ResponseEntity<Page<Mascota>>(mascotaPage, HttpStatus.OK);
            }
        }catch (CannotCreateTransactionException e) {
            response = this.getTransactionExepcion(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);

        }catch  (DataAccessException e) {
            response = this.getDataAccessException(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);

        }

    }

    //Crearemos un get que envie y busque el Id
    @GetMapping("/{mascotaId}")
    public ResponseEntity<?> showMascota(@PathVariable String mascotaId){
        Map<String, Object> response = new HashMap<>();
        logger.debug("Inciamos proceso de busqueda de mascota ID".concat(mascotaId));
        try {
            Mascota mascota = this.iMascotasServices.findById(mascotaId);
            if(mascota == null){
                logger.warn("no existe el id de la mascota".concat(mascotaId));
                response.put("mensaje","no existe una mascota".concat(mascotaId));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            }else{
                logger.info("se procesara la busqueda de forma correcta");
                return new ResponseEntity<Mascota>(mascota, HttpStatus.OK);
            }

        }catch (CannotCreateTransactionException e) {
        response = this.getTransactionExepcion(response, e);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);

    }catch  (DataAccessException e) {
        response = this.getDataAccessException(response, e);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);

    }
    }

    // Vamos a creaer una respuesta nueva
    @PostMapping
    public ResponseEntity<?>create(@Valid @RequestBody MascotaDTO value, BindingResult result){ //Aqui lo utilizamos del DTO
        Map<String, Object> response = new HashMap<>();
        if(result.hasErrors() == true){
            /*uso de landas*/ List<String> errores = result.getFieldErrors().stream().map(error -> error.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("Errores",errores);
            logger.info("Se encuentraron errores al momento de  validar la informacion");
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);//El badreques para cuando al informacion esta mal

        }
        try{
            Mascota mascota = new Mascota();
            mascota.setDueno(value.getMascota());
            this.iMascotasServices.save(mascota);
            logger.info("Se creo el nombre de la nueva mascotata de forma exitosa");
            response.put("mensajes", "la mascota fue creado cone xito");
            response.put("Mascota", mascota);
            return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
        }catch (CannotCreateTransactionException e) {
            response = this.getTransactionExepcion(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);

        }catch  (DataAccessException e) {
            response = this.getDataAccessException(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);

        } // Estoy seguro que lo malo debe de ser la llamada a la base de datos (debe de ver un concepto mal ahi ver linea 125
    }

    @PutMapping("{mascotaId}")
    public ResponseEntity<?> update(@Valid @ResponseBody MascotaDTO value, BindingResult result, @PathVariable String mascotaId){
        Map<String, Object> response = new HashMap<>();
        if(result.hasErrors()){
            List<String> errores = result.getFieldErrors().stream().map(error -> error.getDefaultMessage()).collect(Collectors.toList());
            response.put("errores", errores);
            logger.info("Se encontraron erroes en la validacion");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        try {

        }catch (CannotCreateTransactionException e) {
            response = this.getTransactionExepcion(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);

        }catch  (DataAccessException e) {
            response = this.getDataAccessException(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);

        }
    }

    //Estas son las varaibles que utilizaremos para llamar y ya no escribir todo el codigo repetitivo
    private Map<String, Object> getTransactionExepcion(Map<String,Object> response, CannotCreateTransactionException e){
        logger.error("Error al momento de conectarse a la base de datos");
        response.put("mensajee", "error al moneotno de contectarse a la");
        response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
        return response;
    }

    private Map<String, Object> getDataAccessException(Map<String, Object> response, DataAccessException e){
        logger.error("El error al momento de ejecutlar la consulta ea  la base d adatos");
        response.put("mensaje", "Error al momenot de ejecutar ola consulta a la base de datos");
        response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
        return response;

    }


}
