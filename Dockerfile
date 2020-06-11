FROM openjdk:8
ADD target/docker-productcatalog.jar docker-productcatalog.jar
EXPOSE 8085
ENTRYPOINT ["java","-jar","docker-productcatalog.jar"]