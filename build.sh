#!/bin/bash

echo "Building Bajaj Finserv Health Assignment..."
echo

# Check if Maven is installed
if command -v mvn &> /dev/null; then
    echo "Maven found. Building project..."
    mvn clean package -DskipTests
    
    if [ $? -eq 0 ]; then
        echo
        echo "========================================"
        echo "BUILD SUCCESSFUL!"
        echo "========================================"
        echo "JAR file created at: target/health-assignment-0.0.1-SNAPSHOT.jar"
        echo
        echo "To run the application:"
        echo "java -jar target/health-assignment-0.0.1-SNAPSHOT.jar"
        echo
    else
        echo
        echo "========================================"
        echo "BUILD FAILED!"
        echo "========================================"
        echo "Please check the error messages above."
    fi
else
    echo "Maven is not installed!"
    echo "Please install Maven from: https://maven.apache.org/download.cgi"
    echo "Or use the Maven wrapper if available."
    exit 1
fi
