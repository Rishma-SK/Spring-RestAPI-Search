# Dockerized Springboot Maven application

Building a single REST API endpoint that returns a filtered set of products from the provided data in the data.csv file

## Command to build the application with Maven
```
mvn clean package

```
## Command to build the Docker image
```
docker build -f Dockerfile -t docker-telenor-productcatalog .

```
## Command to Run the Docker image 
```
docker run -p 8085:8085 docker-telenor-productcatalog

```
## Other Commands
```
docker images

```
## Service Endpoints/Requests to verify the behavior

```
Port Used: 8085

```

### Retrieve a list of all products

```
http://localhost:8085/product

Response: HTTP 200

```

### Retrieve a list of all products with query param where type is phone

```
http://localhost:8085/product?type=phone

Response: HTTP 200

```

### Retrieve a list of all products with query params where type is phone, color is silver, min_price is 700, max_priceis 894 and city is Malmö

```
http://localhost:8085/product?type=phone&property:color=silver&min_price=700&max_price=894&city=Malmö

Response: HTTP 200

```
### Retrieve a list of all products with query param where type is mobile 

```
http://localhost:8085/product?type=mobile

Response: HTTP 404(Not Found)

```


### To view Swagger 2 API docs

Run the server and browse to 

```
http://localhost:8085/swagger-ui.html

```

### Dockerfile
```
* FROM openjdk:8
* ADD target/docker-productcatalog.jar docker-productcatalog.jar
* EXPOSE 8085
* ENTRYPOINT ["java","-jar","docker-productcatalog.jar"]
```
## Libraries used

1. Spring Boot 2.3.0.RELEASE
2. Junit,Mockito
3. Jackson
4. OpenCSV
5. Swagger 2 API



