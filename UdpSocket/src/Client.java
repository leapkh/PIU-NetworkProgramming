import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {
	
	public static void main(String[] args) {
		
		try {
			// Create a client socket
			DatagramSocket socket = new DatagramSocket(0);
			
			// Send request to the server
			String requestString = "Hello Server";
			InetAddress serverAddress = InetAddress.getByName("localhost");
			DatagramPacket requestPacket = new DatagramPacket(requestString.getBytes(), requestString.length(), serverAddress, 9999);
			socket.send(requestPacket);
			
			// Wait for response from the server
			byte[] buffer = new byte[1024];
			DatagramPacket responsePacket = new DatagramPacket(buffer, 1024);
			
			// Listen for incoming request from client
			socket.receive(responsePacket);
			
			// Process the response
			String data = new String(responsePacket.getData(), responsePacket.getOffset(), responsePacket.getLength());
			System.out.println("Response: " + data);
			
			socket.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
