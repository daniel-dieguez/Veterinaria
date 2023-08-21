package com.example.veterinaria.dao.services;

import com.example.veterinaria.dao.IMascotaDao;
import com.example.veterinaria.models.entitis.Mascota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //Importante colocar esta independecia, sean a todas las que llamen.
public class MascotaServicesImpl  implements IMascotasServices{

    @Autowired
    private IMascotaDao iMascotaDao; //aqui esta trae todas las instacias de los objetos de iMascotaDao


    @Override
    public List<Mascota> findAll() {
        return this.iMascotaDao.findAll(); //Aqui lo que hara es enlistara todas las instacias que se agregaron
    }

    @Override
    public Page<Mascota> findAll(Pageable pageable) {
        return this.iMascotaDao.findAll(pageable); //Aqui se empaginaran o se mostraran todas las paginas
    }

    @Override
    public Mascota findById(String mascotaId) {
        return this.iMascotaDao.findById(mascotaId).orElse(null);
        ///Esta dice que si encuentre el Id lo muestre, si en dado caso no lo encuentra sace 'null'
    }

    @Override
    public Mascota save(Mascota mascota) {
        return this.iMascotaDao.save(mascota);
    }

    @Override
    public void delete(Mascota mascota) {
    this.iMascotaDao.delete(mascota);
    }
}
