# RaspberryPI_projects

In this repository I will provide a lot of examples to some simple and easy going java codes to be run on the RPI.

-ledblink --> Using PI4J library with Maven 

-pi4j_addtriger --> Using PI4J library + Maven + Spring boot Maven in the build section of the POM.xml
(compare between the pom.xml content of pi4j_addtriger and that of ledblink)
simple ha, I got it from the documentation of the Spring boot maven (content of the pom.xml) with modifying the pi4j dependency:
http://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#getting-started-maven-installation

-MCP_test project:
This project is a redistribution package for the C-libraries of ABelectronics to use the MCP3424 PI plus kit 
Open the 2 java classes to know how to run the jar file.
The project is based on pi4j library and Maven3.3.9

more information visit: 
the ADC Pi Plus https://www.abelectronics.co.uk/p/56/ADC-Pi-Plus-Raspberry-Pi-Analogue-to-Digital-converter
<be>the original C-Demo https://github.com/abelectronicsuk/ABElectronics_C_Libraries/tree/master/ADCPi
