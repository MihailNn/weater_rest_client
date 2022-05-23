package com.aston.mihail.rest.entity;

public class Weather {
    private String temp;

    public Weather() {
    }

    public Weather(String temp) {
        this.temp = temp;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }
}
