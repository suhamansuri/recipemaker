package model;
// Represents a Recipe having a name, list of ingredients, time and list of step component

import java.util.List;

// Represents a recipe with a name, time taken to cook and list of ingredients used
public class Recipe {
    private final String name;
    private int time;
    private final List<String> ingredients;


    // EFFECTS: constructs the recipe (n, loi, t, los)
    public Recipe(String n, int t, List<String> ing) {
        this.name = n;
        this.time = t;
        this.ingredients = ing;
    }

    // EFFECTS: returns the list of ingredients
    public List<String> getIngredients() {
        return this.ingredients;
    }


    // EFFECTS: returns the time taken to cook
    public Integer getTime() {
        return this.time;
    }

    // EFFECTS: returns the name of the recipe
    public String getName() {
        return this.name;
    }

    // REQUIRES: cookTime > 0
    // MODIFIES: this.time
    // EFFECTS: changes the time took to prepare recipe
    public void changeTime(int cookTime) {
        this.time = cookTime;
    }

    // MODIFIES: this.ingredients
    // EFFECTS: adds an ingredient to the list of ingredients if not already in list and returns true, otherwise false
    public boolean addIngredient(String ing) {
        if (ingredients.contains(ing)) {
            return false;
        } else {
            ingredients.add(ing);
            return true;
        }
    }

    // REQUIRES: the input ingredient must be in the list of ingredients
    // MODIFIES: this.ingredients
    // EFFECTS: removes the given ingredient from list of ingredients
    public void removeIngredient(String ing) {
        ingredients.remove(ing);
    }

    public boolean recipeContains(String ing) {
        return (ingredients.contains(ing));
    }

}

