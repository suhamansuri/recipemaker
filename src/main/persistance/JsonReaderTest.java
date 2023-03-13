package persistance;

import model.Recipe;
import model.RecipeBook;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class JsonReaderTest extends JsonTest {
    JsonReaderTest() {
    }


    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/my/illegal/fileName.json");
        try {
            RecipeBook rb = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyRecipeBook() {
        JsonReader reader = new JsonReader("./data/testWriterEmptyRecipeBook.json");
        try {
            RecipeBook rb = reader.read();
            assertEquals("Suha's book", rb.getName());
            assertEquals(0, rb.bookSize());
        } catch (IOException e) {
            fail("Unexpected IOException: " + e);
        }
    }

    @Test
    void testReaderGeneralRecipeBook() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralRecipeBook.json");
        try {
            RecipeBook rb = reader.read();
            assertEquals("Suha's book", rb.getName());
            List<Recipe> recipes = rb.getRecipes();
            assertEquals(2, recipes.size());
            assertEquals(2, rb.getRecipe("cake").getIngredients().size());
            List<String> ingredients = new ArrayList<>();
            ingredients.add("flour");
            ingredients.add("sugar");
            checkRecipe("cake", 60, recipes.get(0));
        } catch (IOException e) {
            fail("Unexpected IOException: " + e);
        }
    }
}

