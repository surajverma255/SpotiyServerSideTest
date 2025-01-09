# Use an official Maven image with Java 11 as a base
# Use Maven image with OpenJDK
FROM maven:3.8.6-openjdk-11-slim

# Set working directory
WORKDIR /app

# Copy project files
COPY pom.xml .
COPY src ./src

# Install dependencies
RUN mvn dependency:resolve

# Define the command to run tests
CMD ["mvn", "test"]
