## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.example.products.ProductsApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

## REST API endpoints

1. ```HTTP GET http://localhost:8080/api/products``` Get *all* products without filtering by statuses (soft-deleted products will also be fetched). 

Returns HTTP 200. 

Limited by configuration param `web.query-result-limit`. 

Produces JSON:
```
[
    {
        "id": 1,
        "createdAt": "2020-04-16T23:11:55.031",
        "name": "Name1",
        "price": 20.21,
        "status": "DELETED"
    },
    {
        "id": 2,
        "createdAt": "2020-04-16T23:11:55.964",
        "name": "Name667",
        "price": 667.21,
        "status": "UPDATED"
    }
]
```

2. ```HTTP POST http://localhost:8080/api/products``` Add a new product. 

Returns HTTP 201. 

Consumes JSON:
```
{
"name": "Name1", 
"price": 12.21
}
```

Produces JSON:
```
{
    "id": 1,
    "createdAt": "2020-04-16T23:11:56.471",
    "name": "Name1",
    "price": 12.21,
    "status": "CREATED"
}
```

3. ```HTTP PUT http://localhost:8080/api/products/${id}``` Update a product with a given ID. 

Returns HTTP 200 or 404.

Consumes JSON:
```
{
"name": "Name1", 
"price": 333.21
}
```

Produces JSON:
```
{
    "id": 1,
    "createdAt": "2020-04-16T23:11:56.471",
    "name": "Name1",
    "price": 333.21,
    "status": "UPDATED"
}
```


4. ```HTTP DELETE http://localhost:8080/api/products/${id}``` Marks product with a given ID as DELETED. 

Returns HTTP 204 or 404.

