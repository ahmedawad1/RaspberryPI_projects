# RaspberryPI_projects

In this repository I will provide a lot of examples to some simple and easy going java codes to be run on the RPI.

-<b>ledblink</b> --> Using PI4J library with Maven 

-<b>pi4j_addtriger</b> --> Using PI4J library + Maven + Spring boot Maven in the build section of the POM.xml
(compare between the pom.xml content of pi4j_addtriger and that of ledblink)
simple ha, I got it from the documentation of the Spring boot maven (content of the pom.xml) with modifying the pi4j dependency:
http://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#getting-started-maven-installation

-<b>MCP_test project</b>:
This project is a redistribution package for the C-libraries of ABelectronics to use the MCP3424 PI plus kit 
Open the 2 java classes to know how to run the jar file.
The project is based on pi4j library and Maven3.3.9

more information visit: 
the ADC Pi Plus https://www.abelectronics.co.uk/p/56/ADC-Pi-Plus-Raspberry-Pi-Analogue-to-Digital-converter

the original C-Demo https://github.com/abelectronicsuk/ABElectronics_C_Libraries/tree/master/ADCPi

-<b>formhandle</b>
It is a simple demo which shows the way to handle form using Spring Boot Maven frame work.
clone or download the project and import the project as a Maven project.
rebuild the project using Maven: form cmd "mvn clean package" or from eclipse and run it on the PI.
