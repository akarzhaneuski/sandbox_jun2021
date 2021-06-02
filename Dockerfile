FROM openjdk:16
EXPOSE 8080
ADD ./sandbox-web/target/sandbox-web-0.0.1-SNAPSHOT.jar /opt/sandbox-web-0.0.1-SNAPSHOT.jar
ENTRYPOINT [ "java", "-jar", "/opt/sandbox-web-0.0.1-SNAPSHOT.jar" ]
#CMD ["--spring.profiles.active=docker"]