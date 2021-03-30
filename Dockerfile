FROM java:8
EXPOSE 8082
ADD target/proyectoSpringPrueba-0.0.1-SNAPSHOT.jar proyectoSpringPrueba-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","proyectoSpringPrueba-0.0.1-SNAPSHOT.jar"]

