import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HttpClient {

	public static void main(String[] args) {
		
		try {
			// Initiate connection to the server
			Socket connection = new Socket("localhost", 80);
			System.out.println("The connection to the server was established.");
			
			// Load IO streams to receive and send data from and to the server
			InputStream inputStream = connection.getInputStream();
			OutputStream outputStream = connection.getOutputStream();
			
			// Send request to the server
			System.out.println("Send data to the server");
			Map<String, String> headers = new HashMap<>();
			headers.put("Host", "localhost");
			HttpRequest request = new HttpRequest("GET", "/", headers, "");
			
			
			PrintWriter printWriter = new PrintWriter(outputStream);
			printWriter.write(request.toString());
			printWriter.flush();
			
			// Read data from the server
			Scanner scanner = new Scanner(inputStream);
			while(scanner.hasNext()) {
				String data = scanner.nextLine();
				System.out.println("[Response from the server] " + data);
			}
			
			// Close resources and connection
			inputStream.close();
			scanner.close();
			outputStream.close();
			printWriter.close();
			connection.close();
			
		} catch (IOException e) {
			System.out.println("Cannot connect to the server. " + e.getMessage());
		}
		
	}
	
}
