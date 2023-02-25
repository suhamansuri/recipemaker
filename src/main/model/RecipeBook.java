package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class RecipeBook {
    private final LinkedList<Recipe> recipes;

    // EFFECTS: Constructs a new RecipeBook
    public RecipeBook() {
        recipes = new LinkedList<>();
    }

    // MODIFIES: this
    // EFFECTS: if recipe not already in book, adds recipe to book and returns true, otherwise false
    public boolean addRecipe(Recipe r) {
        if (recipes.contains(r)) {
            return false;
        } else {
            recipes.add(r);
            return true;
        }
    }

    // EFFECTS: if recipe in recipe book, returns ingredients, else returns null
    public List<String> makeRecipe(String n) {
        List<String> myIngredients = new ArrayList<>();
        for (Recipe recipe : recipes) {
            if (n.equals(recipe.getName())) {
                myIngredients = recipe.getIngredients();
            }
        }
        return myIngredients;
    }


    // EFFECTS: returns the number of recipes currently in recipe book
    public int bookSize() {
        return recipes.size();
    }

    // EFFECTS: Produces a list of recipes with a key ingredient. Produces emptyList if none have ingredient
    public List<String> getFavourites(String ing) {
        List<String> favourites = new ArrayList<>();
        for (Recipe recipe : recipes) {
            if (recipe.recipeContains(ing)) {
                favourites.add(recipe.getName());
            }
        }
        return favourites;
    }

    // REQUIRES: int time >= 0
    // EFFECTS: produces a list of recipes that can be made within a given amount of time
    public List<String> timeFor(Integer time) {
        List<String> timeFor = new ArrayList<>();
        for (Recipe recipe : recipes) {
            if (recipe.getTime() <= time) {
                timeFor.add(recipe.getName());
            }
        }
        return timeFor;
    }

    // EFFECTS: returns all the recipes in the recipe book
    public List<String> viewBook() {
        List<String> recipeNames = new ArrayList<>();
        for (Recipe recipe: recipes) {
            recipeNames.add(recipe.getName());
        }
        return recipeNames;
    }


    // REQUIRES: Recipe must be in recipe book
    // EFFECTS: returns the recipe with the given name
    public Recipe getRecipe(String name) {
        Recipe thisRecipe = new Recipe("", -1, new ArrayList<>());
        for (Recipe recipe : recipes) {
            if (name.equals(recipe.getName())) {
                thisRecipe = recipe;
            }
        }
        return thisRecipe;
    }
}
