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

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="Cars")
public class CarMapper {
    
    private String id;
    private String plate;
    private String brand;
    private String model;
    private int wheel;
    
    @DynamoDBHashKey(attributeName="Id")
    public String getId() { return id;}
    public void setId(String id) {this.id = id;}
    
    @DynamoDBAttribute(attributeName="plate")  
    public String getPlate() { return plate;}
    public void setPlate(String plate) {this.plate = plate;}
    
    @DynamoDBAttribute(attributeName="brand")  
    public String getBrand() {return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    
    @DynamoDBAttribute(attributeName="model")  
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    
    @DynamoDBAttribute(attributeName = "wheel")
    public int getWheel() { return wheel; }
    public void setWheel(int wheel) { this.wheel = wheel; }
    
}