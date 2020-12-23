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
			
			// Send a list request to the server
			//CcpRequest request = new CcpRequest(Constants.PROTOCOL_VERSION, CcpOperation.LIST, null);
			List<String> params = Arrays.asList("KHR", "500", "VND");
			CcpRequest request = new CcpRequest(Constants.PROTOCOL_VERSION, CcpOperation.CONVERT, params);
			OutputStream outputStream = connection.getOutputStream();
			PrintWriter printWriter = new PrintWriter(outputStream);
			printWriter.write(request.toRawString());
			printWriter.flush();
			
			// Read response from the server
			InputStream inputStream = connection.getInputStream();
			Scanner scanner = new Scanner(inputStream);
			String rawRespone = scanner.nextLine();
			CcpResponse response = CcpResponse.fromRawResponse(rawRespone);
			System.out.println("The result is: \n" + response.getResult());
			
			scanner.close();
			connection.close();
			
		} catch (IOException e) {
			System.out.println("Connection fail. " + e.getMessage());
		}
		
	}
	
}
