package main.GUI;

import javax.swing.*;
import java.awt.*;

public class DialogPanel extends JPanel {
    JLabel nameLabel;
    JTextField nameField;

    JLabel urlLabel;
    JTextField urlField;

   // JLabel initialPriceLabel;
   // JTextField initialPriceField;


    JLabel imageNameLabel;
    JTextField imageNameField;

    DialogPanel() {

        nameLabel = new JLabel("Name");
        nameField = new JTextField();

        urlLabel = new JLabel("Link");
        urlField = new JTextField();

        add(nameLabel);
        add(nameField);

        add(urlLabel);
        add(urlField);


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

    }

    public void reset() {
        nameField.setText("");
        urlField.setText("");
    }


}

