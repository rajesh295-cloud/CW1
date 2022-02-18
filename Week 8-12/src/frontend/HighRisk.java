package frontend;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import backend.ComboItems;
import backend.GraphOperations;
import backend.JungLearning;
import cern.colt.Arrays;

import javax.swing.border.TitledBorder;
import java.awt.Color;
import java.awt.Component;

import javax.swing.border.EtchedBorder;

public class HighRisk extends JFrame implements ItemListener, ActionListener{

	private JPanel contentPane;
	private JPanel panel;
	private JTable table;
	private JButton btnTrace;
	private DefaultTableModel model;
	private JComboBox<Object> comboSource;
	private ArrayList<String[]> infected;
	private static Object[] tableLines=null;
	private static Object[] infecteds=null;
	private BufferedReader br;
	private static GraphOperations graph;
	private ArrayList<String []> allPatient;
	private ArrayList<String []> patient;
	private ArrayList<String []> dataCol;
	File f = new File("files\\patients.txt");
	File inf = new File("files\\infected.txt");
	private JungLearning jl = new JungLearning();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HighRisk frame = new HighRisk(graph);
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
	public HighRisk(GraphOperations graph) {
		setTitle("High Risk Patients");
		this.graph = graph;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 805, 478);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("High Risk Patients");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(240, 10, 277, 39);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Patient Name:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(180, 71, 146, 26);
		contentPane.add(lblNewLabel_1);
		
		comboSource = new JComboBox<Object>();
		comboSource.setBounds(376, 71, 179, 25);
		contentPane.add(comboSource);
		comboSource.addItemListener(this);
		
	
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 119, 785, 322);
		contentPane.add(scrollPane);
		model = new DefaultTableModel();
		table = new JTable();
		
		
		Object[] column = {
				"First Name", "Last Name", "City", "Municipality", "Date Contact"
			};
		scrollPane.setViewportView(table);
		model.setColumnIdentifiers(column);
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		btnTrace = new JButton("Trace Contacts");
		btnTrace.setBounds(591, 72, 162, 26);
		contentPane.add(btnTrace);
		btnTrace.addActionListener(this);
		
		getInfected();
		readFile();
		
	}
	
	void populateInformation(ArrayList<Integer> arr) {
        try {
        	
        	br = new BufferedReader(new FileReader(f));
        	
        	tableLines = br.lines().toArray();
        	for(int i=0;i<tableLines.length;i++) {
        		String line = tableLines[i].toString().trim();
        		String[] dataRow = line.split(",");
        		allPatient.add(dataRow);
        	}
        	for(Integer i: arr) {
        		model.addRow(allPatient.get(i));
        	}
        	
        	 
            
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

		
	}
	
	void getInfected() {
		patient  = new ArrayList<String []>();
		try {
        	
        	br = new BufferedReader(new FileReader(inf));
        	infecteds = br.lines().toArray();
        	int size = infecteds.length;
        	for(int i=0;i<size;i++) {
        		String line = infecteds[i].toString().trim();
        		String[] dataRow = line.split(",");
        		comboSource.addItem(new ComboItems(dataRow[1]+" "+dataRow[2], dataRow[0]));
        	}
            
        }
        	
        	 
            
        catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
	}
	
	private void readFile() {
		infected  = new ArrayList<String []>();
        try {
        	
        	br = new BufferedReader(new FileReader(f));
        	
        	tableLines = br.lines().toArray();
        	for(int i=0;i<tableLines.length;i++) {
        		String line = tableLines[i].toString().trim();
        		String[] dataRow = line.split(",");
        		infected.add(dataRow);
        	}
            
        }
        catch(Exception e) {
        	
        }
	}


	@Override
	public void itemStateChanged(ItemEvent e) {
		Object item = comboSource.getSelectedItem();
		int source = Integer.parseInt(((ComboItems) item).getValue());
		populateInfo(source);
		
	}
	
	
	private void populateInfo(int source) {
		dataCol = graph.BFS(source);
		allPatient  = new ArrayList<String []>();
		clearTable();
		populateInformation(graph.allNodes);
		
		
		for(String[] i:dataCol) {
			int patient = Integer.parseInt(i[1]);
			i[2] = allPatient.get(Integer.parseInt(i[1]))[1] +" " + allPatient.get(Integer.parseInt(i[1]))[2];
			i[1] = allPatient.get(Integer.parseInt(i[0]))[1] +" " + allPatient.get(Integer.parseInt(i[0]))[2];
			i[0] = allPatient.get(patient)[9];
		}	
		
	}
	

	private void clearTable() {
		for(int i=model.getRowCount()-1;i>=0;i--){
			model.removeRow(i);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnTrace) {
			jl.getVisual(dataCol);
		}
		
	}
}
