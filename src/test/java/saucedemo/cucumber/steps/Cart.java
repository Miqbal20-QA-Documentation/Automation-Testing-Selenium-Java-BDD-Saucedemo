package saucedemo.cucumber.steps;

import io.cucumber.java.en.*;
import org.junit.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class Cart {
    public WebDriver driver;
    public String baseUrl = "https://saucedemo.com";
    public Integer timeout = 1000;

    /**
     *  Constructor
     * **/
    public Cart() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
    }

    /**
     *  Login
     * **/

    @Given("User on Dashboard Pages Saucedemo")
    public void userOnDashboardPagesSaucedemo() throws InterruptedException {
        this.driver.get(baseUrl);
        this.driver.findElement(By.id("user-name")).sendKeys("standard_user");
        this.driver.findElement(By.id("password")).sendKeys("secret_sauce");
        this.driver.findElement(By.id("login-button")).click();
        Thread.sleep(timeout);
    }

    /**
     *  Item Cart
     * **/
    @And("User choose an item and click Add to cart button")
    public void user_choose_an_item_and_click_add_to_cart_button() {
        this.driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
    }

    @And("User click shopping cart badges")
    public void user_click_shopping_cart_badges() {
        this.driver.findElement(By.id("shopping_cart_container")).click();
    }

    @Then("User redirect to Your Cart Page")
    public void user_redirect_to_your_cart_page() {
        String UrlPage = this.driver.getCurrentUrl();
        Assert.assertEquals(UrlPage, "https://www.saucedemo.com/cart.html");
    }

    @And("Item that have been added before is displayed in the cart")
    public void item_that_have_been_added_before_are_displayed_in_the_cart() throws InterruptedException {
        String item = this.driver.findElement(By.cssSelector("div.inventory_item_name")).getText();

        Assert.assertEquals(item, "Sauce Labs Backpack");
        Thread.sleep(1000);

        driver.quit();
    }

    @And("User choose items and click Add to cart button")
    public void user_choose_items_and_click_add_to_cart_button() {

        Object[][] items = {
                {"add-to-cart-sauce-labs-backpack"},
                {"add-to-cart-sauce-labs-bike-light"},
                {"add-to-cart-sauce-labs-bolt-t-shirt"},
                {"add-to-cart-sauce-labs-fleece-jacket"},
                {"add-to-cart-sauce-labs-onesie"},
                {"add-to-cart-test.allthethings()-t-shirt-(red)"},
        };
        int indexItem = 0;
        while (true){
            if(indexItem < items.length){
                String element = (String) items[indexItem][0];
                this.driver.findElement(By.id(element)).click();
                indexItem += 1;
            } else {
                break;
            }
        }
    }

    @And("All Item that have been added before are displayed in the cart")
    public void items_that_have_been_added_before_are_displayed_in_the_cart() throws InterruptedException {
        ArrayList<String> Items= new ArrayList<String>();
        List<WebElement> list = driver.findElements(By.cssSelector("div.inventory_item_name"));

        for (WebElement webElement : list)
            Items.add(webElement.getText());

        int size = Items.size();
        Assert.assertEquals(size, 6);

        Thread.sleep(1000);
        driver.quit();
    }

}
