package model;

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
        pasta = new Recipe("Rigatoni Rose", 30, new ArrayList<>(Arrays.asList("Rigatoni", "Tomato", "Garlic",
                                                                     "Parmesan", "Heavy Cream", "Oyster Mushrooms")));
        pizza = new Recipe("Margherita", 120, new ArrayList<>(Arrays.asList("Pizza Dough","Tomato Paste",
                                                                 "Mozzarella", "Basil", "Chilli Oil")));
        sandwich = new Recipe("Cajun Chicken", 15, new ArrayList<>(Arrays.asList("Potato Bun", "Arugula", "Tomato",
                                                                                  "Brie", "Chicken", "Sriracha Mayo")));
        soup = new Recipe("Chicken Noodle", 25, new ArrayList<>(Arrays.asList("Shredded Chicken", "Udon Noodles",
                                                                             "Vegetable Broth", "Frozen Vegetables")));
        cake = new Recipe("Molten Lava", 60, new ArrayList<>(Arrays.asList("Flour", "Cocoa Powder",
                                                                        "Baking powder", "Eggs", "Milk", "Hershey's")));
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
        assertFalse(threeTime.contains(cake));
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
}
