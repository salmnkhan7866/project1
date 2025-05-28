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
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class studentFrame extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField stdid;
	private JTextField stdroll;
	private JTextField stdname;
	private JTextField stddiv;
	private JTextField stddob;
	private JFileChooser chooser;
	ImageIcon icon,icon1;
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
					studentFrame frame = new studentFrame();
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
	private JTextField stdPhone;
	
	public studentFrame() {
		
		try {
			Client = MongoClients.create("mongodb://localhost:27017/");
			db = Client.getDatabase("AMS");
			Coll = db.getCollection("studentData");
		} catch (MongoException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		setTitle("Student");
		setBorder(null);
		setBounds(0, 0, 949, 588);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 949, 561);
		panel.setBackground(new Color(255, 255, 255));
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblStudentId = new JLabel("Student ID");
		lblStudentId.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 13));
		lblStudentId.setBounds(39, 131, 98, 16);
		panel.add(lblStudentId);
		
		JLabel lblStudentName = new JLabel("Student Rollno");
		lblStudentName.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 13));
		lblStudentName.setBounds(39, 173, 98, 16);
		panel.add(lblStudentName);
		
		stdroll = new JTextField();
		stdroll.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				stdroll.setBorder(new LineBorder(new Color(137, 137, 137), 1));
			}
		});
		stdroll.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 13));
		stdroll.setColumns(10);
		stdroll.setBounds(137, 170, 178, 22);
		panel.add(stdroll);
		
		JLabel lblStudentName_1 = new JLabel("Student Name");
		lblStudentName_1.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 13));
		lblStudentName_1.setBounds(418, 131, 98, 16);
		panel.add(lblStudentName_1);
		
		stdname = new JTextField();
		stdname.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				stdname.setBorder(new LineBorder(new Color(137, 137, 137), 1));
			}
		});
		stdname.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 13));
		stdname.setColumns(10);
		stdname.setBounds(518, 128, 178, 22);
		panel.add(stdname);
		
		stddiv = new JTextField();
		stddiv.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				stddiv.setBorder(new LineBorder(new Color(137, 137, 137), 1));
			}
		});
		stddiv.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 13));
		stddiv.setColumns(10);
		stddiv.setBounds(518, 170, 178, 22);
		panel.add(stddiv);
		
		JLabel lblDivision = new JLabel("Division");
		lblDivision.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 13));
		lblDivision.setBounds(418, 173, 98, 16);
		panel.add(lblDivision);
		
		JLabel lblDateOfBirth = new JLabel("Date Of Birth");
		lblDateOfBirth.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 13));
		lblDateOfBirth.setBounds(418, 261, 98, 16);
		panel.add(lblDateOfBirth);
		
		stddob = new JTextField();
		stddob.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 13));
		stddob.setColumns(10);
		stddob.setBounds(518, 258, 178, 22);
		panel.add(stddob);
		
		JLabel lblPhoneNo = new JLabel("Phone no.");
		lblPhoneNo.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 13));
		lblPhoneNo.setBounds(39, 264, 98, 16);
		panel.add(lblPhoneNo);
		
		stdPhone = new JTextField();
		stdPhone.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 13));
		stdPhone.setColumns(10);
		stdPhone.setBounds(137, 258, 178, 22);
		panel.add(stdPhone);
		
		JLabel lblYear = new JLabel("Year");
		lblYear.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 13));
		lblYear.setBounds(39, 216, 98, 16);
		panel.add(lblYear);
		
		JLabel lblSemester = new JLabel("Semester");
		lblSemester.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 13));
		lblSemester.setBounds(418, 216, 98, 16);
		panel.add(lblSemester);
		

		String[] year = {"Select...","1st Year","2nd Year","3rd Year"};
		
		String[] sem = {"Select...","1st Sem","2nd Sem","3rd Sem","4th Sem","5th Sem","6th Sem"};
		
		JComboBox<Object> stdYear = new JComboBox<Object>(year);
		stdYear.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				stdYear.setBorder(new LineBorder(new Color(137, 137, 137), 1));
				
			}
		});
		stdYear.setBackground(Color.WHITE);
		stdYear.setBounds(137, 215, 178, 24);
		panel.add(stdYear);
		
		JComboBox<Object> stdSem = new JComboBox<Object>(sem);
		stdSem.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				stdSem.setBorder(new LineBorder(new Color(137, 137, 137), 1));
				
			}
		});
		stdSem.setBackground(Color.WHITE);
		stdSem.setBounds(518, 215, 178, 24);
		panel.add(stdSem);
		
		JLabel photoLabel = new JLabel();
		photoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		photoLabel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		photoLabel.setBounds(734, 128, 148, 168);
		panel.add(photoLabel);
		
		JButton tupload = new JButton("UPLOAD");
		tupload.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				tupload.setSize(88, 26);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				tupload.setSize(85, 25);
			
			}
		});
		tupload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				chooser = new JFileChooser();
				FileNameExtensionFilter filefilter = new FileNameExtensionFilter("JPG, JPEG, PNG", "jpg", "jpeg", "png");
				chooser.setFileFilter(filefilter);
				
				int returnVal = chooser.showOpenDialog(getParent());
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					file = chooser.getSelectedFile();
					try {
						BufferedImage img = ImageIO.read(file);
						if(img != null) {
							filePath = file.getAbsolutePath();
							icon = new ImageIcon(fitimage(img, photoLabel.getWidth(), photoLabel.getHeight()));
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
		tupload.setForeground(Color.WHITE);
		tupload.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 10));
		tupload.setBorder(null);
		tupload.setBackground(new Color(0, 232, 64));
		tupload.setBounds(724, 317, 85, 25);
		panel.add(tupload);
		
		JButton tdelete = new JButton("DELETE");
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
		tdelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				photoLabel.setIcon(null);
				
			}
		});
		tdelete.setForeground(Color.WHITE);
		tdelete.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 10));
		tdelete.setBorder(null);
		tdelete.setBackground(new Color(255, 32, 32));
		tdelete.setBounds(819, 317, 75, 25);
		panel.add(tdelete);
		
		JButton btnNewButton_3 = new JButton("CLEAR");
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton_3.setSize(108, 26);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton_3.setSize(105, 25);
			
			}
		});
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {	
				stdid.setText("");
				stdname.setText("");
				stdroll.setText("");
				stddiv.setText("");
				stddob.setText("");
				stdPhone.setText("");
				stdYear.setSelectedIndex(0);
				stdSem.setSelectedIndex(0);
				photoLabel.setIcon(null);
				
			} catch (MongoException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
				
			}
		});
		btnNewButton_3.setForeground(new Color(255, 255, 255));
		btnNewButton_3.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 13));
		btnNewButton_3.setBorder(null);
		btnNewButton_3.setBackground(new Color(255, 162, 208));
		btnNewButton_3.setBounds(492, 317, 105, 25);
		panel.add(btnNewButton_3);
		
		JButton btnNewButton_2 = new JButton("UPDATE");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton_2.setSize(108, 26);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton_2.setSize(105, 25);
			
			}
		});
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					Coll.updateOne(Filters.eq("Student id", stdid.getText()), Updates.set("Student id", stdid.getText()));
					Coll.updateOne(Filters.eq("Student id", stdid.getText()), Updates.set("Student Name", stdname.getText()));
					Coll.updateOne(Filters.eq("Student id", stdid.getText()), Updates.set("Student Rollno", stdroll.getText()));
					Coll.updateOne(Filters.eq("Student id", stdid.getText()), Updates.set("Division", stddiv.getText()));
					Coll.updateOne(Filters.eq("Student id", stdid.getText()), Updates.set("Date of Birth", stddob.getText()));
					Coll.updateOne(Filters.eq("Student id", stdid.getText()), Updates.set("Year", stdYear.getSelectedItem()));
					Coll.updateOne(Filters.eq("Student id", stdid.getText()), Updates.set("Semester", stdSem.getSelectedItem()));
					Coll.updateOne(Filters.eq("Student id", stdid.getText()), Updates.set("Phone no", stdPhone.getText()));
					Coll.updateOne(Filters.eq("Student id", stdid.getText()), Updates.set("Image", filePath));
					
					JOptionPane.showMessageDialog(null, "Data Updated Successfully...");
					
					stdid.setText("");
					stdname.setText("");
					stdroll.setText("");
					stddiv.setText("");
					stddob.setText("");
					stdPhone.setText("");
					stdYear.setSelectedIndex(0);
					stdSem.setSelectedIndex(0);
					photoLabel.setIcon(null);
					
				} catch (MongoException e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
				
			}
		});
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 13));
		btnNewButton_2.setBorder(null);
		btnNewButton_2.setBackground(new Color(255, 193, 132));
		btnNewButton_2.setBounds(371, 317, 105, 25);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_1 = new JButton("REMOVE");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton_1.setSize(108, 26);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton_1.setSize(105, 25);
			
			}
		});
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					//insert data to Mongodb
					Document doc1= new Document("Student id", stdid.getText());
					
					Coll.deleteOne(doc1);
					
					JOptionPane.showMessageDialog(null, "Data Deleted Successfully...");
				
					stdid.setText("");
					stdname.setText("");
					stdroll.setText("");
					stddiv.setText("");
					stddob.setText("");
					stdPhone.setText("");
					stdYear.setSelectedIndex(0);
					stdSem.setSelectedIndex(0);
					photoLabel.setIcon(null);
				
			} catch (MongoException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
				
			}
		});
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 13));
		btnNewButton_1.setBorder(null);
		btnNewButton_1.setBackground(new Color(255, 140, 140));
		btnNewButton_1.setBounds(254, 317, 105, 25);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("ADD");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton.setSize(108, 26);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton.setSize(105, 25);
			
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (stdid.getText().isEmpty() || stdname.getText().isEmpty() 
						|| stdroll.getText().isEmpty() || stddiv.getText().isEmpty() 
						|| photoLabel.getIcon() == null || stdSem.getSelectedItem().equals(stdSem.getItemAt(0))
						|| stdYear.getSelectedItem().equals(stdYear.getItemAt(0))) {
					stdid.setBorder(new LineBorder(new Color(255, 0, 0), 1));
					stdname.setBorder(new LineBorder(new Color(255, 0, 0), 1));
					stdroll.setBorder(new LineBorder(new Color(255, 0, 0), 1));
					stddiv.setBorder(new LineBorder(new Color(255, 0, 0), 1));
					stdSem.setBorder(new LineBorder(new Color(255, 0, 0), 1));
					stdYear.setBorder(new LineBorder(new Color(255, 0, 0), 1));
					photoLabel.setBorder(new LineBorder(new Color(255, 0, 0), 2));
					JOptionPane.showMessageDialog(null, "required");
				}else {
				
				try {
						//insert data to Mongodb
						Document doc= new Document("Student id", stdid.getText())
								.append("Student Name", stdname.getText())
								.append("Student Rollno", stdroll.getText())
								.append("Division", stddiv.getText())
								.append("Year", stdYear.getSelectedItem())
								.append("Semester", stdSem.getSelectedItem())
								.append("Phone no", stdPhone.getText())
								.append("Date of Birth", stddob.getText())
								.append("Image", filePath);
						
						Coll.insertOne(doc);
						
					JOptionPane.showMessageDialog(null, "Data Inserted Successfully...");
					
					stdid.setText("");
					stdname.setText("");
					stdroll.setText("");
					stddiv.setText("");
					stddob.setText("");
					stdPhone.setText("");
					stdYear.setSelectedIndex(0);
					stdSem.setSelectedIndex(0);
					photoLabel.setIcon(null);
					
				} catch (MongoException e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
				}
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 13));
		btnNewButton.setBorder(null);
		btnNewButton.setBackground(new Color(138, 255, 138));
		btnNewButton.setBounds(137, 317, 105, 25);
		panel.add(btnNewButton);

		stdid = new JTextField();
		stdid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				try {
					
					cursor = Coll.find().iterator();
					
					while(cursor.hasNext()) {
						Document cur = cursor.next();
						Object id = cur.get("Student id");
						if (id.equals(stdid.getText())) {
							
							Object name = cur.get("Student Name");
							Object rollno = cur.get("Student Rollno");
							Object div = cur.get("Division");
							Object birth = cur.get("Date of Birth");
							Object phone = cur.get("Phone no");
							Object year = cur.get("Year");
							Object sem = cur.get("Semester");
							Object imgs = cur.get("Image");
							
							stdname.setText((String) name);
							stdroll.setText((String) rollno);
							stddiv.setText((String) div);
							stddob.setText((String) birth);
							stdPhone.setText((String) phone);
							stdYear.setSelectedItem(year);
							stdSem.setSelectedItem(sem);
							
							photoLabel.setIcon(new ImageIcon((String) imgs));
						}
					}
					
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
				
			}
			@Override
			public void keyPressed(KeyEvent e) {
				stdid.setBorder(new LineBorder(new Color(137, 137, 137)));
			}
		});
		stdid.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 13));
		stdid.setColumns(10);
		stdid.setBounds(137, 128, 178, 22);
		panel.add(stdid);
		
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
