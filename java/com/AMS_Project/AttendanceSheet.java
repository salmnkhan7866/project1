package com.AMS_Project;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import org.bson.Document;
import org.opencv.core.Core;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.AbstractCellEditor;

public class AttendanceSheet extends JFrame {

    private static final long serialVersionUID = 1L;
    JTable table;
    String filePath;
    DefaultTableModel model;

    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        EventQueue.invokeLater(() -> {
            try {
                AttendanceSheet frame = new AttendanceSheet();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    MongoClient Client;
    MongoDatabase db;
    MongoCollection<Document> Coll;

    public AttendanceSheet() {

        try {
            Client = MongoClients.create("mongodb://localhost:27017/");
            db = Client.getDatabase("AMS");
            Coll = db.getCollection("studentData");
        } catch (MongoException e) {
            e.printStackTrace();
        }

        setTitle("Attendance Sheet");
        setBounds(120, 70, 1174, 680);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setBackground(new Color(255, 255, 255));
        getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 1160, 661);
        panel.setBackground(new Color(0, 180, 226));
        getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("Semester");
        lblNewLabel_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 14));
        lblNewLabel_1.setBounds(20, 20, 72, 20);
        panel.add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("Div");
        lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_1.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 14));
        lblNewLabel_1_1.setBounds(300, 20, 55, 20);
        panel.add(lblNewLabel_1_1);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 60, 1116, 519);
        panel.add(scrollPane);

        table = new JTable();
        table.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        scrollPane.setViewportView(table);
        String[] columnNames = {"ID", "Name", "Rollno", "Division", "Birthday", "Phone", "Year", "Semester", "Status"};
        model = new DefaultTableModel(columnNames, 0);
        table.setModel(model);

        MongoCursor<Document> cursor = Coll.find().iterator();

        while (cursor.hasNext()) {
            Document cur = cursor.next();

            Object id = cur.get("Student id");
            Object name = cur.get("Student Name");
            Object rollno = cur.get("Student Rollno");
            Object div = cur.get("Division");
            Object birth = cur.get("Date of Birth");
            Object phone = cur.get("Phone no");
            Object year = cur.get("Year");
            Object sem = cur.get("Semester");

            model.addRow(new Object[]{id, name, rollno, div, birth, phone, year, sem, "Absent"});
        }

        TableColumn statusColumn = table.getColumnModel().getColumn(8);
        statusColumn.setCellRenderer(new StatusRenderer());
        statusColumn.setCellEditor(new StatusEditor());

        JButton btnSave = new JButton("Save");
        btnSave.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btnSave.setBackground(new Color(255, 255, 255));
                btnSave.setForeground(new Color(0, 181, 226));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                btnSave.setForeground(new Color(255, 255, 255));
                btnSave.setBackground(new Color(0, 181, 226));
            }
        });
        btnSave.addActionListener(e -> {

            for (int i = 0; i < table.getRowCount(); i++) {
                String status = (String) table.getValueAt(i, 8);
                Document query = new Document("Student id", table.getValueAt(i, 0));
                Document update = new Document("$set", new Document("Status", status));
                Coll.updateOne(query, update);
            }

            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Save");
            FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV file", "csv");
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
        btnSave.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 14));
        btnSave.setBorder(new LineBorder(new Color(255, 255, 255), 2));
        btnSave.setBackground(new Color(0, 181, 226));
        btnSave.setBounds(282, 604, 120, 30);
        panel.add(btnSave);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btnCancel.setBackground(new Color(255, 255, 255));
                btnCancel.setForeground(new Color(0, 181, 226));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                btnCancel.setForeground(new Color(255, 255, 255));
                btnCancel.setBackground(new Color(0, 181, 226));
            }
        });
        btnCancel.setForeground(Color.WHITE);
        btnCancel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 14));
        btnCancel.setBorder(new LineBorder(new Color(255, 255, 255), 2));
        btnCancel.setBackground(new Color(0, 181, 226));
        btnCancel.setBounds(422, 604, 120, 30);
        panel.add(btnCancel);

        String[] Sem = {"5th", "6th"};
        JComboBox<String> sem = new JComboBox<>(Sem);
        sem.setBounds(100, 20, 150, 20);
        panel.add(sem);

        String[] Div = {"A", "B"};
        JComboBox<String> div = new JComboBox<>(Div);
        div.setBounds(370, 20, 150, 20);
        panel.add(div);
    }

    private class StatusRenderer extends JPanel implements TableCellRenderer {

        JRadioButton presentButton;
        JRadioButton absentButton;
        ButtonGroup group;

        public StatusRenderer() {
            presentButton = new JRadioButton("Present");
            absentButton = new JRadioButton("Absent");
            group = new ButtonGroup();
            group.add(presentButton);
            group.add(absentButton);
            setLayout(new java.awt.GridLayout(1, 2));
            add(presentButton);
            add(absentButton);
            setOpaque(true);
        }

        @Override
        public java.awt.Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if ("Present".equals(value)) {
                presentButton.setSelected(true);
            } else {
                absentButton.setSelected(true);
            }
            return this;
        }
    }

    private class StatusEditor extends AbstractCellEditor implements TableCellEditor {

        StatusRenderer renderer;

        public StatusEditor() {
            renderer = new StatusRenderer();
            renderer.presentButton.addActionListener(e -> stopCellEditing());
            renderer.absentButton.addActionListener(e -> stopCellEditing());
        }

        @Override
        public Object getCellEditorValue() {
            return renderer.presentButton.isSelected() ? "Present" : "Absent";
        }

        @Override
        public java.awt.Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            return renderer.getTableCellRendererComponent(table, value, isSelected, true, row, column);
        }

        @Override
        public boolean isCellEditable(java.util.EventObject e) {
            return true;
        }
    }
}
