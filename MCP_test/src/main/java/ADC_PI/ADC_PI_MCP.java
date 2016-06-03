
package ADC_PI;

import java.io.IOException;

import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;

public class ADC_PI_MCP {

	private static I2CDevice device;
	private static I2CBus bus;
	private static byte[] readbuffer = new byte[10];
	private int signbit = 0 ;
	
	
//	internal method 
	
	public byte set_config(int channel,int pga, int bitrate,int mode){
		
		
		 byte config_byte_pga = (byte) 0x03;
		 byte config_byte_sam = (byte) 0x0c;
		 byte config_byte_mode = (byte) 0x10;
		 byte config_byte_channel = (byte) 0x60;
		 config_byte_pga = (byte) (config_byte_pga & (pga));
		 config_byte_sam = (byte) (config_byte_sam & (bitrate << 2));
		 config_byte_mode = (byte) (config_byte_mode & (mode << 4));
		 config_byte_channel = (byte) (config_byte_channel & (channel << 5));
		//  retrun the -ve number representing the config byte
		 return (byte) (config_byte_pga+config_byte_sam+config_byte_mode+config_byte_channel+(1<<7)); 
	
	}
	
	
	
/*	channel    =  0,1,2,3
  	mode  =  1 continuous ,0 one shot 
	bit rate
	______________________________________
	0 = 240 SPS (12 bits) (Default)
	1 = 60 SPS (14 bits)
	2 = 15 SPS (16 bits)
	3 = 3.75 SPS (18 bits)
	______________________________________
	Pga
	0 = x1 (Default)
	1 = x2
	2 = x4
	3 = x8
*/
	
	public long read_raw(int address,int channel,int pga, int bitrate, int mode) throws IOException{
		
		byte h=0;
		byte m=0;
		byte l=0;
		byte s=0;
		long t = 0;
		signbit=0;
		
		if(device==null)
		bus = I2CFactory.getInstance(I2CBus.BUS_1);
		
		// the address according to the configuration of the kit
		device = bus.getDevice((byte)address); 
		// keep reading the ADC data until the conversion result is ready
		int timeout = 1000; // number of reads before a timeout occurs
		
		int x=0;
//		set configuration byte according to the user 
		byte config = set_config(channel,pga,bitrate,mode);
		
		device.write(config);
		
		do{
    		device.read(readbuffer, 0, readbuffer.length);
    		
    		if(bitrate==3){
    			s =  readbuffer[3];
    			l =  readbuffer[2];
    			m =  readbuffer[1];
    			h =  readbuffer[0];
    			
    		}else{
    			s =  readbuffer[2];
    			m =  readbuffer[1];
    			h =  readbuffer[0];
    			
    		}
    		if ((s & (1 << 7)) == 0) {
    			break;
    		}
    		
    		if (x > timeout) {
    			// timeout occurred
    			System.exit(0);
    		
    		}
    		x++;
    	}while(true);
		
		switch (bitrate) {
		case 3: // bit rate 18
			t = ((h & 3) << 16) + (( ( (byte) m) & 0xff)<< 8) + ( ((byte) l) & 0xff );
			if (((t >> 17) & 1)==1 ) {
				signbit = 1;
				t &= ~(1 << 17);
			}
			break;
		case 2: // bit rate 16
			t = (h << 8) +  ( ((byte) m) & 0xff );
			if ( ((t >> 15) & 1)==1 ) {
				signbit = 1;
				t &= ~(1 << 15);
			}
			break;
		case 1: // bit rate 14
			t = ((h & 63) << 8) + ( ((byte) m) & 0xff );
			if (((t >> 13) & 1)==1 ) {
				signbit = 1;
				t &= ~(1 << 13);
			}
			break;
		case 0:  // bit rate 12
			
//			to convert the byte to unsigned number --> ( ((byte) m) & 0xff )
//			byte in java is always a signed 8 bit number, that's why we convert it to unsigned because it exist at the lower byte of the number
			t = ((h & 15) << 8) + ( ((byte) m) & 0xff ) ;
			if ( ((t >> 11) & 1)==1) {
				signbit = 1;
				t &= ~(1 << 11);
			}
			break;
		default:
			break;
		}
		
		return t;
		
		
	}

	public double read_voltage(int address,int channel,int pga, int bitrate, int mode)throws IOException{
		
		int raw = (int) read_raw(address,  channel,      pga,     bitrate,    mode);
		
		switch (pga) {
		case 0:
			pga = 1;
			break;
		case 1:
			pga = 2;
			break;
		case 2:
			pga = 4;
			break;
		case 3:
			pga = 8;
			break;
		default:
			break;
			
		}
		
		double gain = (double) pga / 2;
		
		double lsb = 0;

		switch (bitrate) {
		case 0:
			lsb = 0.0005;
			break;
		case 1:
			lsb = 0.000125;
			break;
		case 2:
			lsb = 0.00003125;
			break;
		case 3:
			lsb = 0.0000078125;
			break;
		default:
			return (9999);
		}

		if (signbit == 1) // if the signbit is 1 the value is negative and most likely noise so it can be ignored.
		{
			return (0);
		}
		else {
			double voltage = ((double)raw * (lsb / gain)) * 2.471; // calculate the voltage and return it
			return (voltage);
		}
		
		
		
	}
	
}
