From openjdk:17
copy ./target/concerts-api-0.0.1-SNAPSHOT.jar concerts-api-0.0.1-SNAPSHOT.jar
copy config.properties .
CMD ["java","-jar","concerts-api-0.0.1-SNAPSHOT.jar"]