package frontend;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;

public class Register extends JFrame implements ActionListener {
	File f = new File("files\\users.txt"); //File object to store user info
	private JPanel contentPane;
	private JTextField fnameField;
	private JLabel lblLastName;
	private JTextField lnameField;
	private JLabel lblUserName;
	private JTextField unameField;
	private JLabel lblEmailAddress;
	private JTextField emailField;
	private JLabel lblPassword;
	private JPasswordField passwordField;
	private JButton btnRegister;
	private JButton btnCancle;
	private JTextField idField;
	private JPasswordField passwordField_2;
	
	private ArrayList<String> ids; //Stores all IDs of users
	private ArrayList<String> unames; //Stores all Usernames of users
	
	public static void main(String[] args) {
		Register frame = new Register();
		frame.setVisible(true);

	}


	public Register() {
		setTitle("Registartion Page");
		createFolder();
		readFile();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 496, 584);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel titleLabel = new JLabel("Admin Registration Page");
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(168, 25, 232, 36);
		contentPane.add(titleLabel);
		
		JLabel firstNameLabel = new JLabel("First Name");
		firstNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		firstNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		firstNameLabel.setBounds(10, 117, 174, 36);
		contentPane.add(firstNameLabel);
		
		fnameField = new JTextField();
		fnameField.setBounds(184, 124, 243, 26);
		contentPane.add(fnameField);
		fnameField.setColumns(10);
		
		lblLastName = new JLabel("Last Name");
		lblLastName.setHorizontalAlignment(SwingConstants.CENTER);
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLastName.setBounds(10, 163, 174, 36);
		contentPane.add(lblLastName);
		
		lnameField = new JTextField();
		lnameField.setColumns(10);
		lnameField.setBounds(184, 170, 243, 26);
		contentPane.add(lnameField);
		
		lblUserName = new JLabel("User Name");
		lblUserName.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUserName.setBounds(10, 209, 174, 36);
		contentPane.add(lblUserName);
		
		unameField = new JTextField();
		unameField.setColumns(10);
		unameField.setBounds(184, 216, 243, 26);
		contentPane.add(unameField);
		
		lblEmailAddress = new JLabel("Email Address");
		lblEmailAddress.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmailAddress.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEmailAddress.setBounds(10, 288, 174, 36);
		contentPane.add(lblEmailAddress);
		
		emailField = new JTextField();
		emailField.setColumns(10);
		emailField.setBounds(184, 295, 243, 26);
		contentPane.add(emailField);
		
		lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPassword.setBounds(17, 334, 174, 36);
		contentPane.add(lblPassword);
		
		btnCancle= new JButton("Cancle");
		btnCancle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancle.setBounds(65, 482, 140, 44);
		contentPane.add(btnCancle);
		btnCancle.addActionListener(this);
		
		btnRegister = new JButton("Register");
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRegister.setBounds(284, 482, 140, 44);
		contentPane.add(btnRegister);
		btnRegister.addActionListener(this);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(184, 341, 250, 26);
		contentPane.add(passwordField);
		
		JLabel lblUserId = new JLabel("User ID");
		lblUserId.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUserId.setBounds(10, 71, 174, 36);
		contentPane.add(lblUserId);
		
		idField = new JTextField();
		
		idField.setColumns(10);
		idField.setBounds(184, 78, 243, 26);
		contentPane.add(idField);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblConfirmPassword.setBounds(10, 380, 174, 36);
		contentPane.add(lblConfirmPassword);
		
		passwordField_2 = new JPasswordField();
		passwordField_2.setBounds(184, 390, 250, 26);
		contentPane.add(passwordField_2);
		
		JLabel lblNewLabel = new JLabel("Gender");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(53, 255, 103, 26);
		contentPane.add(lblNewLabel);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Male");
		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnNewRadioButton.setBounds(184, 261, 103, 21);
		contentPane.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnFemale.setBounds(307, 259, 103, 21);
		contentPane.add(rdbtnFemale);
		
		populateInformation();
		
        
	}
	
	void populateInformation() {
		/*
		 * This function is responsible for registering users into system
		 * First all current data is pre-loaded and then user's detail is matched with data from file
		 * If username or id is matched an error is raised
		 */
		ArrayList<String []> file_data  = new ArrayList<String []>();
        try {

            Scanner myReader = new Scanner(f); //Scanner is used to read file one by one              
            
            while(myReader.hasNextLine()) {
                String data = myReader.nextLine(); //Reads data line by line
                String arr[] = data.split(","); //Split line into array
                file_data.add(arr);  // add processed data into arraylist
            }
            
            myReader.close();   
            
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
		
		ids = new ArrayList<String>();
        unames = new ArrayList<String>();
        
        for (String[] arr: file_data) {
        	unames.add(arr[3]); //Add Username
            ids.add(arr[0]);// Add Id
        }
        String nextId;
        if(ids.size()==0) {
        	nextId = "1";
        }
        else {
        	
        	nextId = Integer.toString( ids.size()+1);
        }
        idField.setText(nextId);
        idField.setEditable(false);
		idField.setEnabled(false);
		
	}
	
	
	void createFolder() {
		/*
		 * Create Folder "files" if it does not exist
		 */
		if(!f.exists()) {
			f.mkdirs();
		}
	}
	
	void readFile() {
		try {
		FileReader fr = new FileReader(f);
		}
		catch(Exception e) {
			try {
				FileWriter fw = new FileWriter(f);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	

 
    public String validate(String id, String username, String password1, String password2) {
    	/*
    	 * This Function is responsible for validating all fields in our Registartion page
    	 * It looks after password length and blank fields 
    	 */
        String validation = "true";
        int id_len = id.length();
        int un_len = username.length();
        int psw_len = password1.length();
        if (id_len == 0 || un_len == 0 || psw_len == 0) {
            validation = "All Fields are required";
        } else if(un_len<=3) {
            validation = "Username should be atleast contain 4 characters";
        } else if (psw_len<=7) {
            validation = "Password too short. Must contain atleast 8 character";
        } else if (!password1.equals(password2)) {
            validation = "Password does not match";
        }
        return validation;
    }
    
    public boolean linearSearch(String data, ArrayList<String> arr) {
    	/*
    	 * This function checks if username.id already exists in file
    	 */
        boolean exists = false;
        for(int i=0; i<arr.size(); i++) {
            if(data.equals(arr.get(i))) {
                exists = true;
                break;
            }
        }
        return exists;
}
    


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnRegister) {
            String fname = fnameField.getText();
            String lname = lnameField.getText();
            String uname = unameField.getText();
            String email = emailField.getText();
            String password1 = String.valueOf(passwordField.getPassword());
            String password2 = String.valueOf(passwordField_2.getPassword());
            String id = idField.getText();
            
            boolean id_exists = linearSearch(id, ids); //validate id is unique
            boolean uname_exists = linearSearch(uname, unames); //validate uname is unique
            
            String validation = validate(id, uname, password1, password2);
            
            if (validation != "true") {
                JOptionPane.showMessageDialog(btnRegister, validation);
            } else if(id_exists) {
                JOptionPane.showMessageDialog(btnRegister, "ID already exists. Try other ID");
                
            } else if(uname_exists) {
                JOptionPane.showMessageDialog(btnRegister, "Username already exists. Try other username");
                
            } else {
                try {
                    String data = "";
                    FileWriter fw = new FileWriter(f, true);
                    data += id+ ","+fname+ "," +lname+ "," + uname + "," +email+ "," + password1 + "\n";
                    fw.write(data);
                    fw.close(); //Writes into file if all fields are valid
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                JOptionPane.showMessageDialog(btnRegister, "Data successfully saved");
                this.dispose();
                new Login().setVisible(true);
            }
            
        };
        
        if(e.getSource() == btnCancle) {
        	this.dispose();
        }
		
	}
}
