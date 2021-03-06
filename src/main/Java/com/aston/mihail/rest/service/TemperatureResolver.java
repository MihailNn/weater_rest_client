package com.aston.mihail.rest.service;

import com.aston.mihail.rest.entity.City;
import com.aston.mihail.rest.entity.Weather;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TemperatureResolver {
    @Autowired
    RestTemplate restTemplate;

    public Weather getTemperature(City city){
        String url = "https://api.openweathermap.org/data/2.5/weather?appid=e4f07dd763eee655eb375cb2ebe41131&units=metric&q=";
        Weather weather = new Weather();
        url = url + city.getRegion();
        //invoke the API and get the response as JSON string
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        String temperetJson = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(temperetJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
//        "main":{
//            "temp":306.15, //current temperature
//                    "pressure":1013,
//                    "humidity":44,
//                    "temp_min":306, //min current temperature in the city
//                    "temp_max":306 //max current temperature in the city
//        }
        String temperature = jsonNode.path("main").path("temp").asText();
        weather.setTemp(temperature);

        return weather;
    }
}
