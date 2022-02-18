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

public class CreateContact extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JComboBox<ComboItems> patientCombo;
	private JComboBox<ComboItems> contactCombo;
	private BufferedReader br;
	private JButton btnAddContact;
	private Object[] tableLines=null; 
	static File f;
	static int[][] matrix;
	private static GraphOperations graph;
	private static ArrayList<String[]> patients;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateContact frame = new CreateContact(f,graph, patients);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CreateContact(File f, GraphOperations graph, ArrayList<String[]> arr) {
		/*
		 * This function is used to create connection between two different patients
		 */
		setTitle("Add Connection");
		patients = arr;
		this.f= f;
		this.graph = graph;
		matrix = graph.adjacency_matrix;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 432, 328);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Create Contact");
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
		
		JLabel lblContact = new JLabel("Contact");
		lblContact.setHorizontalAlignment(SwingConstants.CENTER);
		lblContact.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblContact.setBounds(0, 165, 155, 36);
		contentPane.add(lblContact);
		
		contactCombo = new JComboBox();
		contactCombo.setBounds(178, 172, 214, 26);
		contentPane.add(contactCombo);
		
		btnAddContact = new JButton("Add Contact");
		btnAddContact.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAddContact.setBounds(185, 233, 169, 42);
		contentPane.add(btnAddContact);
		btnAddContact.addActionListener(this);
		patients();
	}
	
	private void patients() {
		/*
		 * This function is used to add all patients along with their id into combo box
		 * All required data is pre-loaded to reduce file look up
		 */
    	for(String[] i:patients) {
    		patientCombo.addItem(new ComboItems(i[1]+" "+i[2], i[0]));
    		contactCombo.addItem(new ComboItems(i[1]+" "+i[2], i[0]));
    	}
}
	


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnAddContact) {
			Object item1 = patientCombo.getSelectedItem();
			int source = Integer.parseInt(((ComboItems) item1).getValue()); //Gets int value of selected patient
			
			Object item2 = contactCombo.getSelectedItem();
			int destination = Integer.parseInt(((ComboItems) item2).getValue());//gets int value of selected suspect
			
			if(source!=destination) {
				/*
				 * Checks if the suspect and patient is same.
				 * If its 
				 */
				boolean result = graph.addContact(source, destination);
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

	
}
