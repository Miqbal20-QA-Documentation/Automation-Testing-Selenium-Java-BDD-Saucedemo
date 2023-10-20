package saucedemo.cucumber.function;

import org.openqa.selenium.*;

public class addCartFunction {
    public int timeout = 500;

    public void addOneItem(WebDriver driver) throws InterruptedException {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        System.out.println("Add Item");
        Thread.sleep(500);
    }

    public void addAllItem(WebDriver driver) throws InterruptedException {
        Thread.sleep(timeout);
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
                driver.findElement(By.id(element)).click();
                indexItem += 1;
            } else {
                break;
            }
        }
    }

}
