FROM openjdk:21-jdk

WORKDIR /app

EXPOSE 8080

ADD ./build/libs/flyway-0.0.1.jar .

CMD ["java", "-jar", "flyway-0.0.1.jar"]