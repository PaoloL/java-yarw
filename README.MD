# java-yarw
Yet Another Restfull WebService with Jersey and Jackson

## Description
This is a skeleton of a simple RESTfull Web Services just for try Jersey and Jackson together with DynamoDB Tables with relative DynamoDBMapper POJO

### Java
All Java code is on com.xpeppers.* package
- com.xpeppers.microservice.Microservices.java is the Main Class
- com.xpeppers.microservice.motorVehicle package contain Bike and Car Class with relative DynamoDBMapper
- com.xpeppers.microservice.motorVehicle.service package contain Bike and Car Resource

### Jersey
In order to simplify development of RESTful Web services and their clients in Java, a standard and portable JAX-RS API has been designed. [Jersey](https://jersey.java.net/) RESTful Web Services framework is open source, production quality, framework for developing RESTful Web Services in Java that provides support for JAX-RS APIs and serves as a JAX-RS (JSR 311 & JSR 339) Reference Implementation.

#### JDK Http Server

Starting with Java SE 6, Java runtime ships with a built-in lightweight HTTP server. Jersey offers integration with this Java SE HTTP server through the jersey-container-jdk-http container extension module.

```
System.out.println("Starting Jersey REST-full Service with JDK HTTP Server ...");
URI baseUri = UriBuilder.fromUri("http://localhost/v1").port(8080).build();
ResourceConfig config = new ResourceConfig();
config.register(new BikeResource(client));
config.register(new CarResource(client));
HttpServer server = JdkHttpServerFactory.createHttpServer(baseUri, config);
```
### Jackson

[Jackson](https://github.com/FasterXML/jackson) Project, formerly known as the standard JSON library for Java (or JVM platform in general), or, as the "best JSON parser for Java." Or simply as "JSON for Java."

To use Jackson 2.x as your JSON provider you need to add jersey-media-json-jackson module to your pom.xml file:

```
<dependency>
    <groupId>org.glassfish.jersey.media</groupId>
    <artifactId>jersey-media-json-jackson</artifactId>
    <version>2.25.1</version>
</dependency>
```

### DynamoDB Mapper

Object mapper for domain-object interaction with DynamoDB.
To use, define a domain class that represents an item in a DynamoDB table and annotate it with the annotations found in the com.amazonaws.services.dynamodbv2.datamodeling package. In order to allow the mapper to correctly persist the data, each modeled property in the domain class should be accessible via getter and setter methods, and each property annotation should be either applied to the getter method or the class field

[More Info](http://docs.aws.amazon.com/AWSJavaSDK/latest/javadoc/com/amazonaws/services/dynamodbv2/datamodeling/DynamoDBMapper.html)

## Use
```
mvn clean
mvn compile
mvn package
java -jar MotorVehicle-jar-with-dependencies.jar
```
