package main.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddItemDialog extends JDialog {
    JPanel bottom;
    JButton addButton, cancelButton;
    DialogPanel dialogPanel;

    public AddItemDialog(JFrame owner){
        super(owner, true);
        setTitle("Add New Item");
        addButton = new JButton("Add");
        cancelButton = new JButton("Cancel");
        ButtonHandler bHandler = new ButtonHandler();
        addButton.addActionListener(bHandler);
        cancelButton.addActionListener(bHandler);
        bottom = new JPanel();
        bottom.add(addButton);
        bottom.add(cancelButton);
        bottom.setBorder(BorderFactory.createEtchedBorder());
        dialogPanel = new DialogPanel();
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(bottom, BorderLayout.SOUTH);
        getContentPane().add(dialogPanel, BorderLayout.CENTER);
    }


    class ButtonHandler implements ActionListener{
        public void actionPerformed(ActionEvent evt){
            JButton button = (JButton) evt.getSource();
            String label = button.getText();
            if("Ok".equals(label)){
                /*Add Item to list      */
                System.out.println("Item Added");
            }
            dialogPanel.reset();
            setVisible(false);
        }

    }
}
