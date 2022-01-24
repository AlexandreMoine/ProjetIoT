package com.polytech.iot;

import com.polytech.iot.Controller.TauxGazController;
import com.polytech.iot.utilitaire.GazTask;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Timer;
import java.util.TimerTask;

@SpringBootApplication
public class IotApplication {

    public static void main(String[] args) {
        SpringApplication.run(IotApplication.class, args);
    }
}
