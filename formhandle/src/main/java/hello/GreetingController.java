package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
@RestController
@RequestMapping("/api")
public class GreetingController {

	boolean flag=false;
	private static GpioPinDigitalOutput pin;

	private static GpioController gpio;


	private static GpioController gpio1;
	private static GpioPinDigitalInput myButton;
	private static String str = null;	
	
    @RequestMapping("/button1")
    public String LED_control_button() {
    	
    	if (pin == null) {
    		// for the led provision visit http://pi4j.com/pins/model-2b-rev1.html
    		// GPIO_02 is pin number 13 
            gpio = GpioFactory.getInstance();
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
    		return "Led is On";
    	}	
    	
    }
    
    @RequestMapping("")
    public String switch_read() {
    	
//     to remember only, 
/* if you try to run the below code instead of having the test() function
 * it will work also 
 * but there is a severe bug with the addListener action when it is placed inside an annotated method with @RequestMapping !!
   so it was better to instantiate this class in the main function, and call the test() method once to provision the GPIO_06 pin 
   and when ever the Listener detects any changes on the pin, the str variable sends the value to the web

 */
    	
//    	gpio1 = GpioFactory.getInstance();
//    	if(myButton==null)
//   		 myButton = gpio1.provisionDigitalInputPin(RaspiPin.GPIO_06, PinPullResistance.PULL_DOWN);
//   	
//   	     myButton.addListener(new GpioPinListenerDigital() {
//            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
//                // display pin state on console
//              str = event.getState().toString();
//             
//              System.out.println(str);  
//            }
//            
//        });
    	
    	
    	return str;
    	
    	
    }
    
    
    
    @RequestMapping("/greeting")
    public String test_form_handle(@RequestParam("name") String name,@RequestParam("email") String email) {
    	
    		return  "Welcome "+name+" email: "+ email ;
       
    }
    
    public void test(){
    	gpio1 = GpioFactory.getInstance();
    	if(myButton==null)
   		 myButton = gpio1.provisionDigitalInputPin(RaspiPin.GPIO_06, PinPullResistance.PULL_DOWN);
   	
   	     myButton.addListener(new GpioPinListenerDigital() {
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                // display pin state on console
              str = event.getState().toString();
             
              System.out.println(str);  
            }
            
        });
    	
    }
   

}