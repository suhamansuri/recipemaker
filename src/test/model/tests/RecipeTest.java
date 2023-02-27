package model.tests;

import model.Recipe;
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
        pasta = new Recipe("Rigatoni Rose", 30, new ArrayList<>(Arrays.asList("Rigatoni", "Tomato", "Garlic",
                "Parmesan", "Heavy Cream", "Oyster Mushrooms")));
        pizza = new Recipe("Margherita", 120, new ArrayList<>(Arrays.asList("Pizza Dough", "Tomato Paste",
                "Mozzarella", "Basil", "Chilli Oil")));
        sandwich = new Recipe("Cajun Chicken", 15, new ArrayList<>(Arrays.asList("Potato Bun", "Arugula", "Tomato",
                "Brie", "Chicken", "Sriracha Mayo")));
        soup = new Recipe("Chicken Noodle", 25, new ArrayList<>(Arrays.asList("Shredded Chicken", "Udon Noodles",
                "Vegetable Broth", "Frozen Vegetables")));
        cake = new Recipe("Molten Lava", 60, new ArrayList<>(Arrays.asList("Flour", "Cocoa Powder",
                "Baking powder", "Eggs", "Milk", "Hershey's")));
    }


    @Test
    void testGetIngredients() {
        assertEquals(6, pasta.getIngredients().size());
        assertTrue(pasta.getIngredients().contains("Rigatoni"));
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
        assertEquals(6, pasta.getIngredients().size());
        assertTrue(pasta.addIngredient("Pepper"));
        assertFalse(pasta.addIngredient("Garlic"));
        pasta.addIngredient("Pepper");
        pasta.addIngredient("Garlic");
        assertEquals(7, pasta.getIngredients().size());
        assertTrue(pasta.recipeContains("Garlic"));
    }

    @Test
    void testRemoveIngredient() {
        assertEquals(6, sandwich.getIngredients().size());
        sandwich.removeIngredient("Potato Bun");
        assertEquals(5, sandwich.getIngredients().size());
        assertFalse(sandwich.recipeContains("Potato Bun"));
    }

    @Test
    void testRecipeContains() {
        assertTrue(soup.recipeContains("Shredded Chicken"));
        assertFalse(soup.recipeContains("Flour"));
        assertTrue(cake.recipeContains("Flour"));
        assertFalse(cake.recipeContains("Cream"));



    }
}
