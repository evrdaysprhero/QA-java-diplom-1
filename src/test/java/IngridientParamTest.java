import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Ingredient;
import praktikum.IngredientType;

@RunWith(Parameterized.class)
public class IngridientParamTest {

    private IngredientType type;
    private String name;
    private float price;

    public IngridientParamTest(IngredientType type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters
    public static Object[][] getIngridientData() {
        return new Object[][] {
                { IngredientType.SAUCE, "Ранч", 1.2F },
                { IngredientType.FILLING, "Сыр", 2.55F },
        };
    }

    @Test
    public void getTypeSuccess() {
        Ingredient ingredient = new Ingredient(type, name, price);
        IngredientType getType = ingredient.getType();
        Assert.assertEquals("Тип не соответствует ожидаемому",type, getType);
    }

    @Test
    public void getNameSuccess() {
        Ingredient ingredient = new Ingredient(type, name, price);
        String getName = ingredient.getName();
        Assert.assertEquals("Имя не соответствует ожидаемому",name, getName);
    }

    @Test
    public void getPriceSuccess() {
        Ingredient ingredient = new Ingredient(type, name, price);
        float getPrice = ingredient.getPrice();
        Assert.assertEquals("Цена не соответствует ожидаемому",price, getPrice, 0);
    }
}
