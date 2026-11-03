<img width="1917" height="1082" alt="image" src="https://github.com/user-attachments/assets/a1a5445c-b7bd-4315-9ad6-5b665cdf1097" />

# Spring Boot Microservices – Load Balancing

## 📌 Project Overview

This project demonstrates how to implement client-side load balancing in a Spring Boot microservices architecture using **Netflix Eureka Server, Spring Cloud OpenFeign, and Spring Cloud LoadBalancer**.

The same Spring Boot microservice is deployed as multiple instances running on different ports. Eureka Server provides service discovery, while Spring Cloud LoadBalancer distributes incoming requests among available service instances through OpenFeign.

## 🏗️ Project Structure

```text
load-balancer-with-microservices/
│
├── eureka-server/
│   ├── src/
│   └── pom.xml
│
├── greet-api/
│   ├── src/
│   └── pom.xml
│
├── consumer-api/
│   ├── src/
│   └── pom.xml
│
├── README.md
└── .gitignore
```

## 🛠️ Technologies Used

- Java 17 / 21
- Spring Boot
- Spring Cloud Netflix Eureka
- Spring Cloud OpenFeign
- Spring Cloud LoadBalancer
- Spring MVC / REST APIs
- Maven
- IntelliJ IDEA
- Git & GitHub

## 🚀 Running Multiple Instances on Different Ports

The same Spring Boot application can run multiple times on different ports using JVM VM arguments in IntelliJ IDEA.

### Instance Configuration

| Instance | VM Arguments | Port |
|---|---|---|
| Greet API Instance 1 | `-Dserver.port=8086` | 8086 |
| Greet API Instance 2 | `-Dserver.port=8087` | 8087 |
| Greet API Instance 3 | `-Dserver.port=8088` | 8088 |

### IntelliJ IDEA Configuration

1. Open Run → Edit Configurations.
2. Select the existing Spring Boot application.
3. Copy the configuration to create another instance.
4. Open Modify Options → Add VM Options.
5. Configure a different port for each instance.
6. Start all three application instances.

All instances run the same application code but listen on different ports.

## 🔍 Eureka Client Configuration

Configure each Greet API instance to register with the Eureka Server.

```properties
spring.application.name=02-NOV-26-GREET-API

eureka.client.service-url.defaultZone=http://localhost:8761/eureka/

eureka.client.register-with-eureka=true
eureka.client.fetch-registry=true
```

All instances use the same application name: `GREET-API`.

## 🔗 OpenFeign Client Implementation

The Consumer API communicates with Greet API using OpenFeign.

```java
@FeignClient(name = "02-NOV-26-GREET-API")
public interface GreetClient {

    @GetMapping("/greet")
    String getGreet();
}
```

No fixed host or port is required in the Feign client.

Spring Cloud LoadBalancer selects an available Greet API instance using service discovery information from Eureka.

## 🏛️ Architecture

```text
              CONSUMER API
                   |
                   v
               OpenFeign
                   |
                   v
        Spring Cloud LoadBalancer
                   |
                   v
              Eureka Server
                   |
          ---------------------
          |         |         |
          v         v         v
       Greet API Greet API Greet API
        :8086     :8087     :8088
          |         |         |
          ---------------------
             Same Application
```

## 🧪 Testing Load Balancing

Create a REST endpoint in Greet API:

```java
@RestController
public class GreetController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/greet")
    public String greet() {
        return "Hello from Greet API running on port: " + port;
    }
}
```

Invoke the Consumer API multiple times to observe responses from different Greet API instances.

Example responses:

```text
Hello from Greet API running on port: 8086
Hello from Greet API running on port: 8087
Hello from Greet API running on port: 8088
```

The default Spring Cloud LoadBalancer implementation generally uses round-robin selection.

## 🎯 Key Learning Outcomes

- Running multiple instances of the same Spring Boot application.
- Configuring different ports using JVM system properties.
- Implementing service discovery using Netflix Eureka.
- Integrating OpenFeign for inter-service communication.
- Understanding client-side load balancing.
- Building scalable microservices architecture.
- Understanding how microservices communicate dynamically without hardcoded ports.

## ▶️ Execution Order

1. Start Eureka Server on port `8761`.
2. Start Greet API instance on port `8086`.
3. Start Greet API instance on port `8087`.
4. Start Greet API instance on port `8088`.
5. Start Consumer API.
6. Invoke the Consumer API endpoint and observe load balancing.

## 👨‍💻 Author

**Krishna Kant Tiwari**

Sr. Java Backend Developer | Spring Boot | Microservices | REST APIs

GitHub: [JavaDevKKT](https://github.com/JavaDevKKT)

