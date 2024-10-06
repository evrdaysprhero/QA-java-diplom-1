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

    @Mock
    Bun bun;
    @Mock
    Ingredient ingredient;
    @Mock
    Ingredient ingredientTwo;

    @Test
    public void setBunsSuccess() {
        float bunPrice = 1;

        Mockito.when(bun.getPrice()).thenReturn(bunPrice);
        Burger burger = new Burger();
        burger.setBuns(bun);
        float price = burger.getPrice();

        Assert.assertEquals("Булки не добавлены", bunPrice*2, price, 0);
    }

    @Test
    public void addOneIngredientSuccess() {
        float bunPrice = 1;
        float ingredientPrice = 3;

        Mockito.when(bun.getPrice()).thenReturn(bunPrice);
        Mockito.when(ingredient.getPrice()).thenReturn(ingredientPrice);
        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        float price = burger.getPrice();
        Assert.assertEquals("Ингридиенты не добавлены", ingredientPrice + bunPrice*2, price, 0);
    }

    @Test
    public void addThreeIngredientSuccess() {
        float bunPrice = 1;
        float ingredientPrice = 3.1F;

        Mockito.when(bun.getPrice()).thenReturn(bunPrice);
        Mockito.when(ingredient.getPrice()).thenReturn(ingredientPrice);

        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient);

        float price = burger.getPrice();
        Assert.assertEquals("Ингридиенты не добавлены", ingredientPrice*3 + bunPrice*2, price, 0);
    }

    @Test
    public void removeIngredientSuccess() {
        float bunPrice = 1;
        float ingredientPrice = 3.1F;

        Mockito.when(bun.getPrice()).thenReturn(bunPrice);
        Mockito.when(ingredient.getPrice()).thenReturn(ingredientPrice);

        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        burger.removeIngredient(0);

        float price = burger.getPrice();
        Assert.assertEquals("Ингридиент не убран", bunPrice*2, price, 0);
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

        Assert.assertEquals("Цена не соответствует ожидаемой", priceExpected, burger.getPrice(), 0);
    }

    @Test
    public void getPriceTwoIngredientSuccess() {
        float bunPrice = 1;
        float ingredientPrice = 3.59F;
        float ingredientTwoPrice = 1.2F;
        float priceExpected = bunPrice*2 + ingredientPrice + ingredientTwoPrice;

        Mockito.when(bun.getPrice()).thenReturn(bunPrice);
        Mockito.when(ingredient.getPrice()).thenReturn(ingredientPrice);

        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredientTwo);

        Assert.assertEquals("Цена не соответствует ожидаемой", priceExpected, burger.getPrice(), 0);
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
