# Java Backend with Docker

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring](https://img.shields.io/badge/Spring_Framework-5.3-green)
![Swagger](https://img.shields.io/badge/Swagger-Documentation-orange)
![Docker](https://img.shields.io/badge/Docker-Containerization-blue)
![Nginx](https://img.shields.io/badge/Nginx-Reverse_Proxy-green)

This repository contains a backend project in Java built based on the clean code methodology and structured using the Spring Framework. The project incorporates modern development practices such as integrating Docker containers through Docker Compose, leveraging the ability to deploy and orchestrate multiple services.

## Functionalities and Technical Characteristics

The project has the following technical functionalities and characteristics:

- **Language and Framework:** The project is developed in Java 17 and uses the Spring Framework to provide a robust and efficient framework for developing Java applications.

- **Clean Code:** The code follows clean code principles to ensure high readability, modularity and maintainability. This includes meaningful nomenclature, concise functions, and well-organized structures.

- **API documentation:** API documentation is automatically generated with Swagger, providing an interactive interface where you can explore and test API endpoints.

- **Exception Handler:** A centralized exception handler is implemented to consistently handle errors throughout the application, improving the user experience and the quality of error responses.

- **Services and Generic Repositories:** Generic services and repositories were implemented to promote code reuse and maintain a consistent pattern in the data access layer.

- **Authentication with JWT:** Authentication is done using JSON Web Tokens (JWT), ensuring the security of API routes and endpoints.
  
## Integration with Nginx

In addition to backend services, the project also includes an Nginx server as part of the infrastructure. Nginx acts as a reverse proxy server and load balancer for the backend1 and backend2 services, allowing efficient distribution of requests between backend containers. This improves application performance and scalability.

## Configuration and Execution

Follow the steps below to configure and run the project:

1. **Clone the repository:** Clone this repository on your local machine:

   ```
   git clone https://github.com/matheuslara01/rest-api-spring-docker.git
   cd rest-api-spring-docker
   ```

2. **Database Configuration:** If desired, change the database settings in the ``application.properties`` file and in the ``compose.yaml`` file.
   
3. **Nginx Configuration:** The `nginx.conf` file in the root of the project contains the Nginx server settings, including the reverse proxy rules for the backend services. Be sure to adjust these settings as needed.

4. **Start the Containers:** In the terminal, run the following command to start the containers defined in Docker Compose.

   ```
   docker-compose up -d
   ```
5. **Check the Containers:** Wait until all services are running. Check the status of containers with the command.

   ```
   docker-compose ps
   ```

6. **Access the API Documentation:** Access the Swagger API interactive documentation at:

    ```
     http://localhost:9999/demodocker/swagger-ui/index.html
    ```

---

## License

This project is licensed under the [MIT License](https://opensource.org/licenses/MIT).

