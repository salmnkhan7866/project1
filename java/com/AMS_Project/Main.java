package com.AMS_Project;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import HHH.AttendanceSheet11;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the 
	 * @throws IOException 
	 */
	public Main() throws IOException {
		setTitle("Attendance Management System");
		setBounds(100, 100, 1213, 697);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 180, 226));
		panel.setBorder(new LineBorder(new Color(0,181,226), 2));
		panel.setBounds(0, 0, 250, 661);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(237, 0, 967, 81);
		getContentPane().add(panel_1);
		panel_1.setBorder(new LineBorder(new Color(0,181,226), 2));
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setLayout(null);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(251, 81, 957, 580);
		getContentPane().add(desktopPane);
		desktopPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Admin Panel");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 22));
		lblNewLabel_1.setBounds(0, 59, 250, 53);
		panel.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("DASHBORD");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton.setBackground(new Color(255, 255, 255));
				btnNewButton.setForeground(new Color(0, 181, 226));
	    		
	    	}
	    	@Override
	    	public void mouseExited(MouseEvent e) {
	    		
	    		btnNewButton.setForeground(new Color(255, 255, 255));
				btnNewButton.setBackground(new Color(0, 181, 226));
				
	    	
			}
		});
		Dashbord Dashbord = new Dashbord();
		Dashbord.setBorder(null);
		Dashbord.setBounds(0, 0, 949, 580);
		desktopPane.add(Dashbord);
		Dashbord.show();
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					desktopPane.removeAll();
					desktopPane.add(Dashbord);
					Dashbord.show();
				}catch (Exception ex){
					System.out.println(ex);
				}
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 15));
		btnNewButton.setBounds(10, 123, 230, 39);
		btnNewButton.setBackground(new Color(0,181,226));
		btnNewButton.setBorder(new LineBorder(new Color(255, 255, 255), 2));
		panel.add(btnNewButton);
		
		JButton btnTeacher = new JButton("TEACHER");
		btnTeacher.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnTeacher.setBackground(new Color(255, 255, 255));
				btnTeacher.setForeground(new Color(0, 181, 226));
	    		
	    	}
	    	@Override
	    	public void mouseExited(MouseEvent e) {
	    		
	    		btnTeacher.setForeground(new Color(255, 255, 255));
				btnTeacher.setBackground(new Color(0, 181, 226));
				
	    	
			}
		});
		btnTeacher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					teacherFrame teacherFrame = new teacherFrame();
					desktopPane.removeAll();
					desktopPane.add(teacherFrame);
					teacherFrame.show();
				} catch (Exception ex){
					System.out.println(ex);
				}
			}
		});
		btnTeacher.setForeground(new Color(255, 255, 255));
		btnTeacher.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 15));
		btnTeacher.setBounds(10, 183, 230, 39);
		btnTeacher.setBackground(new Color(0,181,226));
		btnTeacher.setBorder(new LineBorder(new Color(255, 255, 255), 2));
		panel.add(btnTeacher);
		
		JButton btnStudent = new JButton("STUDENT");
		btnStudent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnStudent.setBackground(new Color(255, 255, 255));
				btnStudent.setForeground(new Color(0, 181, 226));
	    		
	    	}
	    	@Override
	    	public void mouseExited(MouseEvent e) {
	    		
	    		btnStudent.setForeground(new Color(255, 255, 255));
				btnStudent.setBackground(new Color(0, 181, 226));
				
	    	
			}
		});
		btnStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				studentFrame studentFrame = new studentFrame();
				desktopPane.removeAll();
				desktopPane.add(studentFrame);
				studentFrame.show();
			}
		});
		btnStudent.setForeground(new Color(255, 255, 255));
		btnStudent.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 15));
		btnStudent.setBounds(10, 240, 230, 39);
		btnStudent.setBackground(new Color(0,181,226));
		btnStudent.setBorder(new LineBorder(new Color(255, 255, 255), 2));
		panel.add(btnStudent);
		
		JButton btnBatch = new JButton("IMPORT/EXPORT");
		btnBatch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnBatch.setBackground(new Color(255, 255, 255));
				btnBatch.setForeground(new Color(0, 181, 226));
	    		
	    	}
	    	@Override
	    	public void mouseExited(MouseEvent e) {
	    		
	    		btnBatch.setForeground(new Color(255, 255, 255));
				btnBatch.setBackground(new Color(0, 181, 226));
				
	    	
			}
		});
		btnBatch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				batchFrame batchFrame = new batchFrame();
				desktopPane.removeAll();
				desktopPane.add(batchFrame);
				batchFrame.show();
			}
		});
		btnBatch.setForeground(new Color(255, 255, 255));
		btnBatch.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 15));
		btnBatch.setBounds(10, 298, 230, 39);
		btnBatch.setBackground(new Color(0,181,226));
		btnBatch.setBorder(new LineBorder(new Color(255, 255, 255), 2));
		panel.add(btnBatch);
		
		JButton btnExit = new JButton("LOGOUT");
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				btnExit.setBackground(new Color(255, 255, 255));
				btnExit.setForeground(new Color(0, 181, 226));
	    		
	    	}
	    	@Override
	    	public void mouseExited(MouseEvent e) {
	    		
	    		btnExit.setForeground(new Color(255, 255, 255));
				btnExit.setBackground(new Color(0, 181, 226));
				
			}
		});
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Logout Successfull");
				System.exit(0);
			}
		});
		btnExit.setForeground(new Color(255, 255, 255));
		btnExit.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 15));
		btnExit.setBounds(10, 611, 230, 39);
		btnExit.setBackground(new Color(0,181,226));
		btnExit.setBorder(new LineBorder(new Color(255, 255, 255), 2));
		panel.add(btnExit);
		
		JButton btnAttendenceManage = new JButton("ATTENDENCE MANAGE");
		btnAttendenceManage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				btnAttendenceManage.setBackground(new Color(255, 255, 255));
				btnAttendenceManage.setForeground(new Color(0, 181, 226));
	    		
	    	}
	    	@Override
	    	public void mouseExited(MouseEvent e) {
	    		
	    		btnAttendenceManage.setForeground(new Color(255, 255, 255));
				btnAttendenceManage.setBackground(new Color(0, 181, 226));
				
			}
		});
		btnAttendenceManage.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				
				AttendanceSheet attendanceSheet = new AttendanceSheet();
				attendanceSheet.show();
				
			}
		});
		btnAttendenceManage.setForeground(Color.WHITE);
		btnAttendenceManage.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 15));
		btnAttendenceManage.setBorder(new LineBorder(new Color(255, 255, 255), 2));
		btnAttendenceManage.setBackground(new Color(0, 181, 226));
		btnAttendenceManage.setBounds(10, 359, 230, 39);
		panel.add(btnAttendenceManage);
		
		DateFormat dateFormat = new SimpleDateFormat("EEEE ',' dd MMMMM yyyy");
		
		Date date = new Date();
		
		Label labelTime = new Label();
		labelTime.setBounds(777, 21, 167, 22);
		panel_1.add(labelTime);
		labelTime.setForeground(new Color(0,181,226));
		labelTime.setAlignment(Label.RIGHT);
		
		Label labelDate = new Label();
		labelDate.setBounds(777, 49, 167, 22);
		panel_1.add(labelDate);
		labelDate.setForeground(new Color(0,181,226));
		labelDate.setText(dateFormat.format(date).toString());
		labelDate.setAlignment(Label.RIGHT);
		
		Thread t = new Thread(){
            public void run() {
              for (;;){
                  try {
                      sleep(1000);
                  } catch (InterruptedException ex) {
                      System.out.println(ex.toString());
                  }
                  SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss");
                  Calendar cal = Calendar.getInstance();
                  labelTime.setText(time.format(cal.getTime()));
                  
              }
          }  
        };
        
        t.start();
		
		JLabel lblNewLabel = new JLabel("ATTENDANCE MANAGEMENT SYSTEM");
		lblNewLabel.setBounds(253, 21, 493, 37);
		panel_1.add(lblNewLabel);
		lblNewLabel.setForeground(new Color(0,181,226));
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 27));
	}
}
