FROM openjdk:17-oracle

WORKDIR /app

COPY target/*.jar demo.jar

EXPOSE 8000

ENTRYPOINT ["java", "-jar", "demo.jar"]

