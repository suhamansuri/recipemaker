package ui;

import model.Recipe;
import model.RecipeBook;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class RecipeMaker {
    private final RecipeBook rb;
    private static final String SPECIFIC_MEAL_COMMAND = "make a meal";
    private static final String EDIT_COMMAND = "edit recipe book";
    private static final String GENERAL_MEAL_COMMAND = "options for meals";
    private static final String ERROR_MESSAGE = "Sorry, this recipe is not in the recipe book. Please try again";
    private static final String VIEW_COMMAND = "view recipe book";
    private boolean runMain;
    private final Scanner input;

    public RecipeMaker(RecipeBook rb) {
        this.rb = rb;
        input = new Scanner(System.in);
        runMain = true;
        runRecipeMakerApp();
    }

    public void runRecipeMakerApp() {
        System.out.println("What would you like to do today?");
        String command;
        printOptions();

        while (runMain) {
            if (input.hasNext()) {
                command = input.nextLine();
                command = fixCommandStyle(command);
                analyzeInput(command);
            }
        }
        System.out.println("Thank you! Goodbye.");
    }

    public String fixCommandStyle(String s) {
        s = s.toLowerCase();
        s = s.trim();
        s = s.replaceAll("\"|'", "");
        return s;
    }

    public void printOptions() {
        System.out.println("\nTo make a  specific recipe, type " + "'" + SPECIFIC_MEAL_COMMAND + "'");
        System.out.println("For a list of options, type " + "'" + GENERAL_MEAL_COMMAND + "'");
        System.out.println("To edit or add a recipe, type " + "'" + EDIT_COMMAND + "'");
        System.out.println("To view your entire recipe book, type " + VIEW_COMMAND);
        System.out.println("To quit, type " + "'quit'");
    }

    public void analyzeInput(String s) {
        if (s.length() > 0) {
            switch (s) {
                case SPECIFIC_MEAL_COMMAND:
                    handleMealCommand();
                    break;
                case GENERAL_MEAL_COMMAND:
                    handleOptionCommand();
                    break;
                case EDIT_COMMAND:
                    handleEditCommand();
                    break;
                case VIEW_COMMAND:
                    handleViewCommand();
                    break;
                case "quit":
                    runMain = false;
                    break;
                default:
                    System.out.println("Sorry, I did not understand the command. Try again");
                    printOptions();
                    break;
            }
        }
    }

    public void handleViewCommand() {
        List<String> recipes = new ArrayList<>();
        for (String recipe : rb.viewBook()) {
            recipes.add(recipe);
            System.out.println(recipes);
            printOptions();
        }
    }

    public void handleMealCommand() {
        System.out.println("You have selected meal command, what would you like to make?");
        String recipe = input.nextLine();
        if (rb.makeRecipe(recipe).size() == 0) {
            System.out.println(ERROR_MESSAGE);
        } else {
            System.out.println("Great! Here are the ingredients:" + rb.makeRecipe(recipe));
        }
        printOptions();
    }

    public void handleOptionCommand() {
        System.out.println("You have selected option command");
        System.out.println("Would you like a list based on time or preference?");
        String newCommand = input.nextLine();
        if (newCommand.equals("time")) {
            System.out.println("What is your time limit?");
            int time = input.nextInt();
            System.out.println(rb.timeFor(time));
        } else if (newCommand.equals("preference")) {
            System.out.println("What ingredient would you like to be included?");
            String pref = input.nextLine();
            System.out.println(rb.getFavourites(pref));
        } else {
            System.out.println("Sorry I do not understand this command. Please try again");
            handleOptionCommand();
        }
        printOptions();
    }

    public void handleEditCommand() {
        System.out.println("You have selected edit command, would you like to add a recipe or edit an existing one?");
        System.out.println("Type 'add a recipe' to add and 'edit a recipe' to edit");
        String s = input.nextLine();
        if (s.equals("add a recipe")) {
            addRecipe();
        } else if (s.equals("edit a recipe")) {
            editRecipe();
        } else {
            System.out.println("Sorry, I did not understand the command. Try again");
            handleEditCommand();
        }
    }

    public void addRecipe() {
        String name;
        int time;
        List<String> ingredients = new ArrayList<>();
        System.out.println("What is the name of your recipe?");
        name = input.nextLine();
        System.out.println("How much time does it take to make this meal? (in minutes)");
        time = input.nextInt();
        Recipe recipe = new Recipe(name, time, ingredients);
        rb.addRecipe(recipe);
        System.out.println("What ingredients will you use? Type 'done' if done");
        addIngredients(recipe);
    }

    public void addIngredients(Recipe recipe) {
        String ing = input.nextLine();
        if (ing.equals("done")) {
            System.out.println("Great! We have added your recipe.");
            printOptions();
        } else {
            recipe.addIngredient(ing);
            addIngredients(recipe);
        }
    }

    public void editRecipe() {
        String name;
        Recipe recipe;
        List<String> allRecipes = rb.viewBook();
        System.out.println("What is the name of the recipe you would like to edit?");
        name = input.nextLine();
        recipe = rb.getRecipe(name);
        if (allRecipes.contains(name)) {

            System.out.println("What would you like to edit?");
            System.out.println("Type 'time' for cook time");
            System.out.println("Type 'ingredient' for ingredients");
            changeRecipe(recipe, input.nextLine());

        } else {
            System.out.println("Sorry, that recipe does not exist. Please try again");
            printOptions();
        }
    }

    public void changeRecipe(Recipe recipe, String command) {
        if (command.equals("time")) {
            int time;
            System.out.println("What time would you like to change it to?");
            time = input.nextInt();
            recipe.changeTime(time);
            System.out.println("Great! We have changed the cook time to " + time + " minutes");
            printOptions();
        } else if (command.equals("ingredient")) {
            System.out.println("Would ingredient would you like to add or remove?");
            String newIngredient;
            newIngredient = input.nextLine();
            System.out.println("Great! We have added the ingredient " + newIngredient);
            printOptions();
            if (recipe.addIngredient(newIngredient)) {
                recipe.addIngredient(newIngredient);
            } else {
                recipe.removeIngredient(newIngredient);
            }
        }
    }
}

