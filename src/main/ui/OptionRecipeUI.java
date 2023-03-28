package ui;

import model.Recipe;
import model.RecipeBook;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionRecipeUI extends OptionCommandUI {

    JButton timeButton;
    JButton ingButton;

    public OptionRecipeUI(ActionListener al, RecipeBook rb) {

    }

    @Override
    public void init() {
    }


    @Override
    JPanel generateListView() {
        return null;
    }

    @Override
    Recipe getRecipeFromIndex(String actionCommand) {
        return null;
    }

    @Override
    protected void nextPage(JPanel panel) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
