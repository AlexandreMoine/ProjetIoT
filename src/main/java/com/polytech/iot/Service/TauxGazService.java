package com.polytech.iot.Service;

import com.polytech.iot.Domain.SeuilEntity;
import com.polytech.iot.Domain.TauxGazEntity;
import com.polytech.iot.Repository.TauxGazRepository;
import com.polytech.iot.mesExceptions.MonException;
import com.polytech.iot.utilitaire.ConnexionHTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TauxGazService {

    @Autowired
    private TauxGazRepository tauxGazRepository;
    @Autowired
    private ConnexionHTTP connexionHTTP;

    public Object getTauxGaz(){
        Object o;
        try {
            o = connexionHTTP.getTauxGaz();
            return o;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<TauxGazEntity> getListTauxGaz(){
        return this.tauxGazRepository.findAll();
    }

}
