package frontend;

import java.awt.BorderLayout;
import java.awt.Color;
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
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import backend.FileOperations;
import backend.GraphOperations;

public class Main_Window extends JFrame implements ActionListener{
	static File f = new File("files\\patients.txt"); // Stores file object of patient details
	static FileWriter fw; //File Writer Object
	private JPanel contentPane;
	private JTable table;
	private JButton btnAddDetails;
	private JButton btnAddTracing;
	private JButton btnUpdate;
	private JButton btnView;
	private JButton btnDeleteConnection;
	private JButton btnViewModerateRisk;
	private static DefaultTableModel model;
	private ArrayList<String> covid; //Stores Infected Patients
	private ArrayList<String> suspect; //Stores Suspects
	private static ArrayList<String []>  patient;
	public static int nextId; //Stores Next Id for user
	private static BufferedReader br;
	private static Object[] tableLines=null;
	private int selectedRow = -1; //Initially selected row
	private File matrix = new File("files\\matrix.txt");
	private GraphOperations graph = new GraphOperations(100); //Graph Object
	ArrayList<String []> covid_data = new ArrayList<String []>(); //Temp array for performing matrix operations

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main_Window frame = new Main_Window();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main_Window() {
		/*
		 * Constructor method for main window
		 * Pre-loads all GUI Components
		 */
		setTitle("Admin Pannel");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 939, 530);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDept = new JLabel("Department Of Virology");
		lblDept.setHorizontalAlignment(SwingConstants.CENTER);
		lblDept.setForeground(Color.RED);
		lblDept.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDept.setBounds(313, 10, 347, 34);
		contentPane.add(lblDept);
		
		JLabel lblNep = new JLabel("Nepal Government");
		lblNep.setHorizontalAlignment(SwingConstants.CENTER);
		lblNep.setForeground(Color.GRAY);
		lblNep.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNep.setBounds(379, 35, 209, 34);
		contentPane.add(lblNep);
		
		JLabel lblSystem = new JLabel("Covid Tracing System");
		lblSystem.setHorizontalAlignment(SwingConstants.CENTER);
		lblSystem.setForeground(Color.DARK_GRAY);
		lblSystem.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSystem.setBounds(367, 79, 232, 34);
		contentPane.add(lblSystem);
		
		btnAddDetails = new JButton("Add Patient Details");
		btnAddDetails.setBounds(10, 249, 194, 51);
		contentPane.add(btnAddDetails);
		btnAddDetails.addActionListener(this);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(224, 248, 701, 245);
		contentPane.add(scrollPane);
		
		
		model = new DefaultTableModel();
		table = new JTable();

		Object[] column = {
				"Id","First Name", "Last Name", "Gender", "District", "City", "Patient Type", "Date Tested"
			};
		scrollPane.setViewportView(table);
		model.setColumnIdentifiers(column);
		table.setModel(model);
		
		btnUpdate = new JButton("Update Details");
		btnUpdate.setBounds(10, 341, 194, 51);
		contentPane.add(btnUpdate);
		btnUpdate.addActionListener(this);
		
		btnDeleteConnection = new JButton("Delete Connection");
		btnDeleteConnection.setBounds(10, 432, 194, 51);
		contentPane.add(btnDeleteConnection);
		btnDeleteConnection.addActionListener(this);
		
		btnView = new JButton("View High-Risk Patients");
		btnView.setBounds(389, 141, 194, 51);
		contentPane.add(btnView);
		btnView.addActionListener(this);
		
		btnViewModerateRisk = new JButton("Moderate-Risk Patients");
		btnViewModerateRisk.setBounds(721, 141, 194, 51);
		contentPane.add(btnViewModerateRisk);
		btnViewModerateRisk.addActionListener(this);
		
		btnAddTracing = new JButton("Add Contact Tracing");
		btnAddTracing.setBounds(10, 141, 194, 51);
		contentPane.add(btnAddTracing);
		btnAddTracing.addActionListener(this);
		readMatrix();
		populateInformation();
	}
	
	public void readMatrix() {
		/*
		 * Reads form graph file 
		 * If file does not exists it is created
		 * If it exists all its data are loaded into int[][] of graph object
		 */
		if(!matrix.exists()) { //If file does not exist
			try {
				for (int i=0; i<100; i++) { //create 100X100 array with all 0s
	                for (int j=0; j<100; j++) {
	                    graph.adjacency_matrix[i][j] = 0; //Loads that  into graph object's array
	                }
	            }
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else { //Otherwise if file exist,its data is loaded into graph obj
			try {
				ArrayList<String[]> arr = FileOperations.readFile(matrix); //reads from graph file
				for(String[] i:arr) { //Itterates through result and add it into Arraylist
	        		covid_data.add(i);
	        	}
	        	
	        	int len = covid_data.size();
	            
	            if (len!=0) {	
	                for (int i=0; i<len-1; i++) {
	                    for (int j=0; j<covid_data.get(0).length; j++) { // Adds data one by one into matrix 
	                    	graph.adjacency_matrix[i][j] = Integer.parseInt(covid_data.get(i)[j]); //converts String data into integer
	                    }
	                }
	            }
	        	
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	static void populateInformation() {
		/*
		 * fills the table with user details
		 */
		patient  = FileOperations.readFile(f);
		for(String[] i:patient) {
    		model.addRow(i);
    	}
        nextId = Integer.parseInt(patient.get(patient.size()-1)[0]); //Sets next patient's ID as +1 of last patient of current list

		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnAddDetails) {
			new AddPatient(nextId,f).setVisible(true); //Opens patient addition form
		}
		else if(e.getSource()==btnAddTracing) {
			new CreateContact(f,graph, patient).setVisible(true);// Opens contact tracing form
		}
		else if(e.getSource()==btnDeleteConnection) {
			new RemoveConnection(f,graph, patient).setVisible(true); //Opens Contact Remove Form
		}
		else if(e.getSource()==btnUpdate) {
			selectedRow = table.getSelectedRow(); //Gets id of selected row
			if(selectedRow==-1) { //See if user hasn't selected a row but pressed the button instead
				JOptionPane.showMessageDialog(this, "No patient is selected!");
			}
			else {
				new UpdateDetails(patient, selectedRow).setVisible(true); //Opens update from
			}
		}
		
		else if(e.getSource()==btnView) {
			new HighRisk(graph).setVisible(true); //Opens high risk form
		}
		else if(e.getSource()==btnViewModerateRisk) {
			new ModerateRisk(graph).setVisible(true); //Opens moderate risk form
		}
		
	}	
	
	
	public static void refreshTable() {
		/*
		 * This function is used to refresh table one data is updated/removed
		 * Firstly, all current data are removed
		 * Then, new data is added
		 */
		for(int i=model.getRowCount()-1;i>=0;i--){
			model.removeRow(i);
		    }
		populateInformation();
	}
	
	
}
