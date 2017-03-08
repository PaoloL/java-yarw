/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xpeppers.motorvehicle.services;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xpeppers.motorvehicle.BikeMapper;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author maverick
 */
@Path("bikes")
public class BikeResource {

    private static AmazonDynamoDBClient client;
    
    @Context
    private UriInfo context;

    public BikeResource() {
    }
    
    public BikeResource(AmazonDynamoDBClient client) 
    {
        this.client = client;
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public BikeMapper getBikes(@PathParam("id") String id) 
    {
        System.out.println("GET /bikes/{id}");
        DynamoDBMapper db_mapper = new DynamoDBMapper(client);
        BikeMapper bike = new BikeMapper();
        try {
            bike = db_mapper.load(BikeMapper.class, id);
        }
        catch (AmazonServiceException ase) 
        {
            System.out.println(ase.getErrorMessage());
        }
        return bike;
    }
    
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void putBikes(@PathParam("id") String id, String content) 
    {
        try {
            System.out.println("PUT /bikes/{id}");
            DynamoDBMapper db_mapper = new DynamoDBMapper(client);
            ObjectMapper obj_mapper = new ObjectMapper();
            BikeMapper bike = obj_mapper.readValue(content, BikeMapper.class);
            bike.setId(id);
            System.out.println(content);
            try {
                db_mapper.save(bike);
            }
            catch (AmazonServiceException ase)
            {
                System.out.println(ase.getErrorMessage());
            }   
        }
        catch (IOException ex) 
        {
            Logger.getLogger(BikeResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
