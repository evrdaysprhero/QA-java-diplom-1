import org.junit.Assert;
import org.junit.Test;
import praktikum.Bun;

public class BunTest {
    @Test
    public void getNameSuccess() {
        String name = "Name";
        float price = 1;

        Bun bun = new Bun(name, price);
        String getName = bun.getName();
        Assert.assertEquals("Имя не соответствует ожидаемому",name, getName);
    }

    @Test
    public void getPriceSuccess() {
        String name = "Name";
        float price = 1;

        Bun bun = new Bun(name, price);
        float getPrice = bun.getPrice();
        Assert.assertEquals("Цена не соответствует ожидаемому",price, getPrice, 0);
    }
}
