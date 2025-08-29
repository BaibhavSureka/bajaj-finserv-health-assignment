@echo off
echo Building Bajaj Finserv Health Assignment...
echo.
echo Note: This script requires Maven to be installed.
echo If Maven is not installed, please download it from: https://maven.apache.org/download.cgi
echo.

if exist "mvn.cmd" (
    echo Using local Maven...
    call mvn.cmd clean package -DskipTests
) else if exist "%M2_HOME%\bin\mvn.cmd" (
    echo Using Maven from M2_HOME...
    call "%M2_HOME%\bin\mvn.cmd" clean package -DskipTests
) else (
    echo Trying to use mvn from PATH...
    mvn clean package -DskipTests
)

if %ERRORLEVEL% EQU 0 (
    echo.
    echo ========================================
    echo BUILD SUCCESSFUL!
    echo ========================================
    echo JAR file created at: target\health-assignment-0.0.1-SNAPSHOT.jar
    echo.
    echo To run the application:
    echo java -jar target\health-assignment-0.0.1-SNAPSHOT.jar
    echo.
) else (
    echo.
    echo ========================================
    echo BUILD FAILED!
    echo ========================================
    echo Please check the error messages above.
)

pause
