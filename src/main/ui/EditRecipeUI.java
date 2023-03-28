package ui;

import model.Recipe;
import model.RecipeBook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

public class EditRecipeUI extends JPanel implements ActionListener {
    Recipe recipe;
    JLabel recipeLabel;
    JPanel panel = new JPanel();
    ActionListener al;
    JButton submit = new JButton("Submit");
    JLabel nameLabel = new JLabel("Name of recipe: ");
    JLabel timeLabel = new JLabel("Preparation time: ");
    JLabel ingredientsLabel = new JLabel("Ingredients: ");

    String name;
    int time;
    List<String> ingredients;

    JFormattedTextField nameField;
    JFormattedTextField timeField;
    JList ingredientField;

    public EditRecipeUI(Recipe r, RecipeBook rb, ActionListener al) {

        init();

    }

    public void init() {
        this.setLayout(new BorderLayout());
        this.setVisible(true);
        header(this, "Edit " + name);

        initializeFields();
        initializeButtons();

        add(generateFields(), BorderLayout.CENTER);
        add(submit, BorderLayout.SOUTH);
    }

    public void initializeFields() {
        nameField = new JFormattedTextField();
        timeField = new JFormattedTextField();
        ingredientField = new JList<>();

        nameField.setText(name);
        timeField.setText(String.valueOf(time));
        ingredientField.setListData((Vector) ingredients);

    }

    public void initializeButtons() {
        submit.setActionCommand("submit");
        submit.addActionListener(this);
        submit.addActionListener(al);

    }

    public JPanel generateFields() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4,2));
        header(panel, "Edit fields below");

        panel.add(nameLabel);
        panel.add(nameField);

        panel.add(timeLabel);
        panel.add(timeField);

        panel.add(ingredientsLabel);
        panel.add(ingredientField);

        return panel;
    }

    // REQUIRES: txt must not be empty
    // EFFECTS: Creates header for panel
    protected void header(JPanel panel, String txt) {
        panel.setBorder(BorderFactory.createTitledBorder(txt));
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public JPanel getPanel() {
        return this.panel;
    }
}
