## Microprofile Example

A example with JAX-RS, Hal, OpenApi Doc, JPA, Config, Security and Metrics

## Development with IntelliJ IDEA and Payara Micro

### Setup Development Environment

Download "Payara Micro Jar" file and put it in local folder.

Create a Run Configuration: "Jar Application" with configuration.

```
Path to jar: <your folder>\payara-micro-5\payara-micro-5.194.jar

Program arguments: --deploy <your folder>//payara-rest-micro//target//payara-rest-micro-1.0-SNAPSHOT --contextroot rest-micro

Working directory: <your folder>\payara-rest-micro

JRE: Java 1.8

Search sources using module's classpath: "payara-rest-micro" IntelliJ IDEA module path

Before launch: Build Artifacts ... - choose "payara-rest-micro:war exploded" option
```

For more details on debug configuration: https://blog.payara.fish/how-to-run-and-debug-your-payara-micro-application-with-intellij-idea

Refresh Payara deployment with mvn task.

```
package -Preload -DskipTests=true
```

Now the deployed application is automatically refreshed.

## JAX-RS endpoints

### CREATE
```
curl -X POST http://localhost:8080/rest-micro/api/person --header "Content-Type: application/json" -d "{\"name\":\"Gaurav Gupta\", \"address\":\"Lucknow, India\"}"
```

### READ All
```
curl -X GET  http://localhost:8080/rest-micro/api/person
```

### READ Single
```
curl -X GET  http://localhost:8080/rest-micro/api/person/1
```

### UPDATE
```
curl -X PUT http://localhost:8080/rest-micro/api/person --header "Content-Type: application/json" -d "{\"name\":\"Gaurav\", \"id\":1, \"address\":\"Lucknow, India\"}"
```

### DELETE
```
curl -X DELETE  http://localhost:8080/rest-micro/api/person/1
```

## Actual URL's for Development

REST Services: http://localhost:8080
- GET	/rest-micro/api/person
- POST	/rest-micro/api/person
- PUT	/rest-micro/api/person
- DELETE	/rest-micro/api/person/{id}
- GET	/rest-micro/api/person/{id}

OpenApi UI: 
- http://localhost:8080/rest-micro/api/openapi-ui

Documentation:
- https://download.eclipse.org/microprofile/
- https://www.payara.fish/learn/payara-platform-documentation/
- https://microshed.org/microshed-testing/