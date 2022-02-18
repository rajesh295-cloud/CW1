package backend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;

public class FileOperations {
	/*
	 * This function performs all file operations of our program
	 */
	static String data="";
	private static ArrayList<String[]> result;
	private static BufferedReader br;
	private static Object[] tableLines;
	public static boolean writeToMatrix(int[][] matrix) {
		 try {
			 StringBuilder builder = new StringBuilder();
			 for(int i = 0; i < matrix.length; i++)//for each row
			 {
			    for(int j = 0; j < matrix.length; j++)//for each column
			    {
			       builder.append(matrix[i][j]+"");//append to the output string
			       if(j < matrix.length - 1)//if this is not the last row element
			          builder.append(",");//then add comma (if you don't like commas you can use spaces)
			    }
			    builder.append("\n");//append new line at the end of the row
			 }
			 BufferedWriter writer = new BufferedWriter(new FileWriter("files\\matrix.txt"));
			 writer.write(builder.toString());//save the string representation of the board
			 writer.close();
			 
			 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		 return false;
		 
	}
	
	public static boolean updatePatients(ArrayList<String[]> record) {
		/*
		 * This function updates patient's details
		 */
		String data;
		try {
			File patients = new File("files\\patients.txt"); //Get file object for patient file
			FileWriter fw = new FileWriter(patients);//Get filewriter object for patient file
	        BufferedWriter bw = new BufferedWriter(fw);//Get bufferedwriter object for patient file
	        int lim = record.size();
			for(int i=0;i<lim;i++) {
				 data = record.get(i)[0]+ ","+record.get(i)[1]+ "," +record.get(i)[2]+ "," + record.get(i)[3] + "," +record.get(i)[4]+ "," + record.get(i)[5] + "," + record.get(i)[6]+", " +record.get(i)[7].replaceAll("\\s+", " ") + "," + record.get(i)[8].replaceAll("\\s+", " ") + "," + record.get(i)[9].replaceAll("\\s+", " ") + "\n";
				 bw.write(data);
			}
			bw.close();// close connection after file is overwritten
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static ArrayList<String []> readFile(File f){
		/*
		 * reads from any given file
		 */
		result  = new ArrayList<String []>();
        try {
        	
        	br = new BufferedReader(new FileReader(f));
        	
        	tableLines = br.lines().toArray();
        	for(int i=0;i<tableLines.length;i++) {
        		String line = tableLines[i].toString().trim();
        		String[] dataRow = line.split(",");
        		result.add(dataRow);
        	}
        	
        	 
            
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
		return result;
      

	}

}
