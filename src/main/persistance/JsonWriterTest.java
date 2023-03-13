package persistance;

import model.Recipe;
import model.RecipeBook;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {


    @Test
    void testWriterInvalidFile() {
        try {
            RecipeBook rb = new RecipeBook("Suha's book");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            RecipeBook rb = new RecipeBook("Suha's book");
            JsonWriter writer = new JsonWriter("./data/testReaderEmptyRecipeBook.json");
            writer.open();
            writer.write(rb);
            writer.close();

            JsonReader reader = new JsonReader("./data/testReaderEmptyRecipeBook.json");
            rb = reader.read();
            assertEquals(0, rb.bookSize());
            assertEquals("Suha's book", rb.getName());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            RecipeBook rb = new RecipeBook("Suha's book");
            Recipe r = new Recipe("cake", 60, new ArrayList<>());
            r.addIngredient("flour");
            r.addIngredient("sugar");
            rb.addRecipe(r);
            rb.addRecipe(new Recipe("pasta", 30, new ArrayList<>()));
            JsonWriter writer = new JsonWriter("./data/testReaderGeneralRecipeBook.json");
            writer.open();
            writer.write(rb);
            writer.close();

            JsonReader reader = new JsonReader("./data/testReaderGeneralRecipeBook.json");
            rb = reader.read();
            List<Recipe> recipes = rb.getRecipes();
            assertEquals(2, recipes.size());
            checkRecipe("cake", 60, recipes.get(0));
            checkRecipe("pasta", 30, recipes.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }


}
