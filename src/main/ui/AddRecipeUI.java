package ui;

import model.Recipe;
import model.RecipeBook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddRecipeUI implements ActionListener {
    RecipeBook rb;
    ActionListener al;
    JPanel thisPanel = new JPanel();
    JButton submitButton;
    JButton backButton;
    JPanel recipeField;

    JButton enterName;
    JButton enterTime;
    JButton enterIng;
    JTextField nameField;
    JTextField timeField;
    JTextField ingField;

    String name;
    Integer time;
    ArrayList<String> ingredients = new ArrayList<>();



    public AddRecipeUI(RecipeBook rb, ActionListener al) {
        this.rb = rb;
        this.al = al;
        backButton = new JButton("Back");
        submitButton = new JButton("Submit");
        init();
    }


    public void init() {
        backButton.addActionListener(this);
        backButton.setActionCommand("back");
        submitButton.addActionListener(this);
        submitButton.setActionCommand("submit");
        enterName = new JButton("Submit name");
        enterTime = new JButton("Submit Time");
        enterIng = new JButton("Enter Ingredient");
        enterName.addActionListener(this);
        enterTime.addActionListener(this);
        enterIng.addActionListener(this);


        thisPanel.setLayout(new BorderLayout());
        thisPanel.setMinimumSize(new Dimension(MainMenuUI.WIDTH, MainMenuUI.HEIGHT));
        thisPanel.add(backButton, BorderLayout.NORTH);
        header(thisPanel);
        recipeField = getEnteredNameTime();
        thisPanel.add(recipeField, BorderLayout.CENTER);
        thisPanel.add(submitButton, BorderLayout.SOUTH);

    }

    public void setActionListeners() {
        nameField.addActionListener(this);
        timeField.addActionListener(this);
        ingField.addActionListener(this);
    }


    public JPanel getEnteredNameTime() {
        JPanel addPanel = new JPanel();
        addPanel.setLayout(new GridLayout(0, 2));
        nameField = new JTextField("Enter name");
        nameField.setBounds(50, 100, 200, 30);
        timeField = new JTextField("Enter time");
        timeField.setBounds(50, 100, 200, 30);
        ingField = new JTextField("Enter ingredient");
        ingField.setBounds(50, 100, 200, 30);
        addPanel.add(enterName);
        addPanel.add(nameField);
        addPanel.add(enterTime);
        addPanel.add(timeField);
        addPanel.add(enterIng);
        addPanel.add(ingField);
        setActionListeners();
        return addPanel;
    }


    public void createRecipe(String name, int time, ArrayList<String> ing) {
        Recipe r = new Recipe(name, time, ing);
        rb.addRecipe(r);

    }

    // REQUIRES: txt must not be empty
    // EFFECTS: Creates header for panel
    protected void header(JPanel panel) {
        panel.setBorder(BorderFactory.createTitledBorder("Please enter the following fields:"));
    }


    public JPanel getPanel() {
        return this.thisPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("back")) {
            JComponent comp = (JComponent) e.getSource();
            Window win = SwingUtilities.getWindowAncestor(comp);
            win.dispose();
        } else if (e.getActionCommand().equals("submit")) {
            if (this.name == null | this.time == null) {
                JOptionPane.showMessageDialog(null, "ERROR: cannot add recipe");
            } else {
                createRecipe(this.name, this.time, this.ingredients);
                JOptionPane.showMessageDialog(null, "Great! We have added this recipe!");
            }
        } else if (e.getSource() == enterName) {
            this.name = nameField.getText();
        } else if (e.getSource() == enterTime) {
            try {
                this.time = Integer.parseInt(timeField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Error, cannot enter text as time");
            }
        } else if (e.getSource() == enterIng) {
            this.ingredients.add(ingField.getText());
        }
    }
}
