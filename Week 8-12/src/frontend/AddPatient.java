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
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import com.github.lgooddatepicker.components.DatePicker;

public class AddPatient extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField idField;
	private JTextField textFname;
	private JTextField textLname;
	private JTextField textDistrict;
	private JTextField textCity;
	private DatePicker covidDate;
	private DatePicker contactDate;
	private DatePicker createdDate;
	private JRadioButton rdbtnCovidPatient;
	private JRadioButton rdbtnCovidSuspect;
	private JRadioButton rdbtnMale;
	private JRadioButton rdbtnFemale;
	private JButton btnAdd;
	private static int id;
	private static File f;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() { 
				try {
					AddPatient frame = new AddPatient(id,f);
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
	public AddPatient(int nextId, File f) {
		setTitle("Add New Patient");
		this.id = nextId;
		this.f = f;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 527, 658);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAddDet = new JLabel("Add Patient Details");
		lblAddDet.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddDet.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAddDet.setBounds(137, 10, 306, 35);
		contentPane.add(lblAddDet);
		lblAddDet.setForeground(Color.RED);
		
		JLabel lblId = new JLabel("Patient Id");
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblId.setBounds(10, 60, 140, 28);
		contentPane.add(lblId);
		
		idField = new JTextField();
		idField.setEditable(false);
		idField.setBounds(198, 55, 245, 35);
		contentPane.add(idField);
		idField.setColumns(10);
		idField.setText(Integer.toString(id));
		
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setHorizontalAlignment(SwingConstants.CENTER);
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFirstName.setBounds(10, 118, 140, 28);
		contentPane.add(lblFirstName);
		
		textFname = new JTextField();
		textFname.setBounds(198, 116, 245, 35);
		contentPane.add(textFname);
		textFname.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setHorizontalAlignment(SwingConstants.CENTER);
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLastName.setBounds(10, 175, 140, 28);
		contentPane.add(lblLastName);
		
		textLname = new JTextField();
		textLname.setColumns(10);
		textLname.setBounds(198, 173, 245, 35);
		contentPane.add(textLname);
		
		JLabel lblDateTested = new JLabel("Date Tested");
		lblDateTested.setHorizontalAlignment(SwingConstants.CENTER);
		lblDateTested.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDateTested.setBounds(10, 428, 140, 28);
		contentPane.add(lblDateTested);
		
		JLabel lblContactedDate = new JLabel("Contacted Date");
		lblContactedDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblContactedDate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblContactedDate.setBounds(10, 487, 140, 28);
		contentPane.add(lblContactedDate);
		
		JLabel lblCreatedDate = new JLabel("Created Date");
		lblCreatedDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreatedDate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCreatedDate.setBounds(10, 540, 140, 28);
		contentPane.add(lblCreatedDate);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setHorizontalAlignment(SwingConstants.CENTER);
		lblGender.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGender.setBounds(10, 224, 140, 28);
		contentPane.add(lblGender);
		
		rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setBounds(196, 225, 90, 28);
		contentPane.add(rdbtnMale);
		
		rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setBounds(318, 224, 113, 28);
		contentPane.add(rdbtnFemale);
		
		JLabel lblDistrict = new JLabel("City");
		lblDistrict.setHorizontalAlignment(SwingConstants.CENTER);
		lblDistrict.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDistrict.setBounds(10, 276, 140, 28);
		contentPane.add(lblDistrict);
		
		textDistrict = new JTextField();
		textDistrict.setColumns(10);
		textDistrict.setBounds(198, 274, 245, 35);
		contentPane.add(textDistrict);
		
		JLabel lblDateTested_1_1 = new JLabel("Municipality");
		lblDateTested_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblDateTested_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDateTested_1_1.setBounds(10, 321, 140, 28);
		contentPane.add(lblDateTested_1_1);
		
		textCity = new JTextField();
		textCity.setColumns(10);
		textCity.setBounds(198, 319, 245, 35);
		contentPane.add(textCity);
		
		btnAdd = new JButton("Add Patient");
		btnAdd.setBounds(208, 585, 173, 36);
		btnAdd.setForeground(Color.BLUE);
		contentPane.add(btnAdd);
		btnAdd.addActionListener(this);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnFemale);
		bg.add(rdbtnMale);
		
		JLabel lblPatientType = new JLabel("Patient Type");
		lblPatientType.setHorizontalAlignment(SwingConstants.CENTER);
		lblPatientType.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPatientType.setBounds(10, 373, 140, 28);
		contentPane.add(lblPatientType);
		
		rdbtnCovidPatient = new JRadioButton("Covid Patient");
		rdbtnCovidPatient.setBounds(198, 373, 113, 28);
		contentPane.add(rdbtnCovidPatient);
		rdbtnCovidPatient.addActionListener(this);
		
		rdbtnCovidSuspect = new JRadioButton("Covid Suspect");
		rdbtnCovidSuspect.setBounds(320, 372, 113, 28);
		contentPane.add(rdbtnCovidSuspect);
		rdbtnCovidSuspect.addActionListener(this);
		
		ButtonGroup bg1 = new ButtonGroup();
		bg1.add(rdbtnCovidPatient);
		bg1.add(rdbtnCovidSuspect);
		
		createdDate = new DatePicker();
		createdDate.setBounds(198, 542, 255, 28);
		contentPane.add(createdDate);
		
		contactDate = new DatePicker();
		contactDate.setBounds(198, 489, 255, 28);
		contentPane.add(contactDate);
		contactDate.setEnabled(false);
		
		covidDate = new DatePicker();
		covidDate.setBounds(198, 430, 255, 28);
		contentPane.add(covidDate);
		covidDate.setEnabled(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String gender = "";
		String type = "";
		if(rdbtnCovidPatient.isSelected()) {
			covidDate.setEnabled(true);
			contactDate.setEnabled(false);
			type= "Infected";
		}
		if(rdbtnCovidSuspect.isSelected()) {
			contactDate.setEnabled(true);
			covidDate.setEnabled(false);
			type="Suspect";
		}
		if(rdbtnMale.isSelected()) {
			gender = "Male";
		}
		if(rdbtnFemale.isSelected()) {
			gender="Female";
		}
		
		if(e.getSource()==btnAdd) {
			String fname = textFname.getText();
			String lname = textLname.getText();
			String district = textDistrict.getText();
			String city = textCity.getText();
			String dateTest = covidDate.getText().replace(", ", " ");
			String dateContact = contactDate.getText().replace(", ", " ");
			String dateCreate = createdDate.getText().replace(", ", " ");
			if(type.equals("Suspect")) {
				dateTest = "Null";
			}
			else if(type.equals("Infected")) {
				dateContact = "Null";
			}

			if(validator(fname,lname,gender,district,city,dateTest,dateContact,dateCreate,type)) {
				try {
                    String data = "";
                    FileWriter fw = new FileWriter(f, true);
                    data += id+ ","+fname+ "," +lname+ "," + gender + "," +district+ "," + city + "," + type+", " +dateTest + "," + dateContact + "," + dateCreate + "\n";
                    fw.write(data);
                    fw.close();
                    if(type.equals("Infected")) {
                    	if(addPatient(id, fname, lname)) {
                    		JOptionPane.showMessageDialog(null, "Data successfully saved");
                            Main_Window.nextId++;
                            Main_Window.refreshTable();
                            this.dispose();
                    	}
                    }
                    if(type.equals("Suspect")) {
                    	if(addSuspect(id, fname, lname)) {
                    		JOptionPane.showMessageDialog(null, "Data successfully saved");
                            Main_Window.nextId++;
                            Main_Window.refreshTable();
                            this.dispose();
                    	}
                    }
                    
                    
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                
			}
			
		}

	}
	
	private boolean addPatient(int id, String fname, String lname) {
		String data = "";
		try {
			File infected = new File("files\\infected.txt"); 
			FileWriter fw = new FileWriter(infected, true);
			data += id+", "+fname+", "+lname+ "\n";
			fw.write(data);
			fw.close();
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private boolean addSuspect(int id, String fname, String lname) {
		String data = "";
		try {
			File not_infected = new File("files\\not_infected.txt"); 
			FileWriter fw = new FileWriter(not_infected, true);
			data += id+", "+fname+", "+lname+ "\n";
			fw.write(data);
			fw.close();
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private boolean validator(String fname, String lname, String gender, String district, String city, String dateTest, String dateContact, String dateCreate, String type) {
		if(fname.equals("")||lname.equals("")||gender.equals("")||district.equals("")||city.equals("")||dateTest.equals("")||dateContact.equals("")||dateCreate.equals("")) {
			JOptionPane.showMessageDialog(this, "One or more fields blank!");
			return false;
		}
		if(dateTest.equals("Null") && dateContact.equals("Null")) {
			JOptionPane.showMessageDialog(this, "Please Enter the required dates!");
			return false;
		}
		if((!dateTest.equals("Null")) && (!dateContact.equals("Null"))) {
			JOptionPane.showMessageDialog(this, "Please clear the non-essintial date!");
			return false;
		}
		return true;
	}
	
	void readFile(File f) {
		try {
		FileReader fr = new FileReader(f);
		}
		catch(Exception e) {
			try {
				FileWriter fw = new FileWriter(f);
				System.out.println("File Created!!");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
}
