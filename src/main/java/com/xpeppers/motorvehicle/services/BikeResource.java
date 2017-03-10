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

@Path("bikes")
public class BikeResource {

    private static AmazonDynamoDBClient client;
    
    @Context
    private UriInfo context;

    public BikeResource() {
    }
    
    public BikeResource(AmazonDynamoDBClient client) {
        this.client = client;
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBikes(@PathParam("id") String id) 
    {
        System.out.println("GET /bikes/"+id);
        //Mapping resource on DynamoDB table
        DynamoDBMapper db_mapper = new DynamoDBMapper(client);
        BikeMapper bike = new BikeMapper();
        try {
            bike = db_mapper.load(BikeMapper.class, id);
            //The GET method means retrieve whatever information is identified by the Request-URI.
            return Response.status(200).entity(bike).build();
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
    public Response putBikes(@PathParam("id") String id, String content) throws IOException 
    {
            System.out.println("PUT /bikes/"+id);
            //Mapping resource on DynamoDB table
            DynamoDBMapper db_mapper = new DynamoDBMapper(client);
            ObjectMapper obj_mapper = new ObjectMapper();
            BikeMapper bike = obj_mapper.readValue(content, BikeMapper.class);
            bike.setId(id);
            try {
                db_mapper.save(bike);
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
