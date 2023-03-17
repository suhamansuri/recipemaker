package model.tests;

import model.Recipe;
import org.json.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class RecipeTest {

    Recipe pasta;
    Recipe pizza;
    Recipe sandwich;
    Recipe soup;
    Recipe cake;

    @BeforeEach
    void runBefore() {
        pasta = new Recipe("Rigatoni Rose", 30, new ArrayList<>());
        pizza = new Recipe("Margherita", 120, new ArrayList<>());
        sandwich = new Recipe("Cajun Chicken", 15, new ArrayList<>());
        soup = new Recipe("Chicken Noodle", 25, new ArrayList<>());
        cake = new Recipe("Molten Lava", 60, new ArrayList<>());
    }


    @Test
    void testGetIngredients() {
        assertEquals(0, pasta.getIngredients().size());
        pasta.addIngredient("Rigatoni");
        pasta.addIngredient("Garlic");
        assertTrue(pasta.getIngredients().contains("Rigatoni"));
        assertTrue(pasta.getIngredients().contains("Garlic"));
        assertFalse(pasta.getIngredients().contains("Pepper"));
    }

    @Test
    void testGetTime() {
        assertEquals(30, pasta.getTime());
        assertEquals(120, pizza.getTime());
    }

    @Test
    void testGetName() {
        assertEquals("Margherita", pizza.getName());
        assertEquals("Chicken Noodle", soup.getName());
    }

    @Test
    void testChangeTime() {
        assertEquals(60, cake.getTime());
        cake.changeTime(120);
        assertEquals(120,cake.getTime());
    }

    @Test
    void testAddIngredient() {
        assertEquals(0, pasta.getIngredients().size());
        assertTrue(pasta.addIngredient("Pepper"));
        pasta.addIngredient("Garlic");
        assertFalse(pasta.addIngredient("Garlic"));
        pasta.addIngredient("Pepper");
        pasta.addIngredient("Garlic");
        assertEquals(2, pasta.getIngredients().size());
        assertTrue(pasta.recipeContains("Garlic"));
    }

    @Test
    void testRemoveIngredient() {
        sandwich.addIngredient("Potato Bun");
        assertEquals(1, sandwich.getIngredients().size());
        sandwich.removeIngredient("Potato Bun");
        assertEquals(0, sandwich.getIngredients().size());
        assertFalse(sandwich.recipeContains("Potato Bun"));
    }

    @Test
    void testRecipeContains() {
        soup.addIngredient("Shredded Chicken");
        cake.addIngredient("Flour");
        assertTrue(soup.recipeContains("Shredded Chicken"));
        assertFalse(soup.recipeContains("Flour"));
        assertTrue(cake.recipeContains("Flour"));
        assertFalse(cake.recipeContains("Cream"));



    }
}
