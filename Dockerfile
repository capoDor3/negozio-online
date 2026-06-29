# Nel from decidiamo l'immagine base, nel nostro caso Java
#FROM eclipse-temurin:21-jdk
# nel workdir impostiamo la cartella di lavoro dentro l'immagine
#WORKDIR /app
# nel copy compiamo file dal nostro pc dentro l'immagine, ad esempio il .jar compilato
#COPY target/*.jar app.jar
#nell'expose documentiamo la porta su cui l'app ascolta
#EXPOSE 8080
# nell'entrypoint definiamo il comando da eseguire quando il container parte
# nel nostro caso eseguiamo il jar con java
#ENTRYPOINT ["java","-jar","app.jar"]

# Dockerfile modificato per multi-stage build
# Fase 1 - build - compiliamo il jar
FROM maven:3.9-eclipse-temurin-21 AS build
#partiamo da un'immagine che ha già maven 3.9 e java 21 - AS build ci permette di dare un nome a questa fase
#per poter riferirla più tardi
WORKDIR /app
# copio solo il pom, non ancora i sorgenti, in modo tale da prendermi tutte le dipendenze direttamente dalla cache
# senza aver bisogno di ribuildare tutto quanto insieme
COPY pom.xml .
# scarico tutte le dipendenze dichiarate nel pom
RUN mvn dependency:go-offline
# dopo aver scaricato le dipendenze copio tutti i sorgenti
COPY src ./src
# compilo e produco il jar in target
RUN mvn clean package -DskipTests

# Fase 2 - runtime - immagine leggera con solo Java
#il nuovo FROM inizia una nuova immagine, leggera poichè contiene solo il JRE
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]


