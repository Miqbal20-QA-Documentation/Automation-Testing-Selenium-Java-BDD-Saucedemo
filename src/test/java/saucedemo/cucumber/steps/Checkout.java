package saucedemo.cucumber.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import saucedemo.cucumber.function.LoginFunction;
import saucedemo.cucumber.function.WaitFunction;
import saucedemo.cucumber.function.addCartFunction;

import java.time.Duration;

public class Checkout {
    public LoginFunction login = new LoginFunction();
    public addCartFunction addCart = new addCartFunction();
    public WebDriver driver;

    public WaitFunction delay = new WaitFunction();

    public int timeout = 500;

    public Checkout(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
    }

    @Given("User has logged in Saucedemo")
    public void userHasLoggedInSaucedemo() throws InterruptedException {
        login.UserLogin(this.driver);
    }

    @Then("User choose one item and click Add to cart button")
    public void userChooseOneItemAndClickAddToCartButton() throws InterruptedException {
        addCart.addOneItem(this.driver);
    }

    @And("User go to checkout page")
    public void userGoToCheckoutPage() throws InterruptedException {
        this.driver.findElement(By.id("shopping_cart_container")).click();
        Thread.sleep(timeout);
    }

    @And("User click Checkout button")
    public void userClickCheckoutButton() {
        delay.waiting_by_cssSelector(this.driver, 1000, "button#checkout");
        this.driver.findElement(By.cssSelector("button#checkout")).click();
    }

    @And("User fills (.*) as First Name, (.*) as Last Name, (.*) as Postal Code$")
    public void userFillsFirstnameLastnamePostalcode(String firstname, String lastname, String postalcode) throws InterruptedException {
        this.driver.findElement(By.id("first-name")).sendKeys(firstname);
        this.driver.findElement(By.id("last-name")).sendKeys(lastname);
        this.driver.findElement(By.id("postal-code")).sendKeys(postalcode);
        Thread.sleep(timeout);
    }

    @Then("User Click on Finish button ot checkout item")
    public void userClickOnFinishButtonOtCheckoutItem() throws InterruptedException {
        this.driver.findElement(By.id("finish")).click();
        Thread.sleep(timeout);
    }

    @And("Success message is showing up")
    public void successMessageIsShowingUp() throws InterruptedException {
        String SuccessMessages = this.driver.findElement(By.cssSelector("h2.complete-header")).getText();
        String titlePage = this.driver.getTitle();
        String currentUrl = this.driver.getCurrentUrl();

        //Assert
        Assert.assertEquals(SuccessMessages, "Thank you for your order!");
        Assert.assertEquals(titlePage, "Swag Labs");
        Assert.assertEquals(currentUrl, "https://www.saucedemo.com/checkout-complete.html");
        Thread.sleep(timeout);

        this.driver.quit();
    }

    @And("User Click Continue button")
    public void userClickContinueButton() {
        this.driver.findElement(By.cssSelector("input#continue")).click();
    }

    @Then("Error (.*) as error message is showing up$")
    public void errorMessageIsShowingUp(String error) throws InterruptedException {
        String ErrorMessage = this.driver.findElement(By.cssSelector("div.error-message-container")).getText();
        Assert.assertEquals(ErrorMessage, error);
        Thread.sleep(timeout);
        this.driver.quit();
    }

    @Then("User choose all item and click Add to cart button")
    public void userChooseAllItemAndClickAddToCartButton() throws InterruptedException {
        addCart.addAllItem(this.driver);
        Thread.sleep(timeout);
    }
}
