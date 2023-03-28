package ui;


import model.RecipeBook;
import persistance.JsonReader;
import persistance.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;


public class MainMenuUI extends JPanel implements ActionListener {
    public static final int HEIGHT = 300;
    public static final int WIDTH = 300;
    public static final int HF = 400;
    public static final int WF = 600;
    private static final String JSON_STORE = "./data/recipeBook.json";

    JButton loadButton;
    JButton saveButton;
    JButton makeButton;
    JButton viewButton;
    JButton editButton;
    JButton optionButton;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    RecipeBook rb;

    private OptionCommandUI editRecipeBookUI;
    private OptionCommandUI optionRecipeBookUI;
    private OptionCommandUI viewRecipeBookUI;
    private OptionCommandUI makeRecipeUI;


    public MainMenuUI(RecipeBook rb) {
        this.rb = rb;
        this.setLayout(new BorderLayout());
        init();

        add(makeMainMenu());
        updateUI();
    }

    public JPanel makeMainMenu() {
        JPanel thisPanel = new JPanel();
        thisPanel.setLayout(new BorderLayout());

        header(thisPanel, "What would you like to do today");

        thisPanel.add(makeMenuButtons(), BorderLayout.CENTER);
        thisPanel.add(loadAndSaveButtons(), BorderLayout.SOUTH);

        addMainEventHandlers();

        return thisPanel;
    }

    public void header(JPanel panel, String header) {
        panel.setBorder(BorderFactory.createTitledBorder(header));
    }



    public JPanel loadAndSaveButtons() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0,2));
        panel.add(loadButton);
        panel.add(saveButton);

        return panel;
    }





    // EFFECTS: initializes GUI
    public void init() {
        loadButton = new JButton("Load recipeBook");
        saveButton = new JButton("Save RecipeBook");
        makeButton = new JButton("Make a meal");
        optionButton = new JButton("Options for meals");
        viewButton = new JButton("View recipeBook");
        editButton = new JButton("Edit recipeBook");
        removeBox();

//        makeRecipeUI = new MakeRecipeUI(this::actionPerformed, rb);
//        viewRecipeBookUI = new ViewRecipeBookUI(this::actionPerformed, rb);
//        optionRecipeBookUI = new OptionRecipeBookUI(this::actionPerformed, rb);
//        editRecipeBookUI = new EditRecipeBookUI(this::actionPerformed, rb);

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }



    // EFFECTS: makes main menu
    public JPanel makeMenuButtons() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,4));
        panel.add(makeButton);
        panel.add(editButton);
        panel.add(optionButton);
        panel.add(viewButton);

        return panel;

    }

    public void addMainEventHandlers() {
        makeButton.addActionListener(this);
        makeButton.setActionCommand("make");

        viewButton.addActionListener(this);
        viewButton.setActionCommand("view");

        optionButton.addActionListener(this);
        optionButton.setActionCommand("option");

        editButton.addActionListener(this);
        editButton.setActionCommand("edit");

        loadButton.addActionListener(this);
        loadButton.setActionCommand("load");

        saveButton.addActionListener(this);
        saveButton.setActionCommand("save");


    }

    public void removeBox() {
        loadButton.setFocusPainted(false);
        saveButton.setFocusPainted(false);
        makeButton.setFocusPainted(false);
        viewButton.setFocusPainted(false);
        editButton.setFocusPainted(false);
        optionButton.setFocusPainted(false);

    }

    private void reset() {
        removeAll();
        init();
        updateUI();
    }

    private void nextPage(JPanel panel) {
        Frame newFrame = new JFrame();
        newFrame.add(panel);
        newFrame.toFront();
        newFrame.setMinimumSize(new Dimension(WF,HF));
        newFrame.setResizable(true);
        newFrame.setVisible(true);
        updateUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("load".equals(e.getActionCommand())) {
            loadRecipeBook();
        } else if ("save".equals(e.getActionCommand())) {
            saveRecipeBook();

        } else if ("make".equals(e.getActionCommand())) {
            makeRecipeUI = new MakeRecipeUI(this::actionPerformed, rb);
            nextPage(makeRecipeUI.getPanel());
        } else if ("edit".equals(e.getActionCommand())) {
            editRecipeBookUI = new EditBookUI(this::actionPerformed, rb);
            nextPage(editRecipeBookUI.getPanel());
        } else if ("view".equals(e.getActionCommand())) {
            viewRecipeBookUI = new ViewRecipeBookUI(this::actionPerformed, rb);
            nextPage(viewRecipeBookUI.getPanel());
        } else if ("option".equals(e.getActionCommand())) {
            optionRecipeBookUI = new OptionRecipeUI(this::actionPerformed, rb);
            nextPage(optionRecipeBookUI.getPanel());
        }
    }

    // EFFECTS: loads recipe book
    public void loadRecipeBook() {
        try {
            jsonReader.read();
            rb = jsonReader.read();
            JOptionPane.showMessageDialog(this, "Loaded recipes from " + JSON_STORE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Unable to load recipes from " + JSON_STORE);
        } finally {
            updateUI();
        }
    }


    public void saveRecipeBook() {
        try {
            jsonWriter.open();
            jsonWriter.write(rb);
            jsonWriter.close();
            JOptionPane.showMessageDialog(this, "Saved recipes to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Unable to save recipes to " + JSON_STORE);
        } finally {
            updateUI();

        }
    }

}
