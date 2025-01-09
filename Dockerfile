FROM maven:3.8.5-openjdk-11-slim
WORKDIR /app
COPY . /app
RUN mvn clean install
CMD ["mvn", "test", "-Dsurefire.suiteXmlFiles=./testng.xml"]
