package com.polytech.iot.Service;

import com.polytech.iot.utilitaire.ConnexionHTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentilateurService {

    @Autowired
    private ConnexionHTTP connexionHTTP;

    public void modifierVenti(boolean venti){
        try {
            connexionHTTP.setVenti(venti);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
