package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

@SpringBootApplication
public class Application {
	
	
   
   
	
    public static void main(String[] args) {
    	
    	
//    	Instantiation of Controller here is to help provision the GPIO_06 to be configured   
    	GreetingController cont = new GreetingController();
    	cont.test();
    	
        SpringApplication.run(Application.class, args);
    }

}