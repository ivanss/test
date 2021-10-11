# Start with a base image containing Java runtime
FROM openjdk:11.0.12-slim

# Add Maintainer Info
LABEL maintainer="villavek1@gmail.com"

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8888 available to the world outside this container
EXPOSE 9100

# The application's jar file
ARG JAR_FILE=target/app-oauth-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} app-oauth-0.0.1.jar

# Run the jar file
ENTRYPOINT ["java", "-jar", "/app-oauth-0.0.1.jar"]