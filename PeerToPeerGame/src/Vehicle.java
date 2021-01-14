import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Vehicle {

	private JFrame frmVehicle;
	private JLabel lblVehicle;
	private JComboBox cmbVehicle;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vehicle window = new Vehicle();
					window.frmVehicle.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Vehicle() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmVehicle = new JFrame();
		frmVehicle.getContentPane().addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				moveVehicle(e.getX(), e.getY());
			}
		});
		frmVehicle.setTitle("Vehicle");
		frmVehicle.setBounds(100, 100, 450, 300);
		frmVehicle.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmVehicle.getContentPane().setLayout(null);
		
		lblVehicle = new JLabel("");
		lblVehicle.setIcon(new ImageIcon(System.getProperty("user.dir") + "/img/car.png"));
		lblVehicle.setBounds(6, 45, 71, 32);
		frmVehicle.getContentPane().add(lblVehicle);
		
		cmbVehicle = new JComboBox();
		cmbVehicle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				changeVehicle();
				
			}
		});
		cmbVehicle.setModel(new DefaultComboBoxModel(new String[] {"Car", "Motorbike"}));
		cmbVehicle.setSelectedIndex(0);
		cmbVehicle.setBounds(6, 6, 120, 27);
		frmVehicle.getContentPane().add(cmbVehicle);
	}
	
	private void moveVehicle(int x, int y) {
		lblVehicle.setBounds(x, y, lblVehicle.getWidth(), lblVehicle.getHeight());
	}
	
	private void changeVehicle() {
		String vehicle = cmbVehicle.getSelectedItem().toString();
		if(vehicle.equals("Car")) {
			lblVehicle.setIcon(new ImageIcon(System.getProperty("user.dir") + "/img/car.png"));
		} else {
			lblVehicle.setIcon(new ImageIcon(System.getProperty("user.dir") + "/img/motorbike.png"));
		}
	}

}
