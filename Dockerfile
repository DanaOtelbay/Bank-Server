FROM openjdk:18-oracle
COPY ./target/bankServerProject-0.0.1-SNAPSHOT.jar /testapp.jar
EXPOSE 8087
ENTRYPOINT ["java","-jar","/testapp.jar"]