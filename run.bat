@echo off
echo Starting Bajaj Finserv Health Assignment...
echo.

if exist "target\health-assignment-0.0.1-SNAPSHOT.jar" (
    echo Running the application...
    java -jar target\health-assignment-0.0.1-SNAPSHOT.jar
) else (
    echo JAR file not found!
    echo Please run build.bat first to create the JAR file.
    echo.
    pause
)
