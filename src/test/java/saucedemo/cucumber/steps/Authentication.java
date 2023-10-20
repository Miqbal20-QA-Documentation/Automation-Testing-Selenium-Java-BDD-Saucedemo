package saucedemo.cucumber.steps;

import io.cucumber.java.en.*;
import org.junit.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import java.time.Duration;

import org.openqa.selenium.support.ui.Wait;
import saucedemo.cucumber.function.*;


public class Authentication {
    public WebDriver driver;
    public String baseUrl = "https://saucedemo.com";
    public Integer timeout = 500;


    /**
     *  Constructor
     * **/
    public Authentication() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless=new");

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

    @When("User fills valid (.*) as username and (.*) as password$")
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

    @Then("User get message (.*) as error$")
    public void user_get_message_error(String error) throws InterruptedException {
        Boolean displayMessage = this.driver.findElement(By.cssSelector("div.error-message-container")).isDisplayed();
        String errorMessage = this.driver.findElement(By.cssSelector("div.error-message-container")).getText();

        // Assert
        Assert.assertEquals(displayMessage, true);
        Assert.assertEquals(error, errorMessage);
        Thread.sleep(timeout);
        this.driver.quit();
    }

    @Given("User has been login saucedemo")
    public void userHasBeenLogedInSaucedemo() throws InterruptedException {
        // get function on saucedemo.cucumber.function
        LoginFunction login = new LoginFunction();
        WaitFunction delay = new WaitFunction();

        // Login
        login.UserLogin(this.driver);

        // Find element
        delay.waiting_by_cssSelector(this.driver, 10000, "button#react-burger-menu-btn");
        Thread.sleep(timeout);
    }

    @When("User click on navigation icon")
    public void userClickOnNavigationIcon() throws InterruptedException {
        this.driver.findElement(By.cssSelector("button#react-burger-menu-btn")).click();
        Thread.sleep(timeout);
    }

    @Then("User click on Logout")
    public void userClickOnLogout() throws InterruptedException {
        WaitFunction delay = new WaitFunction();

        this.driver.findElement(By.cssSelector("a#logout_sidebar_link")).click();

        delay.waiting_by_cssSelector(this.driver, 10000, "div.login_logo");
        String currentUrl = this.driver.getCurrentUrl();
        String titlePage = this.driver.getTitle();
        String logo = this.driver.findElement(By.cssSelector("div.login_logo")).getText();

        // Assert
        Assert.assertEquals(currentUrl, "https://www.saucedemo.com/");
        Assert.assertEquals(titlePage, "Swag Labs");
        Assert.assertEquals(logo, "Swag Labs");

        Thread.sleep(timeout);
        this.driver.quit();
    }
}
