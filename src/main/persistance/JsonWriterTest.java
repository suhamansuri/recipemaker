package persistance;

import model.Recipe;
import model.RecipeBook;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {
    JsonWriterTest() {

    }

    @Test
    void testWriterInvalidFile() {
        try {
            new RecipeBook("Suha's book");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            Assertions.fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            RecipeBook rb = new RecipeBook("Suha's book");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyRecipeBook.json");

            writer.open();
            writer.write(rb);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyRecipeBook.json");
            rb = reader.read();
            assertEquals("Suha's book", rb.getName());
            assertEquals(0, rb.bookSize());
        } catch (IOException e) {
            Assertions.fail("Exception should not have been thrown");
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
            assertEquals("Suha's book", rb.getName());
            List<Recipe> recipes = rb.getRecipes();
            assertEquals(2, recipes.size());
            this.checkRecipe("cake", 60, recipes.get(0));
            this.checkRecipe("pasta", 30, recipes.get(1));
        } catch (IOException e) {
            Assertions.fail("Exception should not have been thrown");
        }
    }


}
