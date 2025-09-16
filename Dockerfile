FROM openjdk:8-jre-alpine

WORKDIR /app

EXPOSE 8080

ADD ./build/libs/olp-0.0.1-SNAPSHOT.jar .

CMD ["java", "-jar", "olp-0.0.1-SNAPSHOT.jar"]