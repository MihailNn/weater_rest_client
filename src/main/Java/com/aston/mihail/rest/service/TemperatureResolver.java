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
    private String url = "https://api.openweathermap.org/data/2.5/weather?appid=e4f07dd763eee655eb375cb2ebe41131&units=metric&q=Minsk";

    public Weather getTemperature(City city){
        Weather weather = new Weather();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.set("appid", "e4f07dd763eee655eb375cb2ebe41131");
//        headers.set("units", "metric");
//        String cityName = city.getСity();
        //wrap of HTTP  response
//        ResponseEntity<String> responseEntity = ResponseEntity.ok().headers(headers).body("cityNumber");
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        String temperetJson = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(temperetJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String temerature = jsonNode.path("main").path("temp").asText();

        return weather;
    }
}
