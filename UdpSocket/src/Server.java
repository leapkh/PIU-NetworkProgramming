import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Server {
	
	public static void main(String[] args) {
		
		try {
			// Create a server socket
			DatagramSocket socket = new DatagramSocket(9999);
			
			while(true) {
				// Create a packet for receiving request
				byte[] buffer = new byte[1024];
				DatagramPacket requestPacket = new DatagramPacket(buffer, 1024);
				
				// Listen for incoming request from client
				System.out.println("Listen for clients...");
				socket.receive(requestPacket);
				
				// Process the request
				String data = new String(requestPacket.getData(), requestPacket.getOffset(), requestPacket.getLength());
				System.out.println("Request: " + data);
				
				// Response to the client
				String responseString = "Welcome to the Server";
				DatagramPacket responsePacket = new DatagramPacket(responseString.getBytes(), responseString.length(), requestPacket.getAddress(), requestPacket.getPort());
				socket.send(responsePacket);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
