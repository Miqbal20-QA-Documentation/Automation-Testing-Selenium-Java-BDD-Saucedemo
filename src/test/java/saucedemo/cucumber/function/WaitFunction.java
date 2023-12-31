package saucedemo.cucumber.function;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitFunction {
    public void waiting_by_id(WebDriver driver, int seconds, String elements){
        WebElement element = driver.findElement(By.id(elements));
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }

    public void waiting_by_cssSelector(WebDriver driver, int seconds, String elements){
        WebElement element = driver.findElement(By.cssSelector(elements));
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }

    public void waiting_by_xpath(WebDriver driver, int seconds, String elements){
        WebElement element = driver.findElement(By.id(elements));
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }
}
