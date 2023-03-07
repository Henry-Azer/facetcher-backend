FROM openjdk:11
EXPOSE 8010
ADD target/facetcher-backend.jar facetcher-backend.jar
ENTRYPOINT ["java","-jar","/facetcher-backend.jar"]