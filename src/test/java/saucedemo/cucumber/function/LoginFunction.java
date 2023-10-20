package saucedemo.cucumber.function;
import org.openqa.selenium.*;


public class LoginFunction {
    public String baseUrl = "https://saucedemo.com";
    public void UserLogin(WebDriver driver) throws InterruptedException { // Method
        driver.get(baseUrl);
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
    }
}
