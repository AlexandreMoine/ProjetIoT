package com.polytech.iot.Controller;

import com.polytech.iot.Domain.SeuilEntity;
import com.polytech.iot.Service.SeuilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/seuil")
public class SeuilController {
    @Autowired
    private SeuilService seuilService;

    @GetMapping("/getSeuil")
    public Integer getSeuil() {
        return (Integer)this.seuilService.getSeuil();
    }

/*    @PostMapping("/modificationSeuil")
    public void modificationSeuil(@RequestBody int newSeuil) {
        this.seuilService.modifierSeuil(newSeuil);
    }
 */
    @PostMapping("/modificationSeuil")
    @ResponseBody
    public void modificationSeuil(@RequestBody SeuilEntity seuilEntity) {
        this.seuilService.modifierSeuil(seuilEntity);
    }
}