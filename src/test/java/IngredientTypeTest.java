import org.junit.Assert;
import org.junit.Test;
import praktikum.IngredientType;

public class IngredientTypeTest {

    @Test
    public void getSouce() {
        IngredientType type = IngredientType.SAUCE;

        Assert.assertEquals("Рецепт не соответствет ожидаемому",
                "SAUCE",
                type.toString());
    }

    @Test
    public void getFilling() {
        IngredientType type = IngredientType.FILLING;

        Assert.assertEquals("Рецепт не соответствет ожидаемому",
                "FILLING",
                type.toString());
    }
}
