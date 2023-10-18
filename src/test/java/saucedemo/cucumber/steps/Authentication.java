package saucedemo.cucumber.steps;

import io.cucumber.java.en.*;
import org.junit.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;

import java.time.Duration;

public class Authentication {
    public WebDriver driver;
    public String baseUrl = "https://saucedemo.com";
    public Integer timeout = 1000;

    /**
     *  Constructor
     * **/
    public Authentication() {
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
    @Given("User on Login Pages Saucedemo")
    public void user_on_login_pages_saucedemo(){
        this.driver.get(baseUrl);
    }

    @When("User fills valid (.*) and (.*)$")
    public void user_fills_valid_username_and_password(String email, String password) {
        this.driver.findElement(By.id("user-name")).sendKeys(email);
        this.driver.findElement(By.id("password")).sendKeys(password);
    }

    @And("User click on Login button")
    public void user_click_on_login_button() throws InterruptedException {
        this.driver.findElement(By.id("login-button")).click();
        Thread.sleep(timeout);
    }

    @Then("User redirect to Dashboard Page")
    public void user_redirect_to_dashboard_page() throws InterruptedException {
        String PageUrl = this.driver.getCurrentUrl();
        String PageTitle = this.driver.findElement(By.className("app_logo")).getText();

        Assert.assertEquals(PageUrl, "https://www.saucedemo.com/inventory.html");
        Assert.assertEquals(PageTitle, "Swag Labs");

        Thread.sleep(timeout);
        this.driver.quit();
    }

    @Then("User get message (.*)$")
    public void user_get_message_error(String error) throws InterruptedException {
        Boolean displayMessage = this.driver.findElement(By.cssSelector("div.error-message-container")).isDisplayed();
        String errorMessage = this.driver.findElement(By.cssSelector("div.error-message-container")).getText();

        // Assert
        Assert.assertEquals(displayMessage, true);
        Assert.assertEquals(error, errorMessage);
        Thread.sleep(timeout);
        this.driver.quit();
    }

}
