package com.example.cuu_ho_tech.Domain.Response;

import java.io.Serializable;

public class ServiceResponse implements Serializable {
    private String name_service, img_service;
    private double price_service;


    public ServiceResponse(String name_service, String img_service, double price_service) {
        this.name_service = name_service;
        this.img_service = img_service;
        this.price_service = price_service;
    }

    public String getName_service() {
        return name_service;
    }

    public void setName_service(String name_service) {
        this.name_service = name_service;
    }

    public String getImg_service() {
        return img_service;
    }

    public void setImg_service(String img_service) {
        this.img_service = img_service;
    }

    public double getPrice_service() {
        return price_service;
    }

    public void setPrice_service(double price_service) {
        this.price_service = price_service;
    }
}
