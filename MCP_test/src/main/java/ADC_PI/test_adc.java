/*
 * **********************************************************************
 * ORGANIZATION  :  www.Bigdutchman.de
 * PROJECT       :  MCP3424 ADC converter chip 
 * FILENAME      :  MCP_test.java  
 * Author        : 	Ahmed Awad
 * Date          :  03.06.2016 (Vechta-CalvesLage Germany)
 * This project is considered to be a redistribution package for the C-libraries 
 * from ABelectronics to use the MCP3424 ADC kit plus (ADC Differential Pi)
 * more information about the kit:
 * https://www.abelectronics.co.uk/p/65/ADC-Differential-Pi-Raspberry-Pi-Analogue-to-Digital-converter
 * **********************************************************************
 * Execute the code:
 * 
 * on the target folder there exist a jar package file with the name
 * "MCP3424_adc-1.0-SNAPSHOT-jar-with-dependencies.jar"
 * 1- copy and paste this file to the Raspberry pi
 * 2- navigate to the location where this file was copied using cd command
 * 3- execute the jar file using 
 * "sudo java -jar MCP3424_adc-1.0-SNAPSHOT-jar-with-dependencies.jar"  note: Java JDK must be installed on the Raspberry (JDK7 or 8 recommended)
 * 
 * ********************************************************************** 
 * The project depends on the pi4j library and Maven in java 
 * www.pi4j.com    www.maven.apache.org
 *   _______________________________________________________________________
    |Mapping of Configuration register on the MCP3424 ADC                   |
    |____________Refer to the Data sheet for more information_______________|
    |channel    =  0,1,2,3													|
    |_______________________________________________________________________|											
  	|mode       =  1 continuous ,0 one shot 								|
	|_______________________________________________________________________|
	|bit rate																|
	|0 = 240 SPS (12 bits) (Default)										|	
	|1 = 60 SPS (14 bits)													|	
	|2 = 15 SPS (16 bits)													|
	|3 = 3.75 SPS (18 bits)													|
	|_______________________________________________________________________|	
	|Programmable Gain Amplifier											|
	|0 = x1 (Default)														|
	|1 = x2																	|	
	|2 = x4																	|
	|3 = x8																	|
	|_______________________________________________________________________|
 * 
 * %%
 * Copyright (C) 2015 - 2016 Bigdutchman
 * %%
 * 
 */


package ADC_PI;

public class test_adc {

	
	public static void main(String[] args) throws Exception {
		
		ADC_PI_MCP test =new ADC_PI_MCP();
		
		while(true){
//			clear the console on the Raspberry PI
			System.out.print("\033[H\033[2J");
//			read_voltage(int address,int channel,int pga, int bitrate, int mode)
			 System.out.println("Pin 1: "+test.read_voltage(0x68,0,0,3,1)); // read from adc chip 1, channel 1, 18 bit, pga gain set to 1 and continuous conversion mode
			 
			 System.out.println("Pin 2: "+test.read_voltage(0x68,1,0,2,1));// read from adc chip 1, channel 2, 16 bit, pga gain set to 1 and continuous conversion mode
			 
			 System.out.println("Pin 3: "+test.read_voltage(0x68,2,0, 1,1));// read from adc chip 1, channel 3, 14 bit, pga gain set to 1 and continuous conversion mode
			 
			 System.out.println("Pin 4: "+test.read_voltage(0x68,3,0,0,1));// read from adc chip 1, channel 4, 12 bit, pga gain set to 1 and continuous conversion mode
			 
			 System.out.println("Pin 5: "+test.read_voltage(0x69,0,0,0,1));// read from adc chip 2, channel 1, 12 bit, pga gain set to 1 and continuous conversion mode
			 
			 System.out.println("Pin 6: "+test.read_voltage(0x69,1,0,0,1));// read from adc chip 2, channel 2, 12 bit, pga gain set to 1 and continuous conversion mode
			 
			 System.out.println("Pin 7: "+test.read_voltage(0x69,2,0,0,1));// read from adc chip 2, channel 3, 12 bit, pga gain set to 1 and continuous conversion mode
			 
			 System.out.println("Pin 8: "+test.read_voltage(0x69,3,0,0,1));// read from adc chip 2, channel 4, 12 bit, pga gain set to 1 and continuous conversion mode
			 
			 Thread.sleep(1000);
		}
		
	}
}
