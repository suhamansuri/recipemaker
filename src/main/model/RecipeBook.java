package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistance.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


// Represents all the recipes contained in the recipe book
public class RecipeBook implements Writable {
    private List<Recipe> recipes;

    // EFFECTS: Constructs a new RecipeBook
    public RecipeBook() {
        recipes = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: if recipe not already in book, adds recipe to book and returns true, otherwise false
    public boolean addRecipe(Recipe r) {
        if (recipes.contains(r)) {
            return false;
        } else {
            recipes.add(r);
            EventLog.getInstance().logEvent(new Event("Added Recipe '" + r.getName() + "' to RecipeBook"));
            return true;
        }
    }

    // EFFECTS: if recipe in recipe book, returns ingredients, else returns null
    public List<String> makeRecipe(String n) {
        List<String> myIngredients = new ArrayList<>();
        for (Recipe recipe: recipes) {
            if (recipe.getName().equals(n)) {
                myIngredients.addAll(recipe.getIngredients());
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

    // REQUIRES: int time > 0
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

    // EFFECTS: returns all the names of recipes in the recipe book
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

    // EFFECTS: returns all the recipes in recipe book
    public List<Recipe> getRecipes() {
        return Collections.unmodifiableList(recipes);
    }


    // EFFECTS: adds recipe book to JSON
    // EFFECTS: adds recipe book to JSON
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("recipes", recipesToJson());
        return json;
    }

    // EFFECTS: parses recipe book and adds each recipe to json
    private JSONArray recipesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Recipe r : recipes) {
            jsonArray.put(r.toJson());
        }

        return jsonArray;
    }



}
