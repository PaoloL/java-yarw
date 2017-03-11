FROM java:8

RUN mkdir /app

COPY $CODEBUILD_SRC_DIR/MotorVehicles-jar-with-dependencies.jar /app/MotorVehicles.jar
WORKDIR /app

EXPOSE 8080

CMD ["java" , "-jar", "MotorVehicles.jar"]
