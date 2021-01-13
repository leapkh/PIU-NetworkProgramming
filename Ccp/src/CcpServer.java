import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CcpServer {

	public static void main(String[] args) {
		
		try (ServerSocket serverSocket = new ServerSocket(9999)) {
			
			while(true) {
				// Wait for a client
				System.out.println("Waiting for client...");
				Socket connection = serverSocket.accept();
				
				// Create a thread to handle the connection
				System.out.println("A connection was established.");
				System.out.println("Forward the connection to another thread to handle");
				Thread clientHandlerThread = new ClientHandlerThread(connection);
				clientHandlerThread.start();
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}






