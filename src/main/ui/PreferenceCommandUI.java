package ui;

import model.RecipeBook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PreferenceCommandUI implements ActionListener {

    JPanel panel = new JPanel();
    RecipeBook rb;
    ActionListener al;
    String action;
    Integer tp;
    String ip;
    JTextField textField;
    JButton enterButton;

    // EFFECTS: constructor
    public PreferenceCommandUI(RecipeBook rb, ActionListener al, String action) {
        this.rb = rb;
        this.al = al;
        this.action = action;
        ip = "";
        tp = 0;
        panel.add(enterButton = new JButton("Enter"));
        enterButton.addActionListener(this);
        panel.add(textField = new JTextField("Enter " + action + " preference: "));
    }

    // EFFECTS: responds to action performed by user
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == enterButton) {
            if (action.equals("time")) {
                try {
                    tp = Integer.parseInt(textField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Error! Cannot select text as time");
                }
                JComponent comp = (JComponent) e.getSource();
                Window win = SwingUtilities.getWindowAncestor(comp);
                win.dispose();
                newTimeFrame();
            } else if (action.equals("ingredient")) {
                ip = textField.getText();
                JComponent comp = (JComponent) e.getSource();
                Window win = SwingUtilities.getWindowAncestor(comp);
                win.dispose();
                newIngredientFrame();
            }
        }
    }

    // EFFECTS: creates a new frame with all recipes you can make under time
    public void newTimeFrame() {
        JFrame frame = new JFrame("Recipes you can make under " + tp + " minutes:");
        frame.setMinimumSize(new Dimension(450,200));
        String[] data = rb.timeFor(tp).toArray(new String[0]);
        frame.add(new JList(data));
        frame.pack();
        frame.setVisible(true);
    }

    // EFFECTS: creates a new frame with all the ingredients with given ing
    public void newIngredientFrame() {
        JFrame frame = new JFrame("Recipes you can make with " + ip);
        frame.setMinimumSize(new Dimension(450,200));
        String[] data = rb.getFavourites(ip).toArray(new String[0]);
        frame.add(new JList(data));
        frame.pack();
        frame.setVisible(true);
    }

    // EFFECTS: returns panel
    public JPanel getPanel() {
        return panel;
    }

}
