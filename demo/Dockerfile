
FROM openjdk:21-oracle

# Set the working directory inside the container
WORKDIR /app

# Copy the packaged JAR file into the container
COPY target/demo-0.0.1-SNAPSHOT.jar /app

# Specify the command to run your Spring Boot application
CMD ["java", "-jar", "demo-0.0.1-SNAPSHOT.jar"]

