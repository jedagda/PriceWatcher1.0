package main.GUI;

import javax.swing.*;
import java.awt.*;

public class DialogPanel extends JPanel {
    JLabel nameLabel;
    JTextField nameField;

    JLabel urlLabel;
    JTextField urlField;

    JLabel initialPriceLabel;
    JTextField initialPriceField;

    JLabel dateAddedLabel;
    JTextField dateAddedField;

    JLabel imageNameLabel;
    JTextField imageNameField;

    DialogPanel() {

        nameLabel = new JLabel("Name");
        nameField = new JTextField();

        urlLabel = new JLabel("Link");
        urlField = new JTextField();

        initialPriceLabel = new JLabel("Price");
        initialPriceField = new JTextField();

        dateAddedLabel = new JLabel("Date");
        dateAddedField = new JTextField();

        imageNameLabel = new JLabel("Image");
        imageNameField = new JTextField();

        add(nameLabel);
        add(nameField);

        add(urlLabel);
        add(urlField);

        add(initialPriceLabel);
        add(initialPriceField);

        add(dateAddedLabel);
        add(dateAddedField);

        add(imageNameLabel);
        add(imageNameField);

    }
    public Dimension getPreferredSize(){
        return new Dimension(350, 200);
    }

    public Dimension getMinimumSize(){
        return new Dimension(350, 200);
    }

    public void doLayout(){
        nameLabel.setBounds(10,10, 60,30);
        nameField.setBounds(70,15, 270,30);

        urlLabel.setBounds(10,40, 60,30);
        urlField.setBounds(70,45, 270,30);

        initialPriceLabel.setBounds(10,70, 60,30);
        initialPriceField.setBounds(70,75, 270,30);

        dateAddedLabel.setBounds(10,100, 60,30);
        dateAddedField.setBounds(70,105, 270,30);

        imageNameLabel.setBounds(10,130, 60,30);
        imageNameField.setBounds(70,135, 270,30);
    }

    public void reset() {
        nameField.setText("");
        urlField.setText("");
        initialPriceField.setText("");
        dateAddedField.setText("");
        imageNameField.setText("");
    }


}

