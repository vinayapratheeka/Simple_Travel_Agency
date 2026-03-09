package pages;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

public class PurchasePage {
    WebDriver driver;
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
        wait.until(ExpectedConditions.visibilityOfElementLocated(name))
                .sendKeys(nameValue);
        wait.until(ExpectedConditions.visibilityOfElementLocated(address))
                .sendKeys(addressValue);
        wait.until(ExpectedConditions.visibilityOfElementLocated(city))
                .sendKeys(cityValue);
        wait.until(ExpectedConditions.visibilityOfElementLocated(state))
                .sendKeys(stateValue);
        Actions action = new Actions(driver);
        action.scrollByAmount(0, 300).perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(zip))
                .sendKeys(zipValue);
        wait.until(ExpectedConditions.visibilityOfElementLocated(card))
                .sendKeys(cardValue);
        wait.until(ExpectedConditions.visibilityOfElementLocated(name_on_card))
        .sendKeys(nameoncardValue);
        wait.until(ExpectedConditions.elementToBeClickable(purchase)).click();
    }
}