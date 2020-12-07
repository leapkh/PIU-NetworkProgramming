import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
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
				// Status-Line = HTTP-Version SP Status-Code SP Reason-Phrase CRLF
				String response = "HTTP/1.1 200 OK\r\n";
				response += "Content-Type: text/html";
				response += "Content-Length: " + responseBody.length()+"\r\n";
				response += "\r\n";
				response += responseBody;
				printWriter.write(response);
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












