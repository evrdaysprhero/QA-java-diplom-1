import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private static final int DELTA = 0;

    @Mock
    Bun bun;
    @Mock
    Ingredient ingredient;
    @Mock
    Ingredient ingredientTwo;

    @Test
    public void setBunsSuccess() {

        Burger burger = new Burger();
        burger.setBuns(bun);

        Assert.assertEquals("Булки не добавлены", bun, burger.getBun());
    }

    @Test
    public void addOneIngredientSuccess() {

        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        Assert.assertEquals("Ингридиенты не добавлены", ingredient, burger.getIngredients().get(0));
    }

    @Test
    public void addThreeIngredientSuccess() {

        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredientTwo);
        burger.addIngredient(ingredient);

        Assert.assertEquals("Ингридиенты не добавлены", ingredient, burger.getIngredients().get(0));
        Assert.assertEquals("Ингридиенты не добавлены", ingredientTwo, burger.getIngredients().get(1));
        Assert.assertEquals("Ингридиенты не добавлены", ingredient, burger.getIngredients().get(2));
    }

    @Test
    public void removeIngredientSuccess() {
        String bunName = "Белая булочка";
        String receiptExpected = String.format("(==== %s ====)%n", bunName) +
                String.format("(==== %s ====)%n", bunName) +
                String.format("%nPrice: %f%n", 0F);

        Mockito.when(bun.getName()).thenReturn(bunName);

        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        burger.removeIngredient(0);

        Assert.assertEquals("Ингридиент не убран", receiptExpected, burger.getReceipt());
    }

    @Test
    public void moveIngredientSuccess() {
        String bunName = "Белая булочка";
        String ingredientName = "Соус";
        String ingredientTwoName = "Сыр";

        Mockito.when(bun.getName()).thenReturn(bunName);
        Mockito.when(ingredient.getName()).thenReturn(ingredientName);
        Mockito.when(ingredientTwo.getName()).thenReturn(ingredientTwoName);
        Mockito.when(ingredient.getType()).thenReturn(SAUCE);
        Mockito.when(ingredientTwo.getType()).thenReturn(FILLING);

        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredientTwo);

        String receiptExpected = burger.getReceipt();
        burger.moveIngredient(1,0);
        burger.moveIngredient(0,1);

        Assert.assertEquals("Порядок ингридентов неверный",
                receiptExpected,
                burger.getReceipt());
    }

    @Test
    public void moveIngredientFromReceipSuccess() {
        String bunName = "Белая булочка";
        String ingredientName = "Соус";
        String ingredientTwoName = "Сыр";

        Mockito.when(bun.getName()).thenReturn(bunName);
        Mockito.when(ingredient.getName()).thenReturn(ingredientName);
        Mockito.when(ingredientTwo.getName()).thenReturn(ingredientTwoName);
        Mockito.when(ingredient.getType()).thenReturn(SAUCE);
        Mockito.when(ingredientTwo.getType()).thenReturn(FILLING);

        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredientTwo);
        System.out.println(burger.getReceipt());
        burger.moveIngredient(1,0);

        String receiptExpected = String.format("(==== %s ====)%n", bunName) +
                String.format("= %s =%n", "filling Сыр") +
                String.format("= %s =%n", "sauce Соус") +
                String.format("(==== %s ====)%n", bunName) +
                String.format("%nPrice: %f%n", 0F);

        Assert.assertEquals("Порядок ингридентов неверный",
                receiptExpected,
                burger.getReceipt());
    }

    @Test
    public void getPriceOneIngredientSuccess() {
        float bunPrice = 1;
        float ingredientPrice = 3.59F;
        float priceExpected = bunPrice*2 + ingredientPrice;

        Mockito.when(bun.getPrice()).thenReturn(bunPrice);
        Mockito.when(ingredient.getPrice()).thenReturn(ingredientPrice);

        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        Assert.assertEquals("Цена не соответствует ожидаемой", priceExpected, burger.getPrice(), DELTA);
    }

    @Test
    public void getPriceTwoIngredientSuccess() {
        float bunPrice = 1;
        float ingredientPrice = 3.59F;
        float ingredientTwoPrice = 1.2F;
        float priceExpected = bunPrice*2 + ingredientPrice + ingredientTwoPrice;

        Mockito.when(bun.getPrice()).thenReturn(bunPrice);
        Mockito.when(ingredient.getPrice()).thenReturn(ingredientPrice);
        Mockito.when(ingredientTwo.getPrice()).thenReturn(ingredientTwoPrice);

        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredientTwo);

        Assert.assertEquals("Цена не соответствует ожидаемой", priceExpected, burger.getPrice(), DELTA);
    }

    @Test
    public void getReceiptNoIngridientsSuccess() {
        String bunName = "Белая булочка";
        float bunPrice = 1.1F;
        String receiptExpected = String.format("(==== %s ====)%n", bunName) +
                String.format("(==== %s ====)%n", bunName) +
                String.format("%nPrice: %f%n", bunPrice*2);

        Mockito.when(bun.getName()).thenReturn(bunName);
        Mockito.when(bun.getPrice()).thenReturn(bunPrice);

        Burger burger = new Burger();
        burger.setBuns(bun);

        Assert.assertEquals("Рецепт не соответствет ожидаемому",
                receiptExpected,
                burger.getReceipt());
    }

    @Test
    public void getReceiptTreeIngridientsSuccess() {
        String bunName = "Белая булочка";
        String ingredientName = "Соус";
        String ingredientTwoName = "Сыр";
        float bunPrice = 1.1F;
        float ingredientPrice = 3.59F;
        float ingredientTwoPrice = 1.2F;
        float sum = 8.19F;

        String receiptExpected = String.format("(==== %s ====)%n", bunName) +
                String.format("= %s =%n", "filling Сыр") +
                String.format("= %s =%n", "sauce Соус") +
                String.format("= %s =%n", "filling Сыр") +
                String.format("(==== %s ====)%n", bunName) +
                String.format("%nPrice: %f%n", sum);

        Mockito.when(bun.getName()).thenReturn(bunName);
        Mockito.when(ingredient.getName()).thenReturn(ingredientName);
        Mockito.when(ingredientTwo.getName()).thenReturn(ingredientTwoName);
        Mockito.when(bun.getName()).thenReturn(bunName);
        Mockito.when(bun.getPrice()).thenReturn(bunPrice);
        Mockito.when(ingredient.getPrice()).thenReturn(ingredientPrice);
        Mockito.when(ingredientTwo.getPrice()).thenReturn(ingredientTwoPrice);
        Mockito.when(ingredient.getType()).thenReturn(SAUCE);
        Mockito.when(ingredientTwo.getType()).thenReturn(FILLING);

        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ingredientTwo);
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredientTwo);

        Assert.assertEquals("Рецепт не соответствет ожидаемому",
                receiptExpected,
                burger.getReceipt());
    }
}
