package gui_RPI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;



import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Color;
import javax.swing.JTextArea;

public class GUI_APP   {

	private JFrame frame;
	private JTextField textField;
	private static JTextField textField_1;
	private static JTextArea textArea;
	private URL url;
	Timer timer = new Timer();
	TimerTask Task;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_APP window = new GUI_APP();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI_APP() {
		initialize();
	}

	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setSize(20, 10);
		frame.getContentPane().setLayout(null);
		
		JButton btnGpioLed = new JButton("GPIO LED 2 ");
		btnGpioLed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
		            // get URL content

		            String a="http://10.104.5.129:9090/api/button1"; // The RPI address 10.104.5.129 run ifconfig on the shell to know yours
		            url = new URL(a);
		            URLConnection conn = url.openConnection();

		            // open the stream and put it into BufferedReader
		            BufferedReader br = new BufferedReader(
		                               new InputStreamReader(conn.getInputStream()));

		            String inputLine;
		            while ((inputLine = br.readLine()) != null) {
//		                    System.out.println(inputLine);
		            	textField.setText(inputLine);
		            }
		            br.close();

		            System.out.println("Done");

		        } catch (MalformedURLException error) {
		        	error.printStackTrace();
		        } catch (IOException error) {
		        	error.printStackTrace();
		        }
	            

	           
			}
		});
		btnGpioLed.setBounds(21, 27, 154, 23);
		frame.getContentPane().add(btnGpioLed);
		
		textField = new JTextField();
		textField.setBounds(21, 80, 154, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblThe = new JLabel("The switch case is ");
		lblThe.setBounds(21, 146, 147, 14);
		frame.getContentPane().add(lblThe);
		
		textField_1 = new JTextField();
		textField_1.setBounds(21, 191, 154, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textArea = new JTextArea();
		textArea.setBackground(Color.LIGHT_GRAY);
		textArea.setBounds(237, 88, 154, 72);
		frame.getContentPane().add(textArea);
		
		JLabel lblNewLabel = new JLabel("The user form input");
		lblNewLabel.setBounds(237, 27, 154, 33);
		frame.getContentPane().add(lblNewLabel);
		
		 Task = new TimerTask () {
		    @Override
		    public void run () {
		        // your code here...

				try {
		            // get URL content

		            String a="http://10.104.5.129:9090/api/";
		            String a_form="http://10.104.5.129:9090/api/greeting";
		            url = new URL(a);
		            URL form = new URL(a_form);
		            URLConnection conn = url.openConnection();
		            URLConnection conn1 = form.openConnection();

		            // open the stream and put it into BufferedReader
		            BufferedReader br = new BufferedReader(
		                               new InputStreamReader(conn.getInputStream()));

		            BufferedReader br1 = new BufferedReader(
                            new InputStreamReader(conn1.getInputStream()));

		            String inputLine;
		            String inputLine1;
		            while ((inputLine = br.readLine()) != null) {

		            	textField_1.setText(inputLine);
		            }
		            br.close();
		            
		            while ((inputLine1 = br1.readLine()) != null) {

		            	textArea.setText(inputLine1);
		            	
		            }
		            br1.close();

		        } catch (MalformedURLException error) {
		        	error.printStackTrace();
		        } catch (IOException error) {
		        	error.printStackTrace();
		        }	
		    }
		}; 
		timer.scheduleAtFixedRate( Task , 0, 50);
		
		
	}
}
