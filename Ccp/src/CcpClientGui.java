import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.awt.event.ActionEvent;
import javax.swing.JList;

public class CcpClientGui implements ServerHandlerCallback {

	private JFrame frmCcpClient;
	private JLabel lblStatus;
	private JButton btnConnect;
	
	private Socket connection;
	private JButton btnShow;
	private JList lstCurrencies;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CcpClientGui window = new CcpClientGui();
					window.frmCcpClient.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CcpClientGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCcpClient = new JFrame();
		frmCcpClient.setTitle("Ccp Client");
		frmCcpClient.setBounds(100, 100, 450, 417);
		frmCcpClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCcpClient.getContentPane().setLayout(null);
		
		lblStatus = new JLabel("Status: disconnected");
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus.setBounds(128, 51, 200, 16);
		frmCcpClient.getContentPane().add(lblStatus);
		
		btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
				
					if(connection == null || !connection.isConnected()) {
						connection = new Socket("localhost", 9999);
						lblStatus.setText("Status: connected");
						btnConnect.setText("Disconnect");
					} else {
						connection.close();
						lblStatus.setText("Status: disconnected");
						btnConnect.setText("Connect");
					}
					
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnConnect.setBounds(174, 108, 117, 29);
		frmCcpClient.getContentPane().add(btnConnect);
		
		btnShow = new JButton("Show Supported Currencies");
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Thread thread = new ServerHandlerThread();
				//thread.start();
				
				Thread thread = new ServerHandlerThread2(connection, CcpClientGui.this);
				thread.start();
				
			}
		});
		btnShow.setBounds(128, 171, 216, 29);
		frmCcpClient.getContentPane().add(btnShow);
		
		lstCurrencies = new JList();
		lstCurrencies.setBounds(128, 237, 207, 105);
		frmCcpClient.getContentPane().add(lstCurrencies);
	}
	
	private class ServerHandlerThread extends Thread {
		@Override
		public void run() {
			super.run();
			
			try {
				// Send request to the server
				CcpRequest request = new CcpRequest(Constants.PROTOCOL_VERSION, CcpOperation.LIST, null);
				Utils.sendRequestToTheServer(connection, request);
				
				// Read response from the server
				CcpResponse response = Utils.readResponseFromTheServer(connection);
				
				// Show result into the Jlist
				DefaultListModel<String> model = new DefaultListModel<>();
				for(String currency : response.getData()) {
					model.addElement(currency);
				}
				lstCurrencies.setModel(model);
				
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void onServerResponse(CcpResponse response) {
		// Show result into the Jlist
		DefaultListModel<String> model = new DefaultListModel<>();
		for(String currency : response.getData()) {
			model.addElement(currency);
		}
		lstCurrencies.setModel(model);
	}
	
}











