FROM openjdk:11-jre-slim

RUN apt-get update && \
    apt-get install -y python3 python3-pip python3-opencv && \
    rm -rf /var/lib/apt/lists/*

RUN pip3 install jittor pyqt5 Pillow scipy dominate opencv-python boto3 torch

WORKDIR /facetcher
COPY ../target/facetcher-backend.jar /facetcher
COPY ../src/main/resources/fdl /facetcher/fdl

EXPOSE 8010
ENTRYPOINT ["java","-jar","/facetcher/facetcher-backend.jar"]
