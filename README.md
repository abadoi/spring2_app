# spring2_app
Spring reactive endpoint to handle currency conversion using Java11, Spring Webflux and Spring Boot 2+


This app was first designed to run with the new lightweight functional programming model ( that uses Routes and Handlers as an alternative to the annotation-based programming model - see `functional` branch) but otherwise runs on the same Reactive Core foundation.
but
decided to fallback to the traditional Spring MVC controllers with the reactive WebClient which uses the same annotations from Spring Web MVC module. 

Spring Boot Webflux Project, build in a Maven Docker and run in Docker, helps to ensure there is no "works on my system?" question.

# Build Project

- `docker-compose up --build`

# Run Project

- `docker-compose up`: Create and Start Docker Instances;
- `docker ps`: Check docker instances
- `http://localhost:8080/`: The endpoint

# Rebuild the Project after changes
- `docker-compose stop`: Stop all Instances from docker-compose.yml file;
- `docker rm spring2_app_web`: Delete the App Docker Instance;
- `Build & Run Project`;


# Dockers

### Docker Images
- `maven`: Image with Maven;
- `openjdk`: Image with Java JDK 11;
- `spring2_app_web`: Image based on `openjdk` that contain the JAR file;

### Docker Instances
- `spring2_app`: Running the `demo-0.0.1-SNAPSHOT` image. There are a service with Netty Server running the JAR file at port 8080;

### Docker Commands
- `docker ps`: show all Docker Instances running;
- `docker ps -a`: show all Docker Instances;
- `docker images`: show all Images;
- `docker-compose stop`: Stop all Instances from docker-compose.yml file;
