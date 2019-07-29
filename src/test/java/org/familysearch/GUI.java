package org.familysearch;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI extends JFrame{
    private JButton button = new JButton("Начать скачивание");
    private JTextField startPageInput = new JTextField("", 5);
    private JLabel startPageLabel = new JLabel("Начальная страница");
    private JTextField endPageInput = new JTextField("", 5);
    private JLabel endPageLabel = new JLabel("Конечная страница");

    public GUI () {
        super("Simple example");
        this.setBounds(100, 100, 250, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            Container container = this.getContentPane();
            container.setLayout(new GridLayout(3, 2, 2, 2));
            container.add(startPageLabel);
            container.add(startPageInput);
            container.add(endPageLabel);
            container.add(endPageInput);
            ButtonGroup group = new ButtonGroup();
            button.addActionListener(new ButtonEventListener());
            container.add(button);
    }

    class ButtonEventListener implements ActionListener {
        public void actionPerformed (ActionEvent e) {
            handler();
            String message = "";
            message += "Downloading is starting\n";
            message += "Pages from " + startPageInput.getText() + " to " + endPageInput.getText() + "\n will be downloaded";
            JOptionPane.showMessageDialog(null, message, "Output", JOptionPane.PLAIN_MESSAGE);
        }
    }

    public void handler() {
        FirstTest testObject = new FirstTest();
        try {
            int value1 = Integer.parseInt(startPageInput.getText());
            int value2 = Integer.parseInt(endPageInput.getText());
            try {
                if (value2 > value1 || value2 >= 0 ||  value1 >= 0) {
                    testObject.variablesSetter(value1, value2);
                }
            } catch (Exception ex) {
                System.out.println("Wrong input values");
            }
        } catch (Exception ex) {
            System.out.println("Wrong type of input data");
        }
        try {
            testObject.firstTest();
        } catch (Exception ex) {
            System.out.println("General error");
        }
    }
}
