package ui;

import model.Recipe;
import model.RecipeBook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class EditRecipeUI extends JPanel implements ActionListener {
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

    Recipe recipe;
    String name;
    Integer time;
    ArrayList<String> ingredients = new ArrayList<>();


    // EFFECTS: constructor
    public EditRecipeUI(Recipe r, RecipeBook rb, ActionListener al) {
        this.rb = rb;
        this.al = al;
        this.recipe = r;
        backButton = new JButton("Back");
        submitButton = new JButton("Submit");
        init();

    }

    // EFFECTS: initializes edit recipe ui
    public void init() {
        backButton.addActionListener(this);
        backButton.setActionCommand("back");
        submitButton.addActionListener(this);
        submitButton.setActionCommand("submit");
        enterName = new JButton("Change name");
        enterTime = new JButton("Change Time");
        enterIng = new JButton("Add/Remove Ingredient");
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

    // EFFECTS: sets action listeners for text fields
    public void setActionListeners() {
        nameField.addActionListener(this);
        timeField.addActionListener(this);
        ingField.addActionListener(this);
    }

    // EFFECTS: creates a submit button with each text field
    public JPanel getEnteredNameTime() {
        JPanel addPanel = new JPanel();
        addPanel.setLayout(new GridLayout(0, 2));
        nameField = new JTextField(recipe.getName());
        nameField.setBounds(50, 100, 200, 30);
        timeField = new JTextField(recipe.getTime().toString());
        timeField.setBounds(50, 100, 200, 30);
        ingField = new JTextField(String.valueOf(recipe.getIngredients()));
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


    // EFFECTS: takes the input and changes the recipe
    public void changeRecipe(String name, Integer time, ArrayList<String> ing) {
        List<String> ingredients = recipe.getIngredients();
        if (time != null) {
            recipe.changeTime(time);
        }
        if (name != null) {
            recipe.changeName(name);
        }
        if (ing != null) {
            for (String i : ing) {
                if (ingredients.contains(i)) {
                    recipe.removeIngredient(i);
                } else {
                    recipe.addIngredient(i);
                }
            }
        }
    }

    // REQUIRES: txt must not be empty
    // EFFECTS: Creates header for panel
    protected void header(JPanel panel) {
        panel.setBorder(BorderFactory.createTitledBorder("Edit the fields you would like to change:"));
    }


    // EFFECTS: returns the panel
    public JPanel getPanel() {
        return this.thisPanel;
    }

    // EFFECTS: responds to action done by user in ui
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("back")) {
            JComponent comp = (JComponent) e.getSource();
            Window win = SwingUtilities.getWindowAncestor(comp);
            win.dispose();
        } else if (e.getActionCommand().equals("submit")) {
            changeRecipe(this.name, this.time, this.ingredients);
            JOptionPane.showMessageDialog(null, "Great! We have edited this recipe!");
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

