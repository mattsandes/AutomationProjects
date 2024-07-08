package br.com.sandes.automation.testcases;

import br.com.sandes.automation.methods.TestUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SauceLabsTests {

    private static WebDriver driver;
    private static TestUtils testUtils;

    @BeforeEach
    void setUp(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        testUtils = new TestUtils(driver, wait);
        driver.manage().window().setSize(new Dimension(1200, 765));

        testUtils.logOnSwagLabs(
                "standard_user",
                "secret_sauce");
    }

    @AfterEach
    void tearDown(){
        driver.quit();
    }

    @Test
    @DisplayName("Validate that is possible to interact with a product on the ")
    void interactWithProductOnPage(){

        testUtils.clickOnProduct("Sauce Labs Backpack");

        testUtils.assertProductPrice(
                "inventory_details_price",
                "$29.99");

        var addToCartButton = driver.findElement(By.id("add-to-cart")).isDisplayed();

        assertTrue(addToCartButton);

        testUtils.backToProducts();
    }

    @Test
    @DisplayName("Validate that is possible to add products on cart")
    void addProductAtCart(){

        testUtils.pause(10);

        testUtils.clickOnProduct("Sauce Labs Bike Light");

        testUtils.addToCart();

        testUtils.goToCart();

        testUtils.assertProductPresentOnCart("Sauce Labs Bike Light");
    }

    @Test
    @DisplayName("Validate that is possible to remove products from cart")
    void removeProductFromCart(){

        testUtils.clickOnProduct("Sauce Labs Bolt T-Shirt");

        testUtils.addToCart();

        testUtils.goToCart();

        testUtils.pause(5);

        testUtils.removeProductFromCart();

        testUtils.assertProductIsNotOnCart(By.linkText("Sauce Labs Bike Light"));

        testUtils.continueShopping();
    }
}
