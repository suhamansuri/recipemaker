package persistance;

import model.Recipe;
import model.RecipeBook;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("");
        try {
            RecipeBook rb = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyRecipeBook() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyRecipeBook.json");
        try {
            RecipeBook rb = reader.read();
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
            List<Recipe> recipes = rb.getRecipes();
            assertEquals(2, recipes.size());
            assertEquals(2, rb.getRecipe("cake").getIngredients().size());
        } catch (IOException e) {
            fail("Unexpected IOException: " + e);
        }
    }
}
