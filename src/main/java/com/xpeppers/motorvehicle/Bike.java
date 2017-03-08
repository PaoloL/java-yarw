/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xpeppers.motorvehicle;

/**
 *
 * @author maverick
 */
public class Bike {
    
    private String plate;
    private String brand;
    private String model;
    private int wheels;

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getWheels() {
        return wheels;
    }

    public void setWheels(int wheels) {
        this.wheels = wheels;
    }

     public Bike() {
        this.plate = null;
        this.model = null;
        this.brand = null;
        this.wheels = 0;
    }
    
    public Bike(String plate) {
        this.plate = plate;
        this.model = null;
        this.brand = null;
        this.wheels = 0;
    }
    
    
    
}
