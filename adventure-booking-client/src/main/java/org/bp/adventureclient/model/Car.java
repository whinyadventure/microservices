package org.bp.adventureclient.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Car {

    protected String brand;
    protected String model;
    protected String registrationPlate;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    protected Date from;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    protected Date to;


    public String getBrand() {
        return brand;
    }


    public void setBrand(String value) {
        this.brand = value;
    }


    public String getModel() {
        return model;
    }


    public void setModel(String value) {
        this.model = value;
    }


    public String getRegistrationPlate() {
        return registrationPlate;
    }


    public void setRegistrationPlate(String value) {
        this.registrationPlate = value;
    }


    public Date getFrom() {
        return from;
    }


    public void setFrom(Date value) {
        this.from = value;
    }


    public Date getTo() {
        return to;
    }


    public void setTo(Date value) {
        this.to = value;
    }

}
