package model;
// Represents a Recipe having a name, list of ingredients, time and list of step component

import org.json.JSONArray;
import org.json.JSONObject;
import persistance.Writable;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

// Represents a recipe with a name, time taken to cook and list of ingredients used
public class Recipe implements Writable {
    private String name;
    private int time;
    private List<String> ingredients;


    // EFFECTS: constructs the recipe (n, loi, t, los)
    public Recipe(String n, int t, List<String> ingredients) {
        this.name = n;
        this.time = t;
        this.ingredients = ingredients;
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
        EventLog.getInstance().logEvent(new Event(
                "Changed '" + this.getName() + "' cook time to " + cookTime + " minutes"));
    }

    // MODIFIES: this.ingredients
    // EFFECTS: adds an ingredient to the list of ingredients if not already in list and returns true, otherwise false
    public boolean addIngredient(String ing) {
        if (ingredients.contains(ing)) {
            return false;
        } else {
            this.ingredients.add(ing);
            EventLog.getInstance().logEvent(new Event(
                    "Added '" + ing + "' to " + this.getName()));
            return true;
        }
    }

    // REQUIRES: the input ingredient is in the list of ingredients
    // MODIFIES: this.ingredients
    // EFFECTS: removes the given ingredient from list of ingredients
    public void removeIngredient(String ing) {

        ingredients.remove(ing);
        EventLog.getInstance().logEvent(new Event(
                "Removed '" + ing + "' from " + this.getName()));;
    }

    // EFFECTS: returns true if the recipe uses inputted ingredient, otherwise false
    public boolean recipeContains(String ing) {
        return (ingredients.contains(ing));
    }


    // EFFECTS: changes name of recipe
    public void changeName(String newName) {
        this.name = newName;
    }


    // EFFECTS: adds the recipe to json
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("time", time);
        json.put("ingredients", ingredientsToJson());
        return json;
    }

    // EFFECTS: adds ingredients to json
    public JSONArray ingredientsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (String ing : ingredients) {
            jsonArray.put(ing);
        }

        return jsonArray;
    }


}

