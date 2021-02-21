package com.ironhack.statsservice.controller.impl;

import com.ironhack.statsservice.controller.interfaces.*;
import com.ironhack.statsservice.service.interfaces.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.math.*;
import java.util.*;

@RestController
public class StatsController implements IStatsController {

//    Tenemos un problema, este servicio no tiene bases de datos, por lo tanto, no se pueden hacer sql queries desde aquí,
//    por lo tanto, solo sirve para pedir las estadísticas a los clientes, que entonces tendrían que tener una ruta que
//    entregase cada una de estas estadísticas, y entonces, lo único que haría este microservicio sería repetir esas rutas,
//    por lo que no sería muy util. Entonces, no sería más eficiente símplemente crear en opportunities y en account las
//    rutas que devuelvan las estadísticas que saquen en sus repositories y llamarlas directamente desde ahí?

    @Autowired
    private IStatsService iStatsService;

    @GetMapping("/stats/mean/{data}")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal getMean(@PathVariable String data){
        return iStatsService.getMean(data);
    }

    @GetMapping("/stats/max/{data}")
    @ResponseStatus(HttpStatus.OK)
    public List<Object[]> getMax(String data) {
        return iStatsService.getMax(data);
    }

    @GetMapping("/stats/min/{data}")
    @ResponseStatus(HttpStatus.OK)
    public List<Object[]> getMin(String data) {
        return iStatsService.getMin(data);
    }

    @GetMapping("/stats/median/{data}")
    @ResponseStatus(HttpStatus.OK)
    public double getMedian(String data) {
        return iStatsService.getMedian(data);
    }
}
