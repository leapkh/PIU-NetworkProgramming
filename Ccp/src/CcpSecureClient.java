import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class CcpSecureClient {

	public static void main(String[] args) {
		
		// Specify trust store info
		System.setProperty("javax.net.ssl.trustStore", "/Users/leapkh/Developer/java/PIU-NetworkProgramming/Ccp/lib/piu-trust-store.cacerts");
		System.setProperty("javax.net.ssl.trustStorePassword", "123456");
		
		try {
			SSLSocketFactory socketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
			SSLSocket connection = (SSLSocket) socketFactory.createSocket("localhost", 9999);
			
			// Tell users what they can do
			System.out.println("Please tell us what you want to do:");
			System.out.println(" - Type 'list' to list all supported currencies.");
			System.out.println(" - Type 'convert' convert between supported currenccies.");
			System.out.println(" - Type 'exit' to exit the program.");
			
			// Read input from users, what they want to do
			InputStream keyboardInputStream = System.in;
			Scanner keyboadScanner = new Scanner(keyboardInputStream);
			while(true) {
				System.out.print("Type your operation here: ");
				String userInput = keyboadScanner.nextLine();
				if(userInput.equals(CcpOperation.LIST)) {
					CcpRequest request = new CcpRequest(Constants.PROTOCOL_VERSION, CcpOperation.LIST, null);
					Utils.sendRequestToTheServer(connection, request);
				} else if(userInput.equals(CcpOperation.CONVERT)) {
					
				} else if(userInput.equals(CcpOperation.EXIT)) {
					CcpRequest request = new CcpRequest(Constants.PROTOCOL_VERSION, CcpOperation.EXIT, null);
					Utils.sendRequestToTheServer(connection, request);
					break;
				} else {
					System.out.println("Invalid input. Please try again.");
					continue;
				}
				
				// Read response from the server
				CcpResponse response = Utils.readResponseFromTheServer(connection);
				// Show the result to the user
				System.out.println("Result: " + response.getResult());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
