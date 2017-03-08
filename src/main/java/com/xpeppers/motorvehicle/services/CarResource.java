/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xpeppers.motorvehicle.services;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author maverick
 */
@Path("generic")
public class CarResource {

    private static AmazonDynamoDBClient client;
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of cars
     */
    public CarResource() {
    }
    
    public CarResource(AmazonDynamoDBClient client) {
        this.client = client;
    }

    /**
     * Retrieves representation of an instance of com.xpeppers.motorvehicle.services.CarResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCars() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of CarResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putCars(String content) {
    }
}
