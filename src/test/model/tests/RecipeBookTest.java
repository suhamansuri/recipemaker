package model.tests;


import model.Recipe;
import model.RecipeBook;
import org.json.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class RecipeBookTest {

    RecipeBook rb;
    Recipe pasta;
    Recipe pizza;
    Recipe sandwich;
    Recipe soup;
    Recipe cake;


    @BeforeEach
    void runBefore() {
        rb = new RecipeBook();
        pasta = new Recipe("Rigatoni Rose", 30, new ArrayList<>());
        pizza = new Recipe("Margherita", 120, new ArrayList<>());
        sandwich = new Recipe("Cajun Chicken", 15, new ArrayList<>());
        soup = new Recipe("Chicken Noodle", 25, new ArrayList<>());
        cake = new Recipe("Molten Lava", 60, new ArrayList<>());
    }

    @Test
    public void testBookSize() {
        rb.addRecipe(pizza);
        rb.addRecipe(pasta);
        assertEquals(2, rb.bookSize());
        rb.addRecipe(soup);
        assertEquals(3, rb.bookSize());

    }

    @Test
    public void testAddRecipe() {
        assertEquals(0, rb.bookSize());
        assertTrue(rb.addRecipe(pizza));
        rb.addRecipe(pizza);
        assertEquals(1, rb.bookSize());
        assertFalse(rb.addRecipe(pizza));

        assertTrue(rb.addRecipe(pasta));
        rb.addRecipe(pasta);
        assertEquals(2, rb.bookSize());
    }

    @Test
    public void testGetFavourites() {
        rb.addRecipe(pizza);
        rb.addRecipe(pasta);
        rb.addRecipe(soup);
        pasta.addIngredient("Garlic");
        soup.addIngredient("Udon Noodles");
        List<String> oneFav = rb.getFavourites("Garlic");
        assertEquals(1, oneFav.size());
        assertTrue(soup.addIngredient("Garlic"));
        assertFalse(soup.addIngredient("Udon Noodles"));
        soup.addIngredient("Garlic");
        List<String> twoFav = rb.getFavourites("Garlic");
        assertEquals(2, twoFav.size());
        pasta.removeIngredient("Garlic");
        List<String> removeFav = rb.getFavourites("Garlic");
        assertEquals(1, removeFav.size());

    }

    @Test
    public void testMakeRecipe() {
        pasta.addIngredient("Rigatoni");
        pasta.addIngredient("Garlic");
        pasta.addIngredient("Pepper");
        pasta.addIngredient("Mushroom");
        pasta.addIngredient("Spinach");
        pasta.addIngredient("Vodka");
        assertEquals(0, rb.makeRecipe("pasta").size());
        rb.addRecipe(pasta);
        List<String> ingredients = rb.makeRecipe("Rigatoni Rose");
        assertEquals(6, ingredients.size());
        assertTrue(ingredients.contains("Rigatoni"));
        assertFalse(ingredients.contains("Basil"));
        assertEquals(0, rb.makeRecipe("Salad").size());

    }

    @Test
    public void testTimeFor() {
        assertEquals(0, rb.timeFor(50).size());
        rb.addRecipe(pasta);
        rb.addRecipe(soup);
        rb.addRecipe(cake);
        rb.addRecipe(sandwich);
        List<String> threeTime = rb.timeFor(45);
        assertEquals(3, threeTime.size());
        assertFalse(threeTime.contains("cake"));
        cake.changeTime(42);
        List<String> fourTime = rb.timeFor(45);
        assertEquals(4, fourTime.size());
    }

    @Test
    void testViewBook() {
        assertEquals(0, rb.viewBook().size());
        rb.addRecipe(pasta);
        rb.addRecipe(pizza);
        List<String> allRecipes = rb.viewBook();
        assertEquals(2, allRecipes.size());
        assertTrue(allRecipes.contains(pasta.getName()));
        assertTrue(allRecipes.contains(pizza.getName()));
    }

    @Test
    void testGetRecipe() {
        rb.addRecipe(pasta);
        rb.addRecipe(pizza);
        rb.addRecipe(sandwich);
        assertEquals(sandwich, rb.getRecipe("Cajun Chicken"));
    }

    @Test
    void testGetRecipes() {
        rb.addRecipe(pasta);
        rb.addRecipe(pizza);
        rb.addRecipe(soup);
        assertEquals(3, rb.getRecipes().size());
        assertTrue(rb.getRecipes().contains(pasta));
        assertTrue(rb.getRecipes().contains(pizza));
        assertTrue(rb.getRecipes().contains(soup));
        assertFalse(rb.getRecipes().contains(sandwich));
        assertFalse(rb.getRecipes().contains(cake));
    }
}
