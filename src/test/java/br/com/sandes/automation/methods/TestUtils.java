package br.com.sandes.automation.methods;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestUtils {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public TestUtils(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
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

    public void assertProductPresentOnCart(String product){
        var foundElement = driver.findElement(By.linkText(product)).isDisplayed();

        assertTrue(foundElement);
    }

    public void assertProductIsNotOnCart(By product){

        wait.until(ExpectedConditions.invisibilityOfElementLocated(product));
    }

    public void pause(int seconds){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
    }
}
