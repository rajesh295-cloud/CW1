package frontend;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.github.lgooddatepicker.components.DatePicker;

import backend.FileOperations;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class UpdateDetails extends JFrame implements ActionListener{
	/*
	 * This function is responisble for updating
	 * details of users
	 */
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
	private static ArrayList<String[]> patients;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateDetails frame = new UpdateDetails(patients, id);
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
	public UpdateDetails(ArrayList<String[]> patients, int row) {
		setTitle("Detail Management");
		this.patients = patients; //Stores patient information
		this.id= row; //Stores selected patient's id
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 527, 658);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Add Patient Details");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(137, 10, 306, 35);
		contentPane.add(lblNewLabel);
		
		JLabel lblId = new JLabel("Patient Id");
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblId.setBounds(10, 60, 140, 28);
		contentPane.add(lblId);
		
		idField = new JTextField();
		idField.setBounds(198, 55, 245, 35);
		contentPane.add(idField);
		idField.setColumns(10);
		idField.setText(patients.get(id)[0]);
		idField.setEditable(false);
		
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setHorizontalAlignment(SwingConstants.CENTER);
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFirstName.setBounds(10, 118, 140, 28);
		contentPane.add(lblFirstName);
		
		textFname = new JTextField();
		textFname.setBounds(198, 116, 245, 35);
		contentPane.add(textFname);
		textFname.setColumns(10);
		textFname.setText(patients.get(id)[1]);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setHorizontalAlignment(SwingConstants.CENTER);
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLastName.setBounds(10, 175, 140, 28);
		contentPane.add(lblLastName);
		
		textLname = new JTextField();
		textLname.setColumns(10);
		textLname.setBounds(198, 173, 245, 35);
		contentPane.add(textLname);
		textLname.setText(patients.get(id)[2]);
		
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
		
		
		JLabel lblDistrict = new JLabel("District");
		lblDistrict.setHorizontalAlignment(SwingConstants.CENTER);
		lblDistrict.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDistrict.setBounds(10, 276, 140, 28);
		contentPane.add(lblDistrict);
		
		textDistrict = new JTextField();
		textDistrict.setColumns(10);
		textDistrict.setBounds(198, 274, 245, 35);
		contentPane.add(textDistrict);
		textDistrict.setText(patients.get(id)[4]);
		
		JLabel lblDateTested_1_1 = new JLabel("City");
		lblDateTested_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblDateTested_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDateTested_1_1.setBounds(10, 321, 140, 28);
		contentPane.add(lblDateTested_1_1);
		
		textCity = new JTextField();
		textCity.setColumns(10);
		textCity.setBounds(198, 319, 245, 35);
		contentPane.add(textCity);
		textCity.setText(patients.get(id)[5]);
		
		btnAdd = new JButton("Update Patient");
		btnAdd.setBounds(208, 585, 173, 36);
		contentPane.add(btnAdd);
		btnAdd.addActionListener(this);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnFemale);
		bg.add(rdbtnMale);
		
		if(patients.get(id)[3].equals("Male")) {
			rdbtnMale.setSelected(true);
		}
		else {
			rdbtnFemale.setSelected(true);
		}
		
		JLabel lblPatientType = new JLabel("Patient Type");
		lblPatientType.setHorizontalAlignment(SwingConstants.CENTER);
		lblPatientType.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPatientType.setBounds(10, 373, 140, 28);
		contentPane.add(lblPatientType);
		
		rdbtnCovidPatient = new JRadioButton("Covid Patient");
		rdbtnCovidPatient.setBounds(198, 373, 113, 28);
		contentPane.add(rdbtnCovidPatient);
		
		rdbtnCovidSuspect = new JRadioButton("Covid Suspect");
		rdbtnCovidSuspect.setBounds(320, 372, 113, 28);
		contentPane.add(rdbtnCovidSuspect);
		
		ButtonGroup bg1 = new ButtonGroup();
		bg1.add(rdbtnCovidPatient);
		bg1.add(rdbtnCovidSuspect);
		
		if(patients.get(id)[6].equals("Infected")) {
			rdbtnCovidPatient.setSelected(true);
		}
		else {
			rdbtnCovidSuspect.setSelected(true);
		}
		
		createdDate = new DatePicker();
		createdDate.setBounds(198, 542, 255, 28);
		contentPane.add(createdDate);
		createdDate.setText(patients.get(id)[9]);
		
		contactDate = new DatePicker();
		contactDate.setBounds(198, 489, 255, 28);
		contentPane.add(contactDate);
		contactDate.setEnabled(false);
		contactDate.setText(patients.get(id)[8]);
		
		covidDate = new DatePicker();
		covidDate.setBounds(198, 430, 255, 28);
		contentPane.add(covidDate);
		covidDate.setEnabled(false);
		covidDate.setText(patients.get(id)[7]);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnAdd) {
			String fname = textFname.getText();
			String lname = textLname.getText();
			String district = textDistrict.getText();
			String city = textCity.getText();
			String dateTest = covidDate.getText().replace(", ", " "); //DatePicker automatically add whitespace in its field
			String dateContact = contactDate.getText().replace(", ", " ");//Hence they are removed using replace method
			String dateCreate = createdDate.getText().replace(", ", " ");
			String gender;
			String type="";
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
			
			if(rdbtnMale.isSelected()) {gender="Male";}
			else {gender="female";}
			patients.get(id)[1] = fname;
			patients.get(id)[2] = lname;
			patients.get(id)[3] = gender;
			patients.get(id)[4] = district;
			patients.get(id)[5] = city;
			patients.get(id)[6] = type;
			patients.get(id)[7] = dateTest;
			patients.get(id)[8] = dateContact;
			patients.get(id)[9] = dateCreate;
			
			if(FileOperations.updatePatients(patients)) { //Send update request to backend
				JOptionPane.showMessageDialog(null, "Data Updated Successfully! ");
				Main_Window.refreshTable();
				this.dispose();
			}
		}
		
	}

}
