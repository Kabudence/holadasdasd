# Inn Control Platform

## Summary 
Inn Control Platform, illustrating development with Java, Spring Boot Framework, and Spring Data JPA on MySQL Database. It also illustrates open-api documentation configuration and integration with Swagger UI.

## PostMan Commands

Get
http://localhost:8080/inventory (Gets all items in inventory)
http://localhost:8080/inventory/{id} (Gets a specific item in inventory)
http://localhost:8080/inventory/brand?brandName={brandName} (Gets the items with the same brandName)

Post
http://localhost:8080/inventory (Posts a new item)
```
{
"productTitle": "Name",
"productDescription": "Description",
"Brand": "Brand",
"Quantity": QuantityNumber
}
```

Put
http://localhost:8080/inventory/{id} (Updates a specific item in inventory)
```
{
"productTitle": "Name",
"productDescription": "Description",
"Brand": "Brand",
"Quantity": QuantityNumber
}
```


### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.3.0/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.3.0/maven-plugin/reference/html/#build-image)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.3.0/reference/htmlsingle/index.html#data.sql.jpa-and-spring-data)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/3.3.0/reference/htmlsingle/index.html#using.devtools)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.3.0/reference/htmlsingle/index.html#web)

### Guides
The following guides illustrate how to use some features concretely:

* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

