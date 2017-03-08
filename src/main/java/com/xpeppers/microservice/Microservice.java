package com.xpeppers.microservice;
        
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.sun.net.httpserver.HttpServer;
import com.xpeppers.motorvehicle.services.BikeResource;
import com.xpeppers.motorvehicle.services.CarResource;
import java.net.URI;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;


public class Microservice  {
    
    static AmazonDynamoDBClient client = new AmazonDynamoDBClient(new ProfileCredentialsProvider("default"));
    
    public static void main(String[] args)
    {
        client.withRegion(Regions.EU_WEST_1);
        System.out.println("Starting Jersey REST-full Service with JDK HTTP Server ...");
        URI baseUri = UriBuilder.fromUri("http://localhost/v1").port(8080).build();
        ResourceConfig config = new ResourceConfig();
        config.register(new BikeResource(client));
        config.register(new CarResource(client));
        HttpServer server = JdkHttpServerFactory.createHttpServer(baseUri, config);
    }
}