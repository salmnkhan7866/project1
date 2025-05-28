package HHH;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import org.bson.Document;
import org.opencv.core.Core;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AttendanceSheet11 extends JFrame {

    private static final long serialVersionUID = 1L;
    JTable table;
    JTextField txtrollno, txtid;
    ImageIcon icon;
    String filePath;

    Object Tid, Tname, Tuname, Tpass, Tstatus, Tdob;
    Object id, name, rollno, div, birth, phone, year, sem, imgs;

    DefaultTableModel model, model1;

    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        EventQueue.invokeLater(() -> {
            try {
                AttendanceSheet11 frame = new AttendanceSheet11();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    MongoClient Client;
    MongoDatabase db;
    MongoCollection<Document> Coll, Coll1;
    MongoCursor<Document> cursor, cursor1;

    public AttendanceSheet11() {

        try {
            Client = MongoClients.create("mongodb://localhost:27017/");
            db = Client.getDatabase("AMS");
            Coll = db.getCollection("studentData");
            Coll1 = db.getCollection("teacherData");
        } catch (MongoException e) {
            e.printStackTrace();
        }

        setTitle("Attendance Sheet");
        setBounds(120, 70, 948, 737);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setBackground(new Color(255, 255, 255));
        getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 1213, 700);
        panel.setBackground(new Color(0, 180, 226));
        getContentPane().add(panel);
        panel.setLayout(null);

        JButton btnDone = new JButton("Done");
        btnDone.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnDone.setBackground(new Color(255, 255, 255));
                btnDone.setForeground(new Color(0, 181, 226));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnDone.setForeground(new Color(255, 255, 255));
                btnDone.setBackground(new Color(0, 181, 226));
            }
        });
        btnDone.addActionListener(e -> {
            cursor1 = Coll1.find().iterator();

            while (cursor1.hasNext()) {
                Document cur1 = cursor1.next();
                Tid = cur1.get("Teacher id");
                Tname = cur1.get("Teacher Name");
                Tuname = cur1.get("User name");
                Tpass = cur1.get("Password");
                Tstatus = cur1.get("Teacher Status");
                Tdob = cur1.get("Date of Birth");

                if (txtid.getText().equals(Tid)) {
                    model.addRow(new Object[]{Tid, Tname, "", "", Tdob, "", "", "", "present"});
                }
            }
            cursor1.close();
            txtid.setText("");

            cursor = Coll.find().iterator();

            while (cursor.hasNext()) {
                Document cur = cursor.next();
                id = cur.get("Student id");
                name = cur.get("Student Name");
                rollno = cur.get("Student Rollno");
                div = cur.get("Division");
                birth = cur.get("Date of Birth");
                phone = cur.get("Phone no");
                year = cur.get("Year");
                sem = cur.get("Semester");
                imgs = cur.get("Images");

                if (txtrollno.getText().equals(rollno)) {
                    model.addRow(new Object[]{id, name, rollno, div, birth, phone,year, sem,"present"});
                }
            }
                txtrollno.setText("");
            cursor.close();
        });
        btnDone.setForeground(Color.WHITE);
        btnDone.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 15));
        btnDone.setBorder(new LineBorder(new Color(255, 255, 255), 2));
        btnDone.setBackground(new Color(0, 181, 226));
        btnDone.setBounds(635, 23, 267, 39);
        panel.add(btnDone);

        JLabel lblNewLabel_1 = new JLabel("Roll no");
        lblNewLabel_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 14));
        lblNewLabel_1.setBounds(30, 33, 55, 20);
        panel.add(lblNewLabel_1);

        txtrollno = new JTextField();
        txtrollno.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 14));
        txtrollno.setBounds(95, 30, 220, 26);
        panel.add(txtrollno);
        txtrollno.setColumns(10);

        txtid = new JTextField();
        txtid.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 14));
        txtid.setColumns(10);
        txtid.setBounds(390, 30, 220, 26);
        panel.add(txtid);

        JLabel lblNewLabel_1_1 = new JLabel("Id");
        lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_1.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 14));
        lblNewLabel_1_1.setBounds(325, 33, 55, 20);
        panel.add(lblNewLabel_1_1);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(30, 70, 872, 560);
        panel.add(scrollPane);

        table = new JTable();
        table.setShowGrid(false);
        table.setShowHorizontalLines(false);
        table.setShowVerticalLines(false);
        table.setRowSelectionAllowed(false);
        table.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 12));
        scrollPane.setViewportView(table);
        String[] columnNames = {"ID", "Name", "Rollno", "Division", "Birthday", "Phone", "Year", "Semester", "Status"};
        model = new DefaultTableModel(columnNames, 0);
        table.setModel(model);

        JButton btnSave = new JButton("Save");
        btnSave.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnSave.setBackground(new Color(255, 255, 255));
                btnSave.setForeground(new Color(0, 181, 226));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnSave.setForeground(new Color(255, 255, 255));
                btnSave.setBackground(new Color(0, 181, 226));
            }
        });
        btnSave.addActionListener(e -> {

            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Save");
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "CSV file", "csv");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showSaveDialog(getParent());
            if (returnVal == JFileChooser.APPROVE_OPTION) {

                File selectedfile = chooser.getSelectedFile();
                filePath = selectedfile.getAbsolutePath();

                if (!filePath.endsWith(".csv")) {
                    filePath += ".csv";
                }
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                // Write column headers
                for (int i = 0; i < model.getColumnCount(); i++) {
                    writer.write(model.getColumnName(i));
                    if (i < model.getColumnCount() - 1) writer.write(",");
                }
                writer.newLine();

                // Write data rows
                for (int i = 0; i < model.getRowCount(); i++) {
                    for (int j = 0; j < model.getColumnCount(); j++) {
                        Object value = model.getValueAt(i, j);
                        writer.write(value != null ? value.toString() : "");
                        if (j < model.getColumnCount() - 1) writer.write(",");
                    }
                    writer.newLine();
                }
                JOptionPane.showMessageDialog(null, "Data saved to CSV file successfully.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error saving data: " + ex.getMessage());
            }

        });
        btnSave.setForeground(Color.WHITE);
        btnSave.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 15));
        btnSave.setBorder(new LineBorder(new Color(255, 255, 255), 2));
        btnSave.setBackground(new Color(0, 181, 226));
        btnSave.setBounds(358, 645, 267, 39);
        panel.add(btnSave);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnCancel.setBackground(new Color(255, 255, 255));
                btnCancel.setForeground(new Color(0, 181, 226));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnCancel.setForeground(new Color(255, 255, 255));
                btnCancel.setBackground(new Color(0, 181, 226));
            }
        });
        btnCancel.setForeground(Color.WHITE);
        btnCancel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 15));
        btnCancel.setBorder(new LineBorder(new Color(255, 255, 255), 2));
        btnCancel.setBackground(new Color(0, 181, 226));
        btnCancel.setBounds(635, 645, 267, 39);
        panel.add(btnCancel);
    }
}

