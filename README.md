# Spring Boot Application - Jumbo Stores

This Application used Spring-Boot and JAVA-8 and exposed a Restful services To get the list of Jumbo Stores , Find N number of nearest Jumbo Stores for given Location(Latitude, Longitude) and get the details of the Jumbo stores for given Store ID.


## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

## Prerequisites
For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.springboot.application.Application` class from your IDE.


----
    package com.jumbo.stores;

	import org.springframework.boot.SpringApplication;
	import org.springframework.boot.autoconfigure.SpringBootApplication;

	/**
	 * Spring Boot Application Starting point.
	 * 
	 * @author Waqas
	 *
	 */
	@SpringBootApplication
	public class JumboStoresApplication {
	
		public static void main(String[] args) {
			SpringApplication.run(JumboStoresApplication.class, args);
		}
	}

----



Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```
# REST API's
## Get All Jumbo Stores
The Application exposed a REST Endpoint for returning list of all Jumbo Stores.
API Request:
REST Endpoint URL : http://localhost:8080/api/list/stores
Http Method : GET

Upon hitting the above URL the JSON Response mentioned below will be returned as an outcome which will List of all the Jumbo Stores, Each Store object is having information about the store name,Its latitude and longigude and its address.

API Respose:
```
[
  {
    "uuid": "EOgKYx4XFiQAAAFJa_YYZ4At",
    "latitude": 51.778461,
    "addressName": "Jumbo 's Gravendeel Gravendeel Centrum",
    "longitude": 4.615551
  },

  {
    "uuid": "cg0KYx4XhfUAAAFI3dMYwKxJ",
    "latitude": 52.522354,
    "addressName": "Jumbo Zwolle Westenholte",
    "longitude": 6.048675
  },
  {
    "uuid": "gbcKYx4X4B8AAAFbARoHalpd",
    "latitude": 52.002103,
    "addressName": "Jumbo s-Gravenzande Hart van s-Gravenzande",
    "longitude": 4.163299
  }...
]
```

## Find N Nearest Neighbour
Application exposed a REST Endpoint to find the N Nearest Neighbour for given Latitude and Longitude and return the List of N stores. This Endpoint service used K Nearest Neighbour Algorithm to find the neighbours using the Data Structure KDTree which is based on Binary Search Tree and returns the list of the N nearest Stores for given Latitude and Longitude.  
API Request:
REST Endpoint URL : http://localhost:8080/api/store/EOgKYx4XFiQAAAFJa_YYZ4At
Http Method : GET

Upon hitting the above URL the JSON Response mentioned below will be returned as an outcome which contains the object of Store for Given Store UUID.

API Respose:
```
[
  {
    "uuid": "EOgKYx4XFiQAAAFJa_YYZ4At",
    "latitude": 51.778461,
    "addressName": "Jumbo 's Gravendeel Gravendeel Centrum",
    "longitude": 4.615551
  },

  {
    "uuid": "cg0KYx4XhfUAAAFI3dMYwKxJ",
    "latitude": 52.522354,
    "addressName": "Jumbo Zwolle Westenholte",
    "longitude": 6.048675
  },
  {
    "uuid": "gbcKYx4X4B8AAAFbARoHalpd",
    "latitude": 52.002103,
    "addressName": "Jumbo s-Gravenzande Hart van s-Gravenzande",
    "longitude": 4.163299
  }...
]
```

## Get Jumbo Store Detail
Application exposed a REST Endpoint to return the details of Jumbo Store by Providing Store UUID.  
API Request:
REST Endpoint URL : http://localhost:8080/api/list/nearest/stores?latitude=51.874272&longitude=4.615551&numNeighbour=5
Http Method : GET

Upon hitting the above URL the JSON Response mentioned below will be returned as an outcome which will contain the List of numNeighbour=5 nearest Jumbo Stores for given latitude =51.874272 and longitude =4.615551,  Each Store object is having information about the store name,Its latitude and longigude and its address.

API Respose:
```
{
  "city": "'s Gravendeel",
  "postalCode": "3295 BD",
  "street": "Kerkstraat",
  "street2": "37",
  "street3": "",
  "addressName": "Jumbo 's Gravendeel Gravendeel Centrum",
  "uuid": "EOgKYx4XFiQAAAFJa_YYZ4At",
  "longitude": "4.615551",
  "latitude": "51.778461",
  "complexNumber": "33249",
  "showWarningMessage": true,
  "todayOpen": "08:00",
  "locationType": "SupermarktPuP",
  "collectionPoint": true,
  "sapStoreID": "3605",
  "todayClose": "20:00"
}
```

## Swagger UI - API Documentation
In this Application the swagger UI is also configured for the API documentation purpose. SwaggerUIController exposed the SwaggerUI with the context path ("/")

Swagger UI URL : http://localhost:8080/swagger

The Swagger UI can be used to send the request to the REST API in order get the response of Search Albums and Books operation.
In Swagger UI, Under album-book-search-controller a GET method with the path '/api/list' should be listed in operations. Use this operation in order to search the albums and books by providing the input parameter term and click button Try it out! 
The request has been sent and response will be returned in the response body.


## Monitoring
For Monitoring purpose Spring Actuator is used in this application. Actuator brings production-ready features to the application. Actuator in this application is mainly used to expose the Health and performance metrices of the running API and Application.
Actuator server is configured in another tomcat with a diferrent using the property 'management.server.port=8081' defined in application.properties file.

Actuator Server URL : http://localhost:8081/actuator

Hitting this URL in the browser will give you the details about the endpoints which is exposed through the configuration.

##### Endpoints:
Here are the two endpoints of actualtor which is exposed and configured in this application by defining the configuration property 'management.endpoints.web.exposure.include=metrics,health' in application.properties file. This value of the property enabled health and metrics endpoint for the Application.

- http://localhost:8081/actuator/health - Shows health information of running API and application.
 ```
   {
  "status": "UP",
  "details": {
    "diskSpace": {
      "status": "UP",
      "details": {
        "total": 254735806464,
        "free": 154871386112,
        "threshold": 10485760
          }
        }
      }
    }
```

- http://localhost:8081/actuator/metrics – Shows ‘metrics’ information of the running API and application. This endpoint will list down the default metrics which is provided by the actuator as well as the custom metrics which is defined in the code, You can define your Own metrics like Rest services response time.
```
    {
  "names": [
    "jvm.memory.max",
    "http.server.requests",
    "jvm.gc.memory.promoted",
    "tomcat.cache.hit",
    "tomcat.cache.access",
    "jvm.memory.used",
    "jvm.gc.max.data.size",
    "jvm.gc.pause",
    "jvm.memory.committed",
    "system.cpu.count",
    "tomcat.global.sent",
    "jvm.buffer.memory.used",
    "tomcat.sessions.created",
    "jvm.threads.daemon",
    "system.cpu.usage",
    "jvm.gc.memory.allocated",
    "tomcat.global.request.max",
    "tomcat.global.request",
    "tomcat.sessions.expired",
    "jvm.threads.live",
    "jvm.threads.peak",
    "tomcat.global.received",
    "process.uptime",
    "tomcat.sessions.rejected",
    "process.cpu.usage",
    "tomcat.threads.config.max",
    "jvm.classes.loaded",
    "jvm.classes.unloaded",
    "tomcat.global.error",
    "tomcat.sessions.active.current",
    "tomcat.sessions.alive.max",
    "jvm.gc.live.data.size",
    "tomcat.servlet.request.max",
    "tomcat.threads.current",
    "tomcat.servlet.request",
    "jvm.buffer.count",
    "jvm.buffer.total.capacity",
    "tomcat.sessions.active.max",
    "tomcat.threads.busy",
    "process.start.time",
    "tomcat.servlet.error"
  ]
}
```
    
- Endpoint To Monitor the Metric values: http://localhost:8081/actuator/metrics/{Metric Name}
 example : http://localhost:8081/actuator/metrics/http.server.requests
The Above URL will give details about the server requests, The sample response is given below.
```
{
  "name": "http.server.requests",
  "description": null,
  "baseUnit": "seconds",
  "measurements": [
    {
      "statistic": "COUNT",
      "value": 5.0
    },
    {
      "statistic": "TOTAL_TIME",
      "value": 0.186358847
    },
    {
      "statistic": "MAX",
      "value": 0.0
    }
  ],
  "availableTags": [
    {
      "tag": "exception",
      "values": [
        "None"
      ]
    },
    {
      "tag": "method",
      "values": [
        "GET"
      ]
    },
    {
      "tag": "uri",
      "values": [
        "/**",
        "/api/list/stores"
      ]
    },
    {
      "tag": "status",
      "values": [
        "200",
        "304"
      ]
    }
  ]
}
```

## profile

Application used spring.profiles.active Environment property in the application.properties file to specify which profile is active.
``
    spring.profiles.active=dev
``

application-dev.properties File :

```

   stores.json.file.path=/json/stores.json

```

## Authors
* **Waqas Latif** (email: waqaslatif88@outlook.com)

