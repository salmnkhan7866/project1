package com.AMS_Project;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Dashbord extends JInternalFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dashbord frame = new Dashbord();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public Dashbord() throws IOException {
		setTitle("Dashbord");
		setBorder(null);
		setBounds(0, 0, 949, 588);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 949, 561);
		panel.setBackground(new Color(255, 255, 255));
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 10, 929, 541);
		panel.add(lblNewLabel);
		
		File file = new File("C:\\Users\\Asus\\eclipse-workspace\\com.AMS_Project\\images\\school.jpg");
		BufferedImage bufferedImage = ImageIO.read(file);
		Image image = bufferedImage.getScaledInstance(943, 561, Image.SCALE_DEFAULT);
		lblNewLabel.setIcon(new ImageIcon(image));
	}
}
