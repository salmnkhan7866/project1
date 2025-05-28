package com.AMS_Project;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.util.Properties;

public class SimpleDatePicker {

    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("Simple DatePicker Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(null);

        // Create a date model
        UtilDateModel model = new UtilDateModel();
        Properties properties = new Properties();
        properties.put("text.today", "Today");
        properties.put("text.month", "Month");
        properties.put("text.year", "Year");

        // Create a date panel
        JDatePanelImpl datePanel = new JDatePanelImpl(model, properties);

        // Create a date picker
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, null);

        // Set position and size of the date picker
        datePicker.setBounds(50, 50, 200, 30);

        // Add the date picker to the frame
        frame.add(datePicker);

        // Set the frame visible
        frame.setVisible(true);
    }
}
