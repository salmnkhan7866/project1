package com.AMS_Project;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.bson.Document;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.awt.Font;

public class ViewTeachersData extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewTeachersData frame = new ViewTeachersData();
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
	MongoCollection<Document> Coll;
	MongoCursor<Document> cursor;
	
	public ViewTeachersData() {
		
		try {
			Client = MongoClients.create("mongodb://localhost:27017/");
			db = Client.getDatabase("AMS");
			Coll = db.getCollection("teacherData");
		} catch (MongoException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1172, 686);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 1158, 649);
		scrollPane.setBackground(new Color(255, 255, 255));
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setSurrendersFocusOnKeystroke(true);
		table.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 12));
		table.setEnabled(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBackground(new Color(255, 255, 255));
		scrollPane.setViewportView(table);
		
		 cursor = Coll.find().iterator();

		    String[] columnNames = {"Id", "Name", "Username", "Password", "Status", "birthDay"};
		    DefaultTableModel model = new DefaultTableModel(columnNames, 0);

		    while(cursor.hasNext()) {
		        Document cur = cursor.next();
		        
		        Object id = cur.get("Teacher id");
				Object name = cur.get("Teacher Name");
				Object uname = cur.get("User name");
				Object pass = cur.get("Password");
				Object status = cur.get("Teacher Status");
				Object dob = cur.get("Date of Birth");
		        
		        model.addRow(new Object[] { id, name, uname, pass, status, dob});
		    }
		    table.setModel(model);

	}
}
