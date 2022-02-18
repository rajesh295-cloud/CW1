package frontend;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class Login extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField userfield;
	private JPasswordField passwordField;
	private JButton loginBtn;
	private JButton resetBtn;
	private JButton btnRegister;
	ArrayList<String []> file_data;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		/**
		 * Main Methods are used for making this class executable.
		 */
		Login frame = new Login(); //create login object called frame
		frame.setVisible(true); //Set its visibility to true

	}

	/**
	 * Create the frame.
	 */
	public Login() {
		/**
		 * Constructors are used for automating certain operations in this class.
		 *  Here' its used to create frame and preload all data
		 */
		setTitle("Login Page"); //sets title for page
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //sets close operation for page
		setBounds(100, 100, 602, 534); //sets bound for page
		contentPane = new JPanel(); //create a new content plane to add data into
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); //sets border for content plane
		setContentPane(contentPane); //use the defined content plane
		contentPane.setLayout(null); //Removes default layout from content plane
		
		JLabel headTxt = new JLabel("Covid Tracking System");
		headTxt.setBounds(181, 44, 330, 32);
		headTxt.setForeground(Color.GREEN);
		headTxt.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 24));
		contentPane.add(headTxt);
		
		JLabel loginTxt = new JLabel("Staff Login");
		loginTxt.setFont(new Font("Tahoma", Font.PLAIN, 21));
		loginTxt.setHorizontalAlignment(SwingConstants.CENTER);
		loginTxt.setBounds(207, 103, 239, 32);
		contentPane.add(loginTxt);
		
		JLabel userlbl = new JLabel("UserName");
		userlbl.setHorizontalAlignment(SwingConstants.CENTER);
		userlbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		userlbl.setBounds(10, 189, 191, 38);
		contentPane.add(userlbl);
		
		userfield = new JTextField();
		userfield.setBounds(215, 189, 296, 38);
		contentPane.add(userfield);
		userfield.setColumns(10);
		
		JLabel passlbl = new JLabel("Password");
		passlbl.setHorizontalAlignment(SwingConstants.CENTER);
		passlbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		passlbl.setBounds(10, 264, 191, 38);
		contentPane.add(passlbl);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(215, 268, 296, 38);
		contentPane.add(passwordField);
		
		loginBtn = new JButton("Login");
		loginBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		loginBtn.setBounds(373, 336, 144, 38);
		contentPane.add(loginBtn);
		loginBtn.addActionListener(this);
		loginBtn.setForeground(Color.ORANGE);
		
		resetBtn = new JButton("Reset Details");
		resetBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		resetBtn.setBounds(215, 336, 144, 38);
		resetBtn.setForeground(Color.RED);
		contentPane.add(resetBtn);
		
		btnRegister = new JButton("Register");
		btnRegister.setForeground(Color.BLUE);
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnRegister.setBounds(285, 404, 166, 38);
		contentPane.add(btnRegister);
		btnRegister.addActionListener(this);
		
		JLabel lblNewUser = new JLabel("New user?");
		lblNewUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewUser.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewUser.setBounds(207, 404, 80, 38);
		contentPane.add(lblNewUser);
		
		resetBtn.addActionListener(this); //Add action listener to reset button
		populateData(); //Loads User details into array and perform authentication form it

	}
	
	public void populateData() {
        /*
         * 
         * Gets all data from users.txt and store it in
         * a Arraylist of string array for future uses
         */
        file_data = new ArrayList<String []>(); //Holds all data
        try {
            File file = new File("files\\users.txt"); //File object of user.txt
            Scanner scan = new Scanner(file);              
            
            while(scan.hasNextLine()) {
                String data = scan.nextLine(); //Reads all data line by line
                String arr[] = data.split(","); //Split that particular line into array
                file_data.add(arr); //Add that array into file_data
            }
            
            scan.close();   
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
	
    public boolean userLogin(String user, String psw) {
    	/*
    	 * This function is used to authenticate user
    	 * If user's credentials matches with that of file,
    	 * true is returned
    	 * else false is returned
    	 */
            for (String[] arr: file_data) {
                if (user.equals(arr[3]) && psw.equals(arr[5])) {
                    return true;
                }
            }   
            
            return false; 	
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == loginBtn) {
			/*
			 * Function for logging in users 
			 */
			String username = userfield.getText();
            String password = String.valueOf(passwordField.getPassword());
            
            if(username.equals("") || password.equals("")) {
            	JOptionPane.showMessageDialog(this, "Username/Passowrd Field blank"); // validates that username/pass is not blank
            }
            
            else {
	            boolean res = userLogin(username, password); //Validate that user is registered in system
	            
	            if (res) {
	            	JOptionPane.showMessageDialog(this, "User Verified");
	            	this.dispose();
	            	new Main_Window().setVisible(true);
	                
	            } else {
	                JOptionPane.showMessageDialog(this, "Username/Password Invalid");
	            }
		}}
		else if(e.getSource() == resetBtn) {
			userfield.setText(""); //Clears Username
			passwordField.setText(""); //Clears Password
		}
		
		else if(e.getSource()==btnRegister) {
			this.dispose(); //Closes login window
			new Register().setVisible(true); //Opens register page
		}
		
	}
}
