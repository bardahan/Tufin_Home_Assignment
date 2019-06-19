package com.Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.lang.model.element.Element;
import java.util.*;

public class Ynetnews {
        static private WebDriver driver;
        static private WebElement element;


        public Ynetnews(WebDriver driver){
                this.driver = driver;
        }
// Checks if ynet is open based on the site title.
        public static void checkYnetOpened(){
                String sitetitle = driver.getTitle();
                if (sitetitle.contains("ynetnews - Homepage")){
                        System.out.println("The correct webpage loaded");
                }else{
                        System.out.println("Wrong webpage was loaded site title: " + sitetitle);
                }
        }
// Printing the weather provided by ynet for a specific location. if "null" will print the default option.
        public static void printCurrentWeather(String location){
                element = driver.findElement(By.id("cdanwmansrch_weathertemps"));
                Select weatherElement = new Select(driver.findElement(By.id("cdanwmansrch_weathercitieselect")));
                String currentLocation = weatherElement.getFirstSelectedOption().getAttribute("value");
                if (location != null){
                        currentLocation = location;
                }
                weatherElement.selectByValue(currentLocation);
                String w = element.getText();
                System.out.println(String.format("The weather in %s is %s", currentLocation, w));
        }
// Open a article.
        public static void getArticle(){
                element = driver.findElement(By.cssSelector("a[href*='/articles/']"));
                element.click();
        }
// Checks if a "send to friend" link exist in the article. will print the error if not.
        public static void checkSendToFriendLink(){
                try{
                        element = driver.findElement(By.linkText("send to friend"));
                        System.out.println("send to a friend link exist");

                }catch (Exception e){
                        System.out.println("There was an issue with 'send to friend' link error " + e.getMessage());
                }
        }
// Opens send to a friend link
        public static void openSendToFriend(){
                element = driver.findElement(By.xpath("/html/body/div[9]/div[3]/article/div/div[1]/div/div[1]" +
                        "/div[2]/div/div/ul/li[5]/a/span[2]"));
                element.click();
        }
// Fills the "send to a friend" form and exits it.
// Also returns the driver to the main page.
        public static void fillSendToFriend(String friendEmail, String name, String yourEmail, String comment){
                Set<String> windows = driver.getWindowHandles();
                String perentWindowHandle = driver.getWindowHandle();
                for(String window:windows) {
                        if (!perentWindowHandle.equals(window)) {
                               driver.switchTo().window(window);
                        }
                }

                WebDriverWait wait = new WebDriverWait(driver, 10);
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtRemarks")));
                String[] formFill = new String[]{friendEmail, name, yourEmail, comment};
                String[] form = new String[]{"txtTo", "txtFromName", "txtFromAddress", "txtRemarks"};
                for(int i = 0; i < form.length; i++) {
                        element = driver.findElement(By.id(form[i]));
                        element.sendKeys(formFill[i]);
                }

                driver.close();
                driver.switchTo().window(perentWindowHandle);
        }


// Checks if the updates section article list is moving.
        static void checkUpdateSectionMoving(){
                String firstPosition, secPosition;
                firstPosition = getIframePosition();
                try{
                        Thread.sleep(5000);
                }
                catch(InterruptedException e) {
                        System.out.println("got interrupted!");
                }
                secPosition = getIframePosition();
                if (!firstPosition.equals(secPosition)){
                        System.out.println("Update section article list is moving");
                }else{
                        System.out.println("Update section article list isn't moving");
                }
        }


        private static String getIframePosition(){
                try{
                        element = driver.findElement(By.id("F_Content"));
                        driver.switchTo().frame(element.findElement(By.tagName("iframe")));
                        element = driver.findElement(By.id("aTicker"));
                }catch (Exception e){
                }
                String position = element.getAttribute("style"); // Get the position
                return position;
        }

// Checks if update section is moving on mouse hover.
        static void checkUpdateSectionMovingMouseHover(){
                Actions action = new Actions(driver);
                action.moveToElement(element).build().perform();
                String firstPosition = getIframePosition();
                try{
                        Thread.sleep(5000);
                }
                catch(InterruptedException e) {
                        System.out.println("got interrupted!");
                }
                String secPosition = getIframePosition();
                if(firstPosition.equals(secPosition)){
                        System.out.println("Update section article list static on mouse hover");
                }else{
                        System.out.println("Update section article list moving on mouse hover");
                }


        }




}
