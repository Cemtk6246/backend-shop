FROM openjdk:21-jdk

WORKDIR /app

EXPOSE 8080

ADD ./build/libs/olp-0.0.1-SNAPSHOT.jar .

CMD ["java", "-jar", "olp-0.0.1-SNAPSHOT.jar"]