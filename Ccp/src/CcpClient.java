import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CcpClient {

	public static void main(String[] args) {
		
		// Connect to the server
		try (Socket connection = new Socket("localhost", 9999)){
			
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
					sendRequestToTheServer(connection, request);
				} else if(userInput.equals(CcpOperation.CONVERT)) {
					
				} else if(userInput.equals(CcpOperation.EXIT)) {
					CcpRequest request = new CcpRequest(Constants.PROTOCOL_VERSION, CcpOperation.EXIT, null);
					sendRequestToTheServer(connection, request);
					break;
				} else {
					System.out.println("Invalid input. Please try again.");
					continue;
				}
				
				// Read response from the server
				CcpResponse response = readResponseFromTheServer(connection);
				// Show the result to the user
				System.out.println("Result: " + response.getResult());
			}
		} catch (IOException e) {
			System.out.println("Connection fail. " + e.getMessage());
		}
		
	}
	
	static void sendRequestToTheServer(Socket connection, CcpRequest request) throws IOException {
		OutputStream outputStream = connection.getOutputStream();
		PrintWriter printWriter = new PrintWriter(outputStream);
		printWriter.write(request.toRawString());
		printWriter.flush();
	}
	
	static CcpResponse readResponseFromTheServer(Socket connection) throws IOException {
		InputStream inputStream = connection.getInputStream();
		Scanner scanner = new Scanner(inputStream);
		String rawRespone = scanner.nextLine();
		return CcpResponse.fromRawResponse(rawRespone);
	}
	
	
}










