package pages;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

public class FlightsPage {
    WebDriver driver;
    public FlightsPage(WebDriver driver) {
        this.driver = driver;
    }
    By chooseFlight = By.xpath("(//input[@value='Choose This Flight'])[1]");
    public void verifyFlightsPage() {
        Assert.assertTrue(driver.getPageSource().contains("Flights"),"Flights Page not displayed");
    }
    public void selectFlight() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(chooseFlight)).click();
    }
}
