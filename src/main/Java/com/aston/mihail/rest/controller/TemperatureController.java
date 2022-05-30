package com.aston.mihail.rest.controller;

import com.aston.mihail.rest.entity.City;
import com.aston.mihail.rest.entity.Weather;
import com.aston.mihail.rest.service.TemperatureResolver;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TemperatureController {

    private final TemperatureResolver temperatureResolver;

    public TemperatureController(TemperatureResolver temperatureResolver) {
        this.temperatureResolver = temperatureResolver;
    }

    @PostMapping(value = "/temp", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Weather temperatureHandler(@RequestBody City city) {

        return temperatureResolver.getTemperature(city);
    }
}
