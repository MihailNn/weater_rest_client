package com.aston.mihail.rest.entity;

public class City {
    private String region;

    public City(String region) {
        this.region = region;
    }

    public City() {
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
