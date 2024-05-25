package br.com.sandes.automation.methods;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUtils {

    private final WebDriver driver;

    public TestUtils(WebDriver driver) {
        this.driver = driver;
    }

    public void logOnSwagLabs(String username, String password){
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.id("login-button")).click();
    }

    public void clickOnProduct(String product){
        driver.findElement(By.linkText(product)).click();
    }

    public void addToCart(){
        driver.findElement(By.id("add-to-cart")).click();
    }

    public void assertProductPrice(String element, String price){
        var productPrice = driver.findElement(By.className(element)).getText();

        assertEquals(price, productPrice);
    }

    public void goToCart(){
        driver.findElement(By.className("shopping_cart_link")).click();
    }

    public void removeProductFromCart(String product){
        driver.findElement(By.id("remove-" + product)).click();
    }
}
