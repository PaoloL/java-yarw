FROM pdincau/workshop_java_8

RUN mkdir /app

COPY $CODEBUILD_SRC_DIR/target/MotorVehicle-jar-with-dependencies.jar /app/MotorVehicle.jar
WORKDIR /app

EXPOSE 8080

CMD ["java" , "-jar", "MotorVehicle.jar"]
