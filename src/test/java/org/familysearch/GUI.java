package org.familysearch;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.swing.*;

public class GUI extends JFrame{
    private JButton button = new JButton("Начать скачивание");
    private JTextField startPageInput = new JTextField("", 5);
    private JLabel startPageLabel = new JLabel("Начальная страница");
    private JTextField endPageInput = new JTextField("", 5);
    private JLabel endPageLabel = new JLabel("Конечная страница");
    private JLabel bookSelectorLabel = new JLabel("Выберите метрическую книгу");
    private JComboBox bookSelectorCombo;
    private HashMap titlesAndLinks;

    public GUI(HashMap inputItems) {
        super("Simple example");
        this.titlesAndLinks = inputItems;
        bookSelectorCombo = new JComboBox(getItemsForBookSelectorCombo());
        this.setBounds(200, 200, 600, 180);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        Container container = this.getContentPane();
        container.setLayout(new GridLayout(4, 2, 2, 2));
        container.add(bookSelectorLabel);
        container.add(bookSelectorCombo);
        container.add(startPageLabel);
        container.add(startPageInput);
        container.add(endPageLabel);
        container.add(endPageInput);
        ButtonGroup group = new ButtonGroup();
        button.addActionListener(new ButtonEventListener());
        container.add(button);
    }

    private String[] getItemsForBookSelectorCombo() {
        Set<String> titlesOfBooks = titlesAndLinks.keySet();
        String[] itemsInBookSelectorCombo = titlesOfBooks.toArray(new String[titlesOfBooks.size()]);

        return itemsInBookSelectorCombo;
    }

    class ButtonEventListener implements ActionListener {
        public void actionPerformed (ActionEvent e) {
            MetricBookUrlConfigurator.metricBookTitleGetter((String) bookSelectorCombo.getSelectedItem());
            int value1 = inputGetter(startPageInput);
            int value2 = inputGetter(endPageInput);
            if (inputChecker(value1, value2) != false) {
                String message = "";
                message += "Downloading is starting\n";
                message += "Pages from " + startPageInput.getText() + " to " + endPageInput.getText() + "\n will be downloaded";
                JOptionPane.showMessageDialog(null, message, "Output", JOptionPane.PLAIN_MESSAGE);
                handler(value1, value2);
            } else {
                System.out.println("Wrong input data. The app will be stopped");
                System.exit(0);
            }
        }
    }

    private int inputGetter (JTextField field) {
        int output = 0;
        try {
            output = Integer.parseInt(field.getText());
        } catch (Exception ex) {
            System.out.println("Wrong type of input data: " + field.getUIClassID());
        }
        return output;
    }

    private Boolean inputChecker(int value1, int value2) {
        Boolean decision = false;
            try {
                if (value1 > 0 && value2 > 0) {
                    if (value2 > value1) {
                        decision = true;
                    }
                }
            } catch (Exception ex) {
                System.out.println("Wrong input values");
            }
        return decision;
    }

    public void handler(int value1, int value2) {
        FirstTest testObject = new FirstTest();
        testObject.variablesSetter(value1, value2);
        System.out.println(value1 + " " + value2);
        testObject.firstTest();
//        try {
//            testObject.firstTest();
//        } catch (Exception ex) {
//            System.out.println("General error");
//        }
    }
}
