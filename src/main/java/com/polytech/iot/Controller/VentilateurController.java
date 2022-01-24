package com.polytech.iot.Controller;

import com.polytech.iot.Service.SeuilService;
import com.polytech.iot.Service.VentilateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/ventilateur")
public class VentilateurController {

    @Autowired
    private VentilateurService ventilateurService;

    @PostMapping("/activateVenti")
    public void activateVenti(@RequestBody boolean venti) {
        this.ventilateurService.modifierVenti(venti);
    }
}
