import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class CcpClient {

	public static void main(String[] args) {
		// Connect to the server
		try {
			Socket connection = new Socket("localhost", 9999);
			
			// Send a request to the server
			OutputStream outputStream = connection.getOutputStream();
			PrintWriter printWriter = new PrintWriter(outputStream);
			CcpRequest request = new CcpRequest();
			request.setProtocolVersion("CCP/1.0");
			request.setOperation("list");
			printWriter.write(request.toRawRequest());
			printWriter.flush();
			
			// Read respose from server
			InputStream inputStream = connection.getInputStream();
			Scanner scanner = new Scanner(inputStream);
			String rawResponse = scanner.nextLine();
			CcpResponse response = CcpResponse.fromString(rawResponse);
			System.out.println("[Response from the server] " + response.toRawResponse());
			
		} catch (IOException e) {
			System.out.println("Cannot connect to the server. " + e.getMessage());
		}
	}
	
}
