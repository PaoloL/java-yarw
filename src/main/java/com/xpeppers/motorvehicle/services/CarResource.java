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
import com.xpeppers.motorvehicle.CarMapper;
import java.io.IOException;
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
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author maverick
 * REF: https://www.w3.org/Protocols/rfc2616/rfc2616-sec9.html
 * */

@Path("cars")
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
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCars(@PathParam("id") String id) 
    {
        System.out.println("GET /cars/"+id);
        //Mapping resource on DynamoDB table
        DynamoDBMapper db_mapper = new DynamoDBMapper(client);
        CarMapper car = new CarMapper();
        try {
            car = db_mapper.load(CarMapper.class, id);
            //The GET method means retrieve whatever information is identified by the Request-URI.
            return Response.status(200).entity(car).build();
        }
        catch (AmazonServiceException ase) 
        {
            System.out.println(ase.getErrorMessage());
            return Response.status(500).entity("{\"Status\":\"Error\"}").build();
        }
    }
    
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putCars(@PathParam("id") String id, String content) throws IOException 
    {
            System.out.println("PUT /cars/"+id);
            //Mapping resource on DynamoDB table
            DynamoDBMapper db_mapper = new DynamoDBMapper(client);
            ObjectMapper obj_mapper = new ObjectMapper();
            CarMapper car = obj_mapper.readValue(content, CarMapper.class);
            car.setId(id);
            try {
                db_mapper.save(car);
                //If a new resource is created
                //the origin server MUST inform the user agent via the 201 response
                return Response.created(context.getAbsolutePath()).build();
            }
            catch (AmazonServiceException ase) {
                Logger.getLogger(ase.getErrorMessage());
                return Response.status(500).build();
            }  
    }
}
