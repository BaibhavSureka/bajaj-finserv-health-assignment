#!/bin/bash

echo "Starting Bajaj Finserv Health Assignment..."
echo

if [ -f "target/health-assignment-0.0.1-SNAPSHOT.jar" ]; then
    echo "Running the application..."
    java -jar target/health-assignment-0.0.1-SNAPSHOT.jar
else
    echo "JAR file not found!"
    echo "Please run ./build.sh first to create the JAR file."
    exit 1
fi
