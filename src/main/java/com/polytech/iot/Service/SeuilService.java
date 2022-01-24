package com.polytech.iot.Service;

import com.polytech.iot.Domain.SeuilEntity;
import com.polytech.iot.Repository.SeuilRepository;
import com.polytech.iot.utilitaire.ConnexionHTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeuilService {

    @Autowired
    private SeuilRepository seuilRepository;
    @Autowired
    private ConnexionHTTP connexionHTTP;

    public Object getSeuil(){
        try {
            return connexionHTTP.getSeuil();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void modifierSeuil(SeuilEntity seuilEntity){
        this.seuilRepository.save(seuilEntity);
        try {
            connexionHTTP.setSeuil(seuilEntity.getValeur());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
