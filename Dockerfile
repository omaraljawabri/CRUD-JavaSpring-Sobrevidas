# FROM maven:3.8.2-jdk-8 # for Java 8
FROM jelastic/maven:3.9.5-openjdk-21

WORKDIR /CRUD-JavaSpring-Sobrevidas-main
COPY . .
RUN mvn clean install

CMD mvn spring-boot:run
