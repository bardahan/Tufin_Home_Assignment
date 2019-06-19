package com.Selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Main {
    static WebDriver driver;
    static WebDriverWait wait;

    public static void main(String[] args) {
        System.setProperty("webdriver.gecko.driver", "src//geckodriver");
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "src//logfile.txt");
        // Creating the driver.
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 10);
        // Passing the driver to the other class.
        Ynetnews ynet_handler = new Ynetnews(driver);
        Helper helper_handler = new Helper(driver);
        Helper.openUrl("http://google.com");
        Helper.googleSearch("Ynetnews");
        // Waiting for the search results to appear.
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3.LC20lb")));
        Helper.clickLink("h3.LC20lb");
        Ynetnews.checkYnetOpened();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cdanwmansrch_weathertemps")));
        Ynetnews.printCurrentWeather(null);
        Ynetnews.printCurrentWeather("Elat");
        driver.manage().window().setSize(new Dimension(1920, 1080));
        Ynetnews.getArticle();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("send to friend")));
        Ynetnews.checkSendToFriendLink();
        Ynetnews.openSendToFriend();
        Ynetnews.fillSendToFriend("e@mail.com", "Bar Dahan", "Bar.Dahan@tufin.com", "Hii!");
        Helper.openUrl("http://ynetnews.com");
        Ynetnews.checkUpdateSectionMoving();
        Ynetnews.checkUpdateSectionMovingMouseHover();
    }
}
