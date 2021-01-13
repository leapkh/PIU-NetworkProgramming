import java.io.IOException;
import java.net.Socket;

import javax.swing.DefaultListModel;

public class ServerHandlerThread2 extends Thread {
	
	private Socket connection;
	private ServerHandlerCallback serverHandlerCallback;

	public ServerHandlerThread2(Socket connection, ServerHandlerCallback serverHandlerCallback) {
		super();
		this.connection = connection;
		this.serverHandlerCallback = serverHandlerCallback;
	}

	@Override
	public void run() {
		super.run();
		
		try {
			// Send request to the server
			CcpRequest request = new CcpRequest(Constants.PROTOCOL_VERSION, CcpOperation.LIST, null);
			Utils.sendRequestToTheServer(connection, request);
			
			// Read response from the server
			CcpResponse response = Utils.readResponseFromTheServer(connection);
			
			// Pass the result back to the Main thread
			serverHandlerCallback.onServerResponse(response);
				
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
	
