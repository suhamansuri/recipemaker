package persistance;

import model.Recipe;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkRecipe(String name, int time, Recipe recipe) {
        assertEquals(name, recipe.getName());
        assertEquals(time, recipe.getTime());
    }
}
