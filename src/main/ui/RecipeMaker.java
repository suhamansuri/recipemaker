package ui;

import model.Recipe;
import model.RecipeBook;
import persistance.JsonReader;
import persistance.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class RecipeMaker {
    private static final String JSON_STORE = "./data/workroom.json";
    private RecipeBook rb;
    private static final String SPECIFIC_MEAL_COMMAND = "m";
    private static final String EDIT_COMMAND = "e";
    private static final String GENERAL_MEAL_COMMAND = "o";
    private static final String ERROR_MESSAGE = "Sorry, this recipe is not in the recipe book. Please try again";
    private static final String VIEW_COMMAND = "v";
    private boolean runMain;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public RecipeMaker() throws FileNotFoundException {
        rb = new RecipeBook("Suha's book");
        input = new Scanner(System.in);
        runMain = true;
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runRecipeMakerApp();
    }


    // EFFECTS: starts the user interface and quits when user calls to quit
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
        System.out.println("Would you like to save your book? Type 'y' for yes");
        String s = input.nextLine();
        if (s.equals("y")) {
            saveRecipeBook();
        }
        System.out.println("Thank you! Goodbye.");
    }


    // EFFECTS: alters the input as to make it lower case to work with code
    public String fixCommandStyle(String s) {
        s = s.toLowerCase();
        s = s.trim();
        s = s.replaceAll("\"|'", "");
        return s;
    }


    // EFFECTS: prints out a list of options for the user to use from
    public void printOptions() {
        System.out.println("\nTo make a  specific recipe, --> " +  SPECIFIC_MEAL_COMMAND);
        System.out.println("\tFor a list of options, --> " + GENERAL_MEAL_COMMAND);
        System.out.println("\tTo edit or add a recipe, --> " + EDIT_COMMAND);
        System.out.println("\tTo view your entire recipe book, --> " + VIEW_COMMAND);
        System.out.println("\tTo save recipe book to file, --> s");
        System.out.println("\tTo load recipe book from file, --> l");
        System.out.println("\tTo quit, --> " + "q");
    }


    // EFFECTS: analyzes the input from the user and calls the appropriate method
    public void analyzeInput(String s) {
        if (s.length() > 0) {
            if (s.equals(SPECIFIC_MEAL_COMMAND)) {
                handleMealCommand();
            } else if (s.equals(GENERAL_MEAL_COMMAND)) {
                handleOptionCommand();
            } else if (s.equals(EDIT_COMMAND)) {
                handleEditCommand();
            } else if (s.equals(VIEW_COMMAND)) {
                handleViewCommand();
            } else if (s.equals("q")) {
                runMain = false;
            } else if (s.equals("s")) {
                saveRecipeBook();
                printOptions();
            } else if (s.equals("l")) {
                loadRecipeBook();
                printOptions();
            } else {
                System.out.println("Sorry, I did not understand the command. Try again");
                printOptions();
            }
        }
    }

    // EFFECTS: prints out the name of all the names of the recipes
    public void handleViewCommand() {
        System.out.println(rb.viewBook());
        printOptions();
    }


    // EFFECTS: prints the ingredients for the recipe you called
    public void handleMealCommand() {
        System.out.println("You have selected meal command, what would you like to make?");
        String recipe = input.nextLine();
        if (rb.viewBook().contains(recipe)) {
            System.out.println("Great! Here are the ingredients:" + rb.makeRecipe(recipe));
        } else {
            System.out.println(ERROR_MESSAGE);
        }
        printOptions();
    }

    // EFFECTS: gives the option to choose between time or ingredient and produces a list of given choice
    public void handleOptionCommand() {
        System.out.println("You have selected option command");
        System.out.println("What would you like options based on? Type 't' for time or 'p' for ingredient preference");
        String newCommand = input.nextLine();
        if (newCommand.equals("t")) {
            System.out.println("What is your time limit?");
            int time = input.nextInt();
            System.out.println(rb.timeFor(time));
        } else if (newCommand.equals("p")) {
            System.out.println("What ingredient would you like to be included?");
            String pref = input.nextLine();
            System.out.println(rb.getFavourites(pref));
        } else {
            System.out.println("Sorry I do not understand this command. Please try again");
            handleOptionCommand();
        }
        printOptions();
    }


    // EFFECTS: gives options to edit a recipe that exists by time or ingredient and add a given recipe
    public void handleEditCommand() {
        System.out.println("You have selected edit command, would you like to add a recipe or edit an existing one?");
        System.out.println("Type 'a' to add and 'e' to edit");
        String s = input.nextLine();
        if (s.equals("a")) {
            addRecipe();
        } else if (s.equals("e")) {
            editRecipe();
        } else {
            System.out.println("Sorry, I did not understand the command. Try again");
            handleEditCommand();
        }
    }

    // EFFECTS: handles the case of adding a recipe to the recipe book
    public void addRecipe() {
        String name;
        int time;
        System.out.println("What is the name of your recipe?");
        name = input.nextLine();
        System.out.println("How much time does it take to make this meal? (in minutes)");
        time = input.nextInt();
        Recipe recipe = new Recipe(name, time, new ArrayList<>());
        rb.addRecipe(recipe);
        System.out.println("What ingredients will you use? Type 'done' if done");
        input.nextLine();
        addIngredients(recipe);
    }

    // EFFECTS: adds the ingredients for the new recipes
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

    // EFFECTS: edits the recipe based on time or ingredient
    public void editRecipe() {
        String name;
        Recipe recipe;
        List<String> allRecipes = rb.viewBook();
        System.out.println("What is the name of the recipe you would like to edit?");
        name = input.nextLine();
        recipe = rb.getRecipe(name);
        if (allRecipes.contains(name)) {

            System.out.println("What would you like to edit?");
            System.out.println("Type 't' for cook time");
            System.out.println("Type 'i' for ingredients");
            changeRecipe(recipe, input.nextLine());

        } else {
            System.out.println("Sorry, that recipe does not exist. Please try again");
            printOptions();
        }
    }

    // EFFECTS: works with edit recipe to edit the recipes cook time or adding an ingredient
    public void changeRecipe(Recipe recipe, String command) {
        if (command.equals("t")) {
            int time;
            System.out.println("What time would you like to change it to?");
            time = input.nextInt();
            recipe.changeTime(time);
            System.out.println("Great! We have changed the cook time to " + time + " minutes");
            printOptions();
        } else if (command.equals("i")) {
            System.out.println("Would ingredient would you like to add or remove?");
            String newIngredient;
            newIngredient = input.nextLine();
            System.out.println("Great! We have added the ingredient " + newIngredient);
            printOptions();
            if (recipe.addIngredient(newIngredient)) {
                recipe.addIngredient(newIngredient);
            } else {
                recipe.removeIngredient(newIngredient);
                System.out.println("Ok! We have removed the ingredient " + newIngredient);
            }
        }
    }

    // EFFECTS: saves the recipe book to file
    private void saveRecipeBook() {
        try {
            jsonWriter.open();
            jsonWriter.write(rb);
            jsonWriter.close();
            System.out.println("Saved " + rb.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // EFFECTS: loads recipe book from file
    private void loadRecipeBook() {
        try {
            rb = jsonReader.read();
            System.out.println("Loaded " + rb.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}

