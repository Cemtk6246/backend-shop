FROM openjdk:21-jdk

WORKDIR /app

EXPOSE 8080

ADD ./build/libs/user-0.0.1.jar .

CMD ["java", "-jar", "user-0.0.1.jar"]