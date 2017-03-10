package com.xpeppers.microservice;
        
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.sun.net.httpserver.HttpServer;
import com.xpeppers.motorvehicle.services.BikeResource;
import com.xpeppers.motorvehicle.services.CarResource;
import com.xpeppers.motorvehicle.services.Ping;
import java.net.URI;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;


public class Microservice  {
    
    static AmazonDynamoDBClient client = new AmazonDynamoDBClient();
    
    public static void main(String[] args)
    {
        if (args[0].equals("local"))
        {
            System.out.println("Starting local version of microservices"
                    + "(Do you need a DynamoDB local)");
            System.out.println("http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/DynamoDBLocal.html");
            System.out.println("Starting Jersey REST-full Service with JDK HTTP Server ...");
            client.withEndpoint("http://localhost:8000");
        }
        else 
        {
            System.out.println("Starting Jersey REST-full Service with JDK HTTP Server ...");
            client.withRegion(Regions.EU_WEST_1);
        }
        URI baseUri = UriBuilder.fromUri("http://localhost/v1").port(8080).build();
        ResourceConfig config = new ResourceConfig();
        config.register(new Ping());
        config.register(new BikeResource(client));
        config.register(new CarResource(client));
        HttpServer server = JdkHttpServerFactory.createHttpServer(baseUri, config);
    }
}