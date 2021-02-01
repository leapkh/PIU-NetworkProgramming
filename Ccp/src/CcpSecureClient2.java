import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Scanner;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class CcpSecureClient2 {

	public static void main(String[] args) {
		
		// Create trust managers that trust any server certificate.
		X509TrustManager trustManager = new X509TrustManager() {
			
			@Override
			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}
			
			@Override
			public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				
			}
			
			@Override
			public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				
			}
		};
		TrustManager[] allTrustManagers = new TrustManager[] {trustManager};
		
		try {
			// Create a context using the trust managers above
			SSLContext context = SSLContext.getInstance("SSL");
			context.init(null, allTrustManagers, null);
			
			// Create a client socket and connect to the server using socket factory from the context
			SSLSocketFactory socketFactory = context.getSocketFactory();
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
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		
	}
	
}













