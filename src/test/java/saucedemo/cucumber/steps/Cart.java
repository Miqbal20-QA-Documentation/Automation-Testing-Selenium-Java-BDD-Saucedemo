package saucedemo.cucumber.steps;

import io.cucumber.java.en.*;
import org.junit.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import saucedemo.cucumber.function.LoginFunction;
import saucedemo.cucumber.function.addCartFunction;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class Cart { // Class


    public WebDriver driver;
    public String baseUrl = "https://saucedemo.com";
    public Integer timeout = 500;

    public addCartFunction addCart = new addCartFunction();


    public Cart() { // Constructor
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
    }

    @Given("User on Dashboard Pages Saucedemo")
    public void userOnDashboardPagesSaucedemo() throws InterruptedException {
        LoginFunction login = new LoginFunction();
        login.UserLogin(this.driver);
    }

    // Add to Item Cart
    @And("User choose an item and click Add to cart button")
    public void user_choose_an_item_and_click_add_to_cart_button() throws InterruptedException {
        addCart.addOneItem(this.driver);
        Thread.sleep(timeout);
    }

    @And("User click shopping cart badges")
    public void user_click_shopping_cart_badges() throws InterruptedException {
        this.driver.findElement(By.id("shopping_cart_container")).click();
        Thread.sleep(timeout);
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
    public void user_choose_items_and_click_add_to_cart_button() throws InterruptedException {
        addCart.addAllItem(this.driver);
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
