import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ClientHandlerThread extends Thread {
	
	private Socket connection;
	
	public ClientHandlerThread(Socket connection) {
		super();
		this.connection = connection;
	}
	
	public void setConnection(Socket connection) {
		this.connection = connection;
	}

	@Override
	public void run() {
		super.run();
		
		while(true) {
			try {
			// Read request from the client
			InputStream inputStream = connection.getInputStream();
			Scanner scanner = new Scanner(inputStream);
			String rawRequest = scanner.nextLine();	
			CcpRequest request = CcpRequest.fromRawString(rawRequest);
			
			// Process the request
			if(request.getOperation().equals(CcpOperation.LIST)) {
				// Response supported currencies to the client
				List<String> data = Arrays.asList(Constants.SUPPORTED_CURRENCIES);
				CcpResponse response = new CcpResponse(Constants.PROTOCOL_VERSION, CcpStatus.OK, data);
				sendResponse(connection, response);
				
			}else if(request.getOperation().equals(CcpOperation.EXIT)) {
				System.out.println("Done with the client.");
				connection.close();
				break;
			} else {
				// Response currency exchange result to the client
				List<String> params = request.getParams();
				String sourceCurrency = params.get(0);
				double amount = Double.parseDouble(params.get(1));
				String destinationCurrency = params.get(2);
				double sourceExchangeRate = Constants.EXCHANGE_RATES.get(sourceCurrency);
				double destinationExchangeRate = Constants.EXCHANGE_RATES.get(destinationCurrency);
				double result = amount * destinationExchangeRate / sourceExchangeRate;  
				
				List<String> data = Arrays.asList(result + "");
				CcpResponse response = new CcpResponse(Constants.PROTOCOL_VERSION, CcpStatus.OK, data);
				sendResponse(connection, response);
			}
			}catch(IOException ex) {
				
			}
		}
	}
	
	private void sendResponse(Socket connection, CcpResponse response) throws IOException {
		OutputStream outputStream = connection.getOutputStream();
		PrintWriter printWriter = new PrintWriter(outputStream);
		printWriter.write(response.toRawResponse());
		printWriter.flush();
	}

}
