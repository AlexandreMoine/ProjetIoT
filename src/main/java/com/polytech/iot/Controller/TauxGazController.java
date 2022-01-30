package com.polytech.iot.Controller;

import com.polytech.iot.Domain.TauxGazEntity;
import com.polytech.iot.Service.SeuilService;
import com.polytech.iot.Service.TauxGazService;
import com.polytech.iot.utilitaire.GazTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Timer;

import java.util.List;
import java.util.TimerTask;

@RestController
@CrossOrigin
@RequestMapping("/tauxGaz")
public class TauxGazController{

    @Autowired
    private TauxGazService tauxGazService;
    private TauxGazEntity tauxGazEntity;
    private Date date;

    @GetMapping("/getTauxGaz")
    public Integer getTauxGaz() {
        return (Integer)this.tauxGazService.getTauxGaz();
    }

    @GetMapping("/enregistrerTauxGaz")
    public void enregistrerTauxGaz(){
        Timer timer;
        timer = new Timer();
        timer.schedule(new GazTask(), 1000, 5000); //en milli seconde
    }

    @GetMapping("/getListeTauxGaz")
    public List<TauxGazEntity> getListTauxGaz() {
        return this.tauxGazService.getListTauxGaz();
    }
}
