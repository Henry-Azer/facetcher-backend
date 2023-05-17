# Getting Started

The backend of Facetcher is the underlying system that powers the application and handles its functionality. It is built using Spring Boot, a popular Java-based framework for building web applications. The backend is responsible for handling all the processing of data and requests from the frontend, as well as interacting with the database to store and retrieve information.
The use of Spring Boot as the framework for the backend ensures that the system is scalable, efficient, and robust. It allows for the easy integration of various third-party libraries, making it easier to incorporate new features and functionality as needed.

# Requirements

- JDK 11
- Maven 4

Installation:
- clone repo
```
git clone https://github.com/henry-azer/facetcher-backend.git
```
- run using  Spring Boot Maven plugin
```
mvn clean install
mvn spring-boot:run
```
- Docker 

```
docker pull seniorsteam23/facetcher-backend:tagname
docker run -d -p 8010:8010 seniorsteam23/facetcher-backend:tagname
```

# System Architecture

<p align="center">
  <img src="https://github.com/henry-azer/facetcher-backend/assets/102770811/3bb84b75-ecad-4463-9af3-2576f66f3b87" alt="System Architecture" width="800">
</p>

# Database Design Architecture

<p align="center">
  <img src="https://github.com/henry-azer/facetcher-backend/assets/102770811/68bf1f2c-c912-4f59-a0fd-0223795ce131" alt="Database Design Architecture" width="800">
</p>

# Swagger Document

<p align="center">
  <img src="https://github.com/henry-azer/facetcher-backend/assets/102770811/1cd8f4fb-bd9c-40d6-9751-d849fdcd02a3" alt="Swagger Document 1" width="800">
</p>
<p align="center">
  <img src="https://github.com/henry-azer/facetcher-backend/assets/102770811/03b01c98-6486-4ba6-9862-4c39dac98c6a" alt="Swagger Document 2" width="800">
</p>
<p align="center">
  <img src="https://github.com/henry-azer/facetcher-backend/assets/102770811/05482a7a-1dda-48e5-909f-34d12b8bf4e9" alt="Swagger Document 3" width="800">
</p>
<p align="center">
  <img src="https://github.com/henry-azer/facetcher-backend/assets/102770811/36e3fd7b-6148-43d8-b6a9-acc6a95b5ad0" alt="Swagger Document 4" width="800">
</p>
<p align="center">
  <img src="https://github.com/henry-azer/facetcher-backend/assets/102770811/0b9ccc94-045d-43b4-9011-a7c7da26fa82" alt="Swagger Document 5" width="800">
</p>

# Project Structure

```
├──  .circleci - This folder contains the config file.
|    └── config.yml
│    
│
├──  docker-compose - This folder contains the version, services and build configurations of the system.
│    └── Dockerfile
|    └── docker-compose.yml
│
|
├──  src - This folder contains the contains the source code for the project.
     └── main/
     └── test/java/com/henry/facetcher/
```

# Features

- User authentication and authorization: The backend allows users to securely log in to the application, and ensures that only authorized users can access certain features or data. This helps to protect the privacy and  security of the data stored in the system.

- Database management: The backend is responsible for storing and retrieving data to and from the database. This includes user account information, draft drawings, and generated images. The backend must ensure that all data is stored securely and is easily accessible to authorized users.

- API integration: The backend integrates with various APIs to support the functionality of the application. For example, it may integrate with a mapping API to provide location data for suspects or integrate with a facial recognition API for further analysis of generated images.

- User activity tracking: The backend tracks user activity within the application, including creating new drafts, modifying existing drafts, and accessing user profiles. This helps to ensure that the system is being used effectively and can provide useful data for future improvements or feature additions.

- Error handling: The backend must provide robust error handling to ensure that the system remains stable and functional. This includes handling errors related to user input, database connectivity, and machine learning algorithm errors.

# Libraries & Tools Used

- Spring Boot
- Restful Web Servics
- Clean Architecture 
- JWT Authentication 
- Postgres DB
- Circle CI 
- Docker
- Simple Storage Service (AWS)
- Relational Database Service (AWS)
- Elastic Compute Cloud (AWS)
- Simple Email Service (AWS)
- CloudFront (AWS) 
- Amazon Web Services

# Contribution

Henry Azer, the creator of the Facetcher system backend, has made a significant contribution to the development of the application. The backend is the foundation upon which the entire application is built, and without Henry's expertise and dedication, the application would not be possible.
