import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HttpServer {
	
	public static void main(String[] args) {
		
		// Bind to port 1234
		try {
			System.out.println("Bind to the port 1234");
			ServerSocket serverSocket = new ServerSocket(1234);
			
			while (true) {
				System.out.println("Wait and accept a client");
				Socket connection = serverSocket.accept();
				
				System.out.println("Read request from the client");
				InputStream inputStream = connection.getInputStream();
				Scanner scanner = new Scanner(inputStream);
				while(scanner.hasNextLine()) {
					String request = scanner.nextLine();
					if(request.isEmpty()) {
						break;
					}
					System.out.println("Request: " + request);
				}

				
				System.out.println("Send response to the client.");
				OutputStream outputStream = connection.getOutputStream();
				PrintWriter printWriter = new PrintWriter(outputStream);
				String responseBody = "Hello client. Welcome to the server";
				Map<String, String> responseHeaders = new HashMap<String, String>();
				responseHeaders.put("Content-Type", "text/html");
				responseHeaders.put("Content-Length", responseBody.length() + "");
				HttpResponse response = new HttpResponse(200, "OK", responseHeaders, responseBody);
				
				printWriter.write(response.toString());
				printWriter.flush();
				
				System.out.println("Close the connection");
				printWriter.close();
				outputStream.close();
				scanner.close();
				inputStream.close();
				connection.close();
			}
			
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
	}

}












