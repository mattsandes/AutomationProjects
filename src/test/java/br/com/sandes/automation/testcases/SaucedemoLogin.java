package br.com.sandes.automation.testcases;

import br.com.sandes.automation.methods.TestUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SaucedemoLogin {

    private static WebDriver driver;
    private static TestUtils testUtils;

    @BeforeAll
    static void setUp(){
        driver = new ChromeDriver();
        testUtils = new TestUtils(driver);
        driver.manage().window().setSize(new Dimension(1200, 765));
        driver.get("https://www.saucedemo.com/");

        testUtils.logOnSwagLabs(
                "standard_user",
                "secret_sauce");
    }

    @AfterAll
    static void tearDown(){
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
    }

    @Test
    @DisplayName("Validate that is possible to add products on cart")
    void addProductAtCart(){

        testUtils.clickOnProduct("Sauce Labs Bike Light");

        testUtils.addToCart();

        testUtils.goToCart();

        var productOnCart = driver.findElement(By.linkText("Sauce Labs Bike Light")).isDisplayed();

        assertTrue(productOnCart);
    }

    @Test
    @DisplayName("Validate that is possible to remove products from cart")
    void removeProductFromCart(){

        testUtils.clickOnProduct("Sauce Labs Bike Light");

        testUtils.addToCart();

        testUtils.goToCart();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        testUtils.removeProductFromCart("sauce-labs-bike-light");
    }
}
