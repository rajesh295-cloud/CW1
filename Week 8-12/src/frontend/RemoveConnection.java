package frontend;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import com.github.lgooddatepicker.components.DatePicker;

import backend.ComboItems;
import backend.GraphOperations;

import javax.swing.JButton;

public class RemoveConnection extends JFrame implements ActionListener, ItemListener{

/*
 * This class removes connection between nodes
 */
	private JPanel contentPane;
	private JComboBox<ComboItems> patientCombo;
	private JComboBox<ComboItems> contactCombo;
	private BufferedReader br;
	private JButton btnRemoveContact;
	private Object[] tableLines=null; 
	static File f;
	static int[][] matrix;
	private static GraphOperations graph;
	private static ArrayList<String[]> patients;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RemoveConnection frame = new RemoveConnection(f,graph,patients);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public RemoveConnection(File f, GraphOperations graph, ArrayList<String[]> arr) {
		/*
		 * Required parameters:
		 * File f -> file to read graph from
		 * GraphOperations graph -> graph object which has preloaded matrix data
		 * arr -> Array containing patients's details
		 */
		setTitle("Remove Connections");
		this.f= f;
		this.graph = graph;
		patients = arr;
		matrix = graph.adjacency_matrix;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 432, 328);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Remove Contact");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(114, 10, 240, 42);
		contentPane.add(lblNewLabel);
		
		JLabel lblPatient = new JLabel("Patient");
		lblPatient.setHorizontalAlignment(SwingConstants.CENTER);
		lblPatient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPatient.setBounds(0, 92, 155, 36);
		contentPane.add(lblPatient);
		
		patientCombo = new JComboBox();
		patientCombo.setBounds(178, 99, 214, 26);
		contentPane.add(patientCombo);
		patientCombo.addItemListener(this);
		
		JLabel lblContact = new JLabel("Contact");
		lblContact.setHorizontalAlignment(SwingConstants.CENTER);
		lblContact.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblContact.setBounds(0, 165, 155, 36);
		contentPane.add(lblContact);
		
		contactCombo = new JComboBox();
		contactCombo.setBounds(178, 172, 214, 26);
		contentPane.add(contactCombo);
		
		btnRemoveContact = new JButton("Remove Connection");
		btnRemoveContact.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnRemoveContact.setBounds(185, 233, 169, 42);
		contentPane.add(btnRemoveContact);
		btnRemoveContact.addActionListener(this);
		patients();
	}
	


	private void patients() {
		/*
		 * Populate combo bo containing patient info
		 */
		for(String[] i: patients) {
	    		patientCombo.addItem(new ComboItems(i[1]+" "+i[2], i[0]));
		}
	    		

}
	
	public void getContacts(ArrayList<Integer> contacts) {
		/*
		 * Get All contacts of selected patients
		 * contacts -> Arraylist containing adjacent connection of a node
		 */
		contactCombo.removeAllItems(); //Firstly all existing items for combobox is removed
		for(int i:contacts) {
			contactCombo.addItem(new ComboItems(patients.get(i)[1]+" "+patients.get(i)[2], patients.get(i)[0]));
		}
		if(contacts.isEmpty()) {
			/*
			 * If connection is empty, remove and dropdown are disabled 
			 */
			contactCombo.setEnabled(false);
			btnRemoveContact.setEnabled(false);
		}
	}
	
	
	
	


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnRemoveContact) {
			Object item1 = patientCombo.getSelectedItem();
			int source = Integer.parseInt(((ComboItems) item1).getValue());
			
			Object item2 = contactCombo.getSelectedItem();
			int destination = Integer.parseInt(((ComboItems) item2).getValue());
			if(source!=destination) {

				boolean result = graph.removeContact(source, destination); //removes contact form given nodes
				graph.save();
				if(result) {
					JOptionPane.showMessageDialog(this, "Contact Added Successfully!");
					this.dispose();
				}
			}
			
			else {
				JOptionPane.showMessageDialog(this, "Source and destination can not be same!");
			}

		}
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		/*
		 * Function to monitor if combobox item is change..if so, get all contacts of selected user and display
		 * in contact combobox
		 */
		if(e.getSource()==patientCombo) {
			Object item = patientCombo.getSelectedItem();
			int source = Integer.parseInt(((ComboItems) item).getValue());
			contactCombo.setEnabled(true);
			btnRemoveContact.setEnabled(true);
			getContacts(graph.adjacentNodes(source));
		}
		
	}

	
}
