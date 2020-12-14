import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

public class CcpServer {

	public static void main(String[] args) {
		
		// Create server socket
		try (ServerSocket serverSocket = new ServerSocket(9999)) {
			
			// Wait and accept connection from clients
			System.out.println("Waiting for client...");
			Socket connection = serverSocket.accept();
			System.out.println("Accepted a client.");
			
			// Load IO streams
			InputStream inputStream = connection.getInputStream();
			OutputStream outputStream = connection.getOutputStream();
			
			// Read request from client
			Scanner scanner = new Scanner(inputStream);
			String rawRequest = scanner.nextLine();
			// Convert raw request to the CcpRequest
			CcpRequest request = CcpRequest.fromString(rawRequest);
			if(request == null) {
				// Invalid request
				CcpResponse response = new CcpResponse();
				response.setProtocolVersion("CCP/1.0");
				response.setStatus("Invalid");
				response.addData("Invalid request.");
				responseToClient(outputStream, response);
			} else {
				// Valid request
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private static void responseToClient(OutputStream outputStream, CcpResponse response) {
		System.out.println("Response to the client.");
		PrintWriter printWriter = new PrintWriter(outputStream);
		printWriter.write(response.toRawResponse());
		printWriter.flush();
	}
	
}






