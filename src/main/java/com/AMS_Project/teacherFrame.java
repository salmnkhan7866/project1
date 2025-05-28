package com.AMS_Project;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class teacherFrame extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField tchuname;
	private JTextField tchid;
	private JTextField tchname;
	private JTextField tchpass;
	private JTextField tchdob;
	private JFileChooser chooser;
	ImageIcon icon;
	File file;
	String filePath;
	GridFSBucket gridFSBucket;

	ObjectId fileId;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					teacherFrame frame = new teacherFrame();
					frame.setBackground(new Color(255, 255, 255));
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
	
	public teacherFrame() {
		
		try {
			Client = MongoClients.create("mongodb://localhost:27017/");
			db = Client.getDatabase("AMS");
			Coll = db.getCollection("teacherData");
		} catch (MongoException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		setTitle("Teacher");
		setBorder(null);
		setBounds(0, 0, 949, 586);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 949, 559);
		panel.setBackground(new Color(255, 255, 255));
		getContentPane().add(panel);
		panel.setLayout(null);
		
		tchuname = new JTextField();
		tchuname.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				tchuname.setBorder(new LineBorder(new Color(137, 137, 137)));
			}
		});
		tchuname.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 13));
		tchuname.setColumns(10);
		tchuname.setBounds(133, 204, 178, 22);
		panel.add(tchuname);
		
		tchname = new JTextField();
		tchname.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				tchname.setBorder(new LineBorder(new Color(137, 137, 137)));
			}
		});
		tchname.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 13));
		tchname.setColumns(10);
		tchname.setBounds(514, 144, 178, 22);
		panel.add(tchname);
		
		tchpass = new JPasswordField();
		tchpass.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				tchpass.setBorder(new LineBorder(new Color(137, 137, 137)));
			}
		});
		tchpass.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 13));
		tchpass.setColumns(10);
		tchpass.setBounds(514, 201, 178, 22);
		panel.add(tchpass);
		
		tchdob = new JTextField();
		tchdob.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 13));
		tchdob.setColumns(10);
		tchdob.setBounds(514, 261, 178, 22);
		panel.add(tchdob);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setBounds(35, 207, 98, 16);
		panel.add(lblUserName);
		lblUserName.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 13));
		
		JLabel lblNewLabel = new JLabel("Teacher ID");
		lblNewLabel.setBounds(35, 147, 98, 16);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 13));
		
		JLabel lblTeacherStates = new JLabel("Teacher Status");
		lblTeacherStates.setBounds(35, 264, 98, 16);
		panel.add(lblTeacherStates);
		lblTeacherStates.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 13));
		
		JLabel lblTeacherName = new JLabel("Teacher Name");
		lblTeacherName.setBounds(414, 147, 98, 16);
		panel.add(lblTeacherName);
		lblTeacherName.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 13));
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(414, 207, 98, 16);
		panel.add(lblPassword);
		lblPassword.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 13));
		
		JLabel lblDate = new JLabel("Date of Birth");
		lblDate.setBounds(414, 264, 98, 16);
		panel.add(lblDate);
		lblDate.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 13));
		
		JLabel photoLabel = new JLabel();
		photoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		photoLabel.setBounds(742, 131, 148, 168);
		photoLabel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.add(photoLabel);
		
		String[] Gender = {"TRUE","FALSE"};
		JComboBox<?> tchstate = new JComboBox<Object>(Gender);
		tchstate.setBackground(new Color(255, 255, 255));
		tchstate.setBounds(133, 260, 178, 24);
		panel.add(tchstate);
		
		JButton tclear_1 = new JButton("UPLOAD");
		tclear_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				tclear_1.setSize(88, 26);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				tclear_1.setSize(85, 25);
			
			}
		});
		tclear_1.setForeground(new Color(255, 255, 255));
		tclear_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				chooser = new JFileChooser();
				FileNameExtensionFilter filefilter = new FileNameExtensionFilter("JPG, JPEG, PNG", "jpg", "jpeg", "png");
				chooser.setFileFilter(filefilter);
				int returnVal = chooser.showOpenDialog(getParent());
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = chooser.getSelectedFile();
					try {
						BufferedImage img = ImageIO.read(file);
						if(img != null) {
							filePath = file.getAbsolutePath();
							ImageIcon icon = new ImageIcon(fitimage(img, photoLabel.getWidth(), photoLabel.getHeight()));
							photoLabel.setIcon(icon);
						}
					} catch (IOException e2) {
						// TODO: handle exception
						e2.printStackTrace();
					}
				}
				photoLabel.setBorder(new LineBorder(new Color(0,0,0), 2));
			}
		});
		tclear_1.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 10));
		tclear_1.setBorder(null);
		tclear_1.setBackground(new Color(0, 232, 64));
		tclear_1.setBounds(732, 320, 85, 25);
		panel.add(tclear_1);
		
		JButton tdelete= new JButton("DELETE");
		tdelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				tdelete.setSize(78, 26);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				tdelete.setSize(75, 25);
			
			}
		});
		tdelete.setForeground(new Color(255, 255, 255));
		tdelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				photoLabel.setIcon(null);
				
			}
		});
		tdelete.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 10));
		tdelete.setBorder(null);
		tdelete.setBackground(new Color(255, 32, 32));
		tdelete.setBounds(827, 320, 75, 25);
		panel.add(tdelete);
		
		JButton tadd = new JButton("ADD");
		tadd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				tadd.setSize(108, 26);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				tadd.setSize(105, 25);
			}
		});
		tadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tchid.getText().isEmpty() || tchname.getText().isEmpty()
						|| tchuname.getText().isEmpty() || tchpass.getText().isEmpty()
						|| photoLabel.getIcon() == null) {
					tchid.setBorder(new LineBorder(new Color(255, 0, 0), 1));
					tchname.setBorder(new LineBorder(new Color(255, 0, 0), 1));
					tchuname.setBorder(new LineBorder(new Color(255, 0, 0), 1));
					tchpass.setBorder(new LineBorder(new Color(255, 0, 0), 1));
					photoLabel.setBorder(new LineBorder(new Color(255, 0, 0), 2));
					JOptionPane.showMessageDialog(null, "required");
				}else {
				
				try {
					//insert data to Mongodb
					Document doc= new Document("Teacher id", tchid.getText())
							.append("Teacher Name", tchname.getText())
							.append("User name", tchuname.getText())
							.append("Password", tchpass.getText())
							.append("Teacher Status", tchstate.getSelectedItem())
							.append("Date of Birth", tchdob.getText())
							.append("Image", filePath);
					
					Coll.insertOne(doc);
					
				JOptionPane.showMessageDialog(null, "Data Inserted Successfully...");
				
				tchid.setText("");
				tchname.setText("");
				tchuname.setText("");
				tchpass.setText("");
				tchdob.setText("");
				tchstate.setSelectedIndex(0);
				photoLabel.setIcon(null);
				
			} catch (MongoException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
				}
			}
		});
		tadd.setForeground(new Color(255, 255, 255));
		tadd.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 13));
		tadd.setBorder(null);
		tadd.setBackground(new Color(138, 255, 138));
		tadd.setBounds(140, 320, 105, 25);
		panel.add(tadd);
		
		JButton tremove = new JButton("REMOVE");
		tremove.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				tremove.setSize(108, 26);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				tremove.setSize(105, 25);
			
			}
		});
		tremove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					//insert data to Mongodb
					Document doc= new Document("Teacher id", tchid.getText());
					
					Coll.deleteOne(doc);
					
				JOptionPane.showMessageDialog(null, "Data Deleted Successfully...");
				
				tchid.setText("");
				tchname.setText("");
				tchuname.setText("");
				tchpass.setText("");
				tchdob.setText("");
				tchstate.setSelectedIndex(0);
				photoLabel.setIcon(null);
				
			} catch (MongoException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
				
			}
		});
		tremove.setForeground(new Color(255, 255, 255));
		tremove.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 13));
		tremove.setBorder(null);
		tremove.setBackground(new Color(255, 140, 140));
		tremove.setBounds(257, 320, 105, 25);
		panel.add(tremove);
		
		JButton tupdate = new JButton("UPDATE");
		tupdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				tupdate.setSize(108, 26);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				tupdate.setSize(105, 25);
			
			}
		});
		tupdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					Coll.updateOne(Filters.eq("Teacher id", tchid.getText()), Updates.set("Teacher id", tchid.getText()));
					Coll.updateOne(Filters.eq("Teacher id", tchid.getText()), Updates.set("Teacher Name", tchname.getText()));
					Coll.updateOne(Filters.eq("Teacher id", tchid.getText()), Updates.set("User name", tchuname.getText()));
					Coll.updateOne(Filters.eq("Teacher id", tchid.getText()), Updates.set("Password", tchpass.getText()));
					Coll.updateOne(Filters.eq("Teacher id", tchid.getText()), Updates.set("Teacher Status", tchstate.getSelectedItem()));
					Coll.updateOne(Filters.eq("Teacher id", tchid.getText()), Updates.set("Date of Birth", tchdob.getText()));
					Coll.updateOne(Filters.eq("Teacher id", tchid.getText()), Updates.set("Image", filePath));
					
					JOptionPane.showMessageDialog(null, "Data Updated Successfully...");
					
					tchid.setText("");
					tchname.setText("");
					tchuname.setText("");
					tchpass.setText("");
					tchdob.setText("");
					tchstate.setSelectedIndex(0);
					photoLabel.setIcon(null);
				
				} catch (MongoException e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
				
			}
		});
		tupdate.setForeground(new Color(255, 255, 255));
		tupdate.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 13));
		tupdate.setBorder(null);
		tupdate.setBackground(new Color(255, 193, 132));
		tupdate.setBounds(374, 320, 105, 25);
		panel.add(tupdate);
		
		JButton tclear = new JButton("CLEAR");
		tclear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				tclear.setSize(108, 26);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				tclear.setSize(105, 25);
			
			}
		});
		tclear.setForeground(new Color(255, 255, 255));
		tclear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tchuname.setText("");
				tchid.setText("");
				tchname.setText("");
				tchpass.setText("");
				tchdob.setText("");
				tchstate.setSelectedIndex(0);
				photoLabel.setIcon(null);
			}
		});
		tclear.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 13));
		tclear.setBorder(null);
		tclear.setBackground(new Color(255, 162, 208));
		tclear.setBounds(495, 320, 105, 25);
		panel.add(tclear);
		
		tchid = new JTextField();
		tchid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e){
				
				cursor = Coll.find().iterator();
				while (cursor.hasNext()) {
					Document cur = cursor.next();
					Object id = cur.get("Teacher id");
					if (id.equals(tchid.getText())) {
						Object name = cur.get("Teacher Name");
						Object uname = cur.get("User name");
						Object pass = cur.get("Password");
						Object status = cur.get("Teacher Status");
						Object dob = cur.get("Date of Birth");
						Object imgs = cur.get("Image");
						
						tchid.setText((String) id);
						tchname.setText((String) name);
						tchuname.setText((String) uname);
						tchpass.setText((String) pass);
						tchstate.setSelectedItem(status);
						tchdob.setText((String) dob);
						
						photoLabel.setIcon(new ImageIcon((String) imgs));
					}
				}
				
			}
			@Override
			public void keyPressed(KeyEvent e) {
				tchid.setBorder(new LineBorder(new Color(137, 137, 137)));
			}
		});
		tchid.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 13));
		tchid.setColumns(10);
		tchid.setBounds(133, 144, 178, 22);
		panel.add(tchid);
	}
	
	private Image fitimage(Image img, int w, int h) {
		BufferedImage resizedimage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = resizedimage.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(img, 0, 0, w, h, null);
		g2.dispose();
		return resizedimage;
		
	}
}
