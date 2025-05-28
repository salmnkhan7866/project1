package com.AMS_Project;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.bson.Document;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriterBuilder;
import com.opencsv.ICSVWriter;
import com.opencsv.exceptions.CsvValidationException;

public class batchFrame extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	
	File selectedfile, selectedfile1;
	String filePath, filePath1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					batchFrame frame = new batchFrame();
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
	
	MongoClient Client;
	MongoDatabase db;
	MongoCollection<Document> Coll, Coll1;
	MongoCursor<Document> cursor;
	
	public batchFrame() {
		
		try {
			Client = MongoClients.create("mongodb://localhost:27017/");
			db = Client.getDatabase("AMS");
			db = Client.getDatabase("AMS");
			Coll = db.getCollection("studentData");
			Coll1 = db.getCollection("teacherData");
		} catch (MongoException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		setTitle("Batch");
		setBorder(null);
		setBounds(0, 0, 962, 590);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 962, 563);
		panel.setBackground(new Color(255, 255, 255));
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("INSERT TEACHER DATA");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser chooser = new JFileChooser();
			    FileNameExtensionFilter filter = new FileNameExtensionFilter(
			        "CSV file", "csv");
			    chooser.setFileFilter(filter);
			    int returnVal = chooser.showOpenDialog(getParent());
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			    	
			    	selectedfile = chooser.getSelectedFile();
			    	filePath = selectedfile.getAbsolutePath();
			    	
			    }
				
				
				try (CSVReader reader = new CSVReaderBuilder(new FileReader(filePath)).build()) {

                    String[] headers = reader.readNext();
                    while ((headers = reader.readNext()) != null) {
                        Document document = new Document();
                        for (int i = 0; i < headers.length; i++) {
                            document.put("Teacher id", headers[0]);
                            document.put("Teacher Name", headers[1]);
                            document.put("User name", headers[2]);
                            document.put("Password", headers[3]);
                            document.put("Teacher Status", headers[4]);
                            document.put("Date of Birth", headers[5]);
                            document.put("Image", headers[6]);
                        }
                        Coll1.insertOne(document);
                    }

                    JOptionPane.showMessageDialog(null, "Import successful!");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error importing CSV: " + ex.getMessage());
                } catch (CsvValidationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 13));
		btnNewButton.setBorder(null);
		btnNewButton.setBackground(new Color(138, 255, 138));
		btnNewButton.setBounds(25, 113, 283, 126);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("DOWNLOAD TEACHER DATA");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser chooser = new JFileChooser();
				chooser.setDialogTitle("Save");
			    FileNameExtensionFilter filter = new FileNameExtensionFilter(
			        "CSV file", "csv");
			    chooser.setFileFilter(filter);
			    int returnVal = chooser.showSaveDialog(getParent());
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			    	
			    	selectedfile1 = chooser.getSelectedFile();
			    	filePath1 = selectedfile1.getAbsolutePath();
			    	
			    }
			    
			    
			    try (ICSVWriter writer = new CSVWriterBuilder(new FileWriter(filePath1)).build()) {
                    writer.writeNext(new String[]{"Teacher id", "Teacher Name", "User name", "Password", "Teacher Status", "Date of Birth","Image"}); // headers
                    for (Document document : Coll1.find()) {
                        writer.writeNext(new String[]{document.get("Teacher id").toString(), document.get("Teacher Name").toString(), document.get("User name").toString(),
                        		document.get("Password").toString(), document.get("Teacher Status").toString(), document.get("Date of Birth").toString(), document.get("Image").toString()});
                    }

                    JOptionPane.showMessageDialog(null, "Downloaded successful!");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error exporting CSV: " + ex.getMessage());
                }
			    
				
			}
		});
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 13));
		btnNewButton_1.setBorder(null);
		btnNewButton_1.setBackground(new Color(255, 140, 140));
		btnNewButton_1.setBounds(329, 113, 305, 126);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("INSERT STUDENT DATA");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser chooser = new JFileChooser();
				chooser.setDialogTitle("Open");
			    FileNameExtensionFilter filter = new FileNameExtensionFilter(
			        "CSV file", "csv");
			    chooser.setFileFilter(filter);
			    int returnVal = chooser.showOpenDialog(getParent());
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			    	
			    	selectedfile1 = chooser.getSelectedFile();
			    	filePath1 = selectedfile1.getAbsolutePath();
			    	
			    }
				
				
				try (CSVReader reader = new CSVReaderBuilder(new FileReader(filePath1)).build()) {

                    String[] headers = reader.readNext();
                    while ((headers = reader.readNext()) != null) {
                        Document document = new Document();
                        for (int i = 0; i < headers.length; i++) {
                            document.put("Student id", headers[0]);
                            document.put("Student Name", headers[1]);
                            document.put("Student Rollno", headers[2]);
                            document.put("Division", headers[3]);
                            document.put("Year", headers[4]);
                            document.put("Semester", headers[5]);
                            document.put("Phone no", headers[6]);
                            document.put("Date of Birth", headers[7]);
                        }
                        Coll.insertOne(document);
                    }

                    JOptionPane.showMessageDialog(null, "Import successful!");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error importing CSV: " + ex.getMessage());
                } catch (CsvValidationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 13));
		btnNewButton_2.setBorder(null);
		btnNewButton_2.setBackground(new Color(255, 193, 132));
		btnNewButton_2.setBounds(655, 113, 283, 126);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("DOWNLOAD STUDENT DATA");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser chooser1 = new JFileChooser();
				chooser1.setDialogTitle("Save");
			    FileNameExtensionFilter filter = new FileNameExtensionFilter(
			        "CSV file", "csv");
			    chooser1.setFileFilter(filter);
			    int returnVal = chooser1.showSaveDialog(getParent());
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			    	
			    	selectedfile1 = chooser1.getSelectedFile();
			    	filePath1 = selectedfile1.getAbsolutePath();
			    	
			    }
			    
			    
			    try (ICSVWriter writer = new CSVWriterBuilder(new FileWriter(filePath1)).build()) {

                    writer.writeNext(new String[]{"Student id", "Student Name", "Student Rollno", "Division", "Year", "Semester", "Phone no", "Date of Birth"}); // headers
                    for (Document document : Coll.find()) {
                        writer.writeNext(new String[]{document.get("Student id").toString(), document.get("Student Name").toString(), document.get("Student Rollno").toString(),
                        		document.get("Division").toString(), document.get("Year").toString(), document.get("Semester").toString(), document.get("Phone no").toString(),
                        		document.get("Date of Birth").toString()});
                    }

                    JOptionPane.showMessageDialog(null, "Downloaded successful!");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error exporting CSV: " + ex.getMessage());
                }
			    
				
			}
		});
		btnNewButton_3.setForeground(new Color(255, 255, 255));
		btnNewButton_3.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 13));
		btnNewButton_3.setBorder(null);
		btnNewButton_3.setBackground(new Color(255, 162, 208));
		btnNewButton_3.setBounds(25, 299, 283, 126);
		panel.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("VIEW TECHERS DATA");
		btnNewButton_4.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				
				ViewTeachersData viewTeachersData = new ViewTeachersData();
				viewTeachersData.show();
				
			}
		});
		btnNewButton_4.setForeground(new Color(255, 255, 255));
		btnNewButton_4.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 13));
		btnNewButton_4.setBorder(null);
		btnNewButton_4.setBackground(new Color(133, 194, 194));
		btnNewButton_4.setBounds(329, 299, 305, 126);
		panel.add(btnNewButton_4);
		
		JButton btnNewButton_2_1 = new JButton("VIEW STUDENTS DATA");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				
				ViewStudentsData viewStudentsData = new ViewStudentsData();
				viewStudentsData.show();
				
			}
		});
		btnNewButton_2_1.setForeground(Color.WHITE);
		btnNewButton_2_1.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 13));
		btnNewButton_2_1.setBorder(null);
		btnNewButton_2_1.setBackground(new Color(115, 115, 255));
		btnNewButton_2_1.setBounds(655, 299, 283, 126);
		panel.add(btnNewButton_2_1);

	}
}
