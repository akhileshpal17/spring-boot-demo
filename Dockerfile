# Use an official Java 21 runtime as a parent image
FROM openjdk:21-jdk-slim

# Copy the JAR file from the build/libs directory to the container
COPY crudapp/build/libs/crudapp-0.0.1-SNAPSHOT.jar crudapp.jar

# Expose the port the application will run on (5000)
EXPOSE 5000

# Command to run the JAR file
ENTRYPOINT ["java", "-jar", "crudapp.jar"]
