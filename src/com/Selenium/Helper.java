package com.Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class Helper{
      static WebDriver driver;
      static WebElement element;

    public Helper (WebDriver driver){
        this.driver = driver;
    }

//Function that makes a google search bast on a string input.
    public static void googleSearch(String s) {
        driver.get("http://google.com");
        element = driver.findElement(By.name("q"));
        element.sendKeys(s);
        element.submit();
    }
//Open a URL.
    public static void openUrl(String s){
        driver.get(s);
    }

//Link click by cssSelector.
    public static void clickLink(String s){
        element = driver.findElement(By.cssSelector(s));
        element.click();
    }


}
