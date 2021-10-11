# Start with a base image containing Java runtime
FROM openjdk:11.0.12-slim

# Add Maintainer Info
LABEL maintainer="villavek1@gmail.com"

# Add a volume pointing to /tmp
VOLUME /tmp

# The application's jar file
ARG JAR_FILE=target/exchangerate-0.0.1.jar

# Add the application's jar to the container
ADD ${JAR_FILE} exchangerate-0.0.1.jar

# Run the jar file
ENTRYPOINT ["java","-jar", "/exchangerate-0.0.1.jar"]