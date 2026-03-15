package pages;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

public class PurchasePage {
    WebDriver driver;
    Logger log = LogManager.getLogger(PurchasePage.class);
    public PurchasePage(WebDriver driver) {
        this.driver = driver;
    }
    By name = By.id("inputName");
    By address = By.id("address");
    By city = By.id("city");
    By state = By.id("state");
    By zip = By.id("zipCode");
    By card = By.id("creditCardNumber");
    By name_on_card = By.id("nameOnCard");
    By purchase = By.xpath("//input[@value='Purchase Flight']");
    public void verifyPurchasePage() {
        Assert.assertTrue(driver.getPageSource().contains("Your flight"),"Purchase Page not loaded");
    }
    public void enterDetails(String nameValue, String addressValue, String cityValue,
            String stateValue, String zipValue, String cardValue, String nameoncardValue) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        log.info("Entering passenger name: " + nameValue);
        wait.until(ExpectedConditions.visibilityOfElementLocated(name))
                .sendKeys(nameValue);
        log.info("Entering address: " + addressValue);
        wait.until(ExpectedConditions.visibilityOfElementLocated(address))
                .sendKeys(addressValue);
        log.info("Entering city: " + cityValue);
        wait.until(ExpectedConditions.visibilityOfElementLocated(city))
                .sendKeys(cityValue);
        log.info("Entering state: " + stateValue);
        wait.until(ExpectedConditions.visibilityOfElementLocated(state))
                .sendKeys(stateValue);
        Actions action = new Actions(driver);
        action.scrollByAmount(0, 300).perform();
        log.info("Entering zip code: " + zipValue);
        wait.until(ExpectedConditions.visibilityOfElementLocated(zip))
                .sendKeys(zipValue);
        log.info("Entering credit card number");
        wait.until(ExpectedConditions.visibilityOfElementLocated(card))
                .sendKeys(cardValue);
        log.info("Entering name on card: " + nameoncardValue);
        wait.until(ExpectedConditions.visibilityOfElementLocated(name_on_card))
        .sendKeys(nameoncardValue);
        log.info("Clicking Purchase Flight button");
        wait.until(ExpectedConditions.elementToBeClickable(purchase)).click();
    }
}