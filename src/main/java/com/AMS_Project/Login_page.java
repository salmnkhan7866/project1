package com.AMS_Project;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import org.bson.Document;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class Login_page {

	private JFrame frame;
	private JPanel panel;
	private JTextField txtuser;
	private JPasswordField txtpass;
	private JPasswordField txtcpass;
	private JPasswordField txtpass1;
	private JTextField txtuser1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login_page window = new Login_page();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login_page() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private MongoClient Client;
	private MongoDatabase db;
	private MongoCollection<Document> Coll;

	private void initialize() {

		try {
			Client = MongoClients.create("mongodb://localhost:27017/");
			db = Client.getDatabase("AMS");
			Coll = db.getCollection("LoginData");
		} catch (MongoException e) {
			e.printStackTrace();
		}

		frame = new JFrame("Login");
		frame.setBounds(100, 100, 843, 543);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);

		panel = new JPanel();
		panel.setBackground(new Color(0, 180, 226));
		panel.setBounds(0, 0, 425, 514);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Create an account");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel.setBounds(121, 424, 189, 21);
		panel.add(lblNewLabel);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(418, -30, 425, 554);
		frame.getContentPane().add(tabbedPane);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("SignUp", null, panel_1, null);

		JLabel lblNewLabel_1 = new JLabel("Sign Up");
		lblNewLabel_1.setForeground(new Color(0, 180, 226));
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(164, 10, 97, 32);
		panel_1.add(lblNewLabel_1);

		JLabel txtlabl = new JLabel("Username");
		txtlabl.setForeground(new Color(0, 180, 226));
		txtlabl.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 14));
		txtlabl.setBounds(50, 92, 71, 13);
		panel_1.add(txtlabl);

		txtuser = new JTextField();
		txtlabl.setLabelFor(txtuser);
		txtuser.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 14));
		txtuser.setForeground(new Color(0, 180, 226));
		txtuser.setBounds(50, 115, 310, 32);
		txtuser.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 180, 226)));
		panel_1.add(txtuser);
		txtuser.setColumns(10);

		txtpass = new JPasswordField();
		txtpass.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 14));
		txtpass.setForeground(new Color(0, 180, 226));
		txtpass.setColumns(10);
		txtpass.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 180, 226)));
		txtpass.setBounds(50, 205, 310, 32);
		panel_1.add(txtpass);

		JLabel lblNewLabel_2_1 = new JLabel("Password");
		lblNewLabel_2_1.setLabelFor(txtpass);
		lblNewLabel_2_1.setForeground(new Color(0, 180, 226));
		lblNewLabel_2_1.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_2_1.setBounds(50, 182, 71, 13);
		panel_1.add(lblNewLabel_2_1);

		txtcpass = new JPasswordField();
		txtcpass.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 14));
		txtcpass.setForeground(new Color(0, 180, 226));
		txtcpass.setColumns(10);
		txtcpass.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 180, 226)));
		txtcpass.setBounds(50, 292, 310, 32);
		panel_1.add(txtcpass);

		JLabel lblNewLabel_2_2 = new JLabel("Confirm Password");
		lblNewLabel_2_2.setLabelFor(txtcpass);
		lblNewLabel_2_2.setForeground(new Color(0, 180, 226));
		lblNewLabel_2_2.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_2_2.setBounds(50, 269, 122, 13);
		panel_1.add(lblNewLabel_2_2);

		JButton btnNewButton = new JButton("Sign Up");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Document doc = new Document("Username", txtuser.getText())
						.append("Password", new String(txtpass.getPassword()));

				if (new String(txtcpass.getPassword()).equals(new String(txtpass.getPassword()))) {
					Coll.insertOne(doc);
					JOptionPane.showMessageDialog(frame, "Sign Up Successfully");
				} else {
					JOptionPane.showMessageDialog(frame, "Passwords do not match");
				}
			}
		});
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 15));
		btnNewButton.setBounds(50, 373, 89, 32);
		btnNewButton.setBackground(new Color(0, 180, 226));
		panel_1.add(btnNewButton);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("SignIn", null, panel_2, null);

		JButton btnSignIn = new JButton("Sign In");
		btnSignIn.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean found = false;
				try (MongoCursor<Document> cursor = Coll.find().iterator()) {
					while (cursor.hasNext()) {
						Document cur = cursor.next();

						String user = cur.getString("Username");
						String pass = cur.getString("Password");

						if (txtuser1.getText().equals(user) && new String(txtpass1.getPassword()).equals(pass)) {
							JOptionPane.showMessageDialog(frame, "Login Successful");
							found = true;
							Main main;
							try {
								main = new Main();
								main.show();
								break;
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
						}
					}
				}

				if (!found) {
					JOptionPane.showMessageDialog(frame, "Invalid Username or Password");
				}
			}
		});
		btnSignIn.setForeground(Color.WHITE);
		btnSignIn.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 15));
		btnSignIn.setBackground(new Color(0, 180, 226));
		btnSignIn.setBounds(50, 373, 89, 32);
		panel_2.add(btnSignIn);

		txtpass1 = new JPasswordField();
		txtpass1.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 14));
		txtpass1.setForeground(new Color(0, 180, 226));
		txtpass1.setColumns(10);
		txtpass1.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 180, 226)));
		txtpass1.setBounds(50, 191, 310, 32);
		panel_2.add(txtpass1);

		JLabel lblNewLabel_2_2_1 = new JLabel("Password");
		lblNewLabel_2_2_1.setLabelFor(txtpass1);
		lblNewLabel_2_2_1.setForeground(new Color(0, 180, 226));
		lblNewLabel_2_2_1.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_2_2_1.setBounds(50, 168, 71, 13);
		panel_2.add(lblNewLabel_2_2_1);

		txtuser1 = new JTextField();
		txtuser1.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 14));
		txtuser1.setForeground(new Color(0, 180, 226));
		txtuser1.setColumns(10);
		txtuser1.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 180, 226)));
		txtuser1.setBounds(50, 104, 310, 32);
		panel_2.add(txtuser1);

		JLabel lblNewLabel_2_1_1 = new JLabel("Username");
		lblNewLabel_2_1_1.setLabelFor(txtuser1);
		lblNewLabel_2_1_1.setForeground(new Color(0, 180, 226));
		lblNewLabel_2_1_1.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_2_1_1.setBounds(50, 81, 71, 13);
		panel_2.add(lblNewLabel_2_1_1);

		JLabel lblNewLabel_1_1 = new JLabel("Sign In");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setForeground(new Color(0, 180, 226));
		lblNewLabel_1_1.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel_1_1.setBounds(160, 10, 97, 32);
		panel_2.add(lblNewLabel_1_1);

		JToggleButton tglbtnNewToggleButton_1 = new JToggleButton("Sign Up");
		tglbtnNewToggleButton_1.setForeground(new Color(255, 255, 255));
		tabbedPane.setSelectedIndex(1);
		tglbtnNewToggleButton_1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (tglbtnNewToggleButton_1.isSelected()) {
					tglbtnNewToggleButton_1.setText("Sign In");
					lblNewLabel.setText("Already have an account?");
					tabbedPane.setSelectedIndex(0);
				} else {
					tglbtnNewToggleButton_1.setText("Sign Up");
					lblNewLabel.setText("Create an account");
					tabbedPane.setSelectedIndex(1);
				}
			}
		});
		tglbtnNewToggleButton_1.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 14));
		tglbtnNewToggleButton_1.setBackground(new Color(0, 180, 226));
		tglbtnNewToggleButton_1.setBorder(new LineBorder(new Color(255, 255, 255)));
		tglbtnNewToggleButton_1.setBounds(163, 455, 96, 21);
		panel.add(tglbtnNewToggleButton_1);

		JLabel lblNewLabel_3 = new JLabel("Welcome");
		lblNewLabel_3.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 69));
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(0, 189, 415, 119);
		panel.add(lblNewLabel_3);
	}
}
