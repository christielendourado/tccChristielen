FROM openjdk:11-jdk-slim

MAINTAINER Christielen Dourado

VOLUME /tmp

# Add Spring Boot app.jar to Container
ADD "target/*.jar" app.jar
# Add application.yml to Container
ADD "target/classes/application-prod.yml" application.yml

ENV JAVA_OPTS=""

# Fire up our Spring Boot app by default
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]