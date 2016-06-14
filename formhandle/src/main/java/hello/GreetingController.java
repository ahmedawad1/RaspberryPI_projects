package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

@RestController
@RequestMapping("/api")
public class GreetingController {

	boolean flag=false;
	private static GpioPinDigitalOutput pin;
	
	
   

    @RequestMapping("/button1")
    public String LED_control_button() {
    	
    	if (pin == null) {
    		// for the led provision visit http://pi4j.com/pins/model-2b-rev1.html
    		// GPIO_02 is pin number 13 
            GpioController gpio = GpioFactory.getInstance();
            pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "MyLED", PinState.LOW);
        }
    	
    	if(flag == false){
    		flag = true;
    		pin.low();
    		return "Led is off";
    	}
		
    	else{
    		flag= false;
    		pin.high();
    		return "Led Is On";
    	}	
    	
    }
    
    @RequestMapping("/greeting")
    public String test_form_handle(@RequestParam("name") String name,@RequestParam("email") String email) {
    	
    		return  "Welcome "+name+" email: "+ email ;
       
    }

}