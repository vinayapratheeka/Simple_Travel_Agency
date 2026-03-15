package pages;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

public class HomePage {

    WebDriver driver;
    Logger log = LogManager.getLogger(HomePage.class);
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    By departure = By.name("fromPort");
    By destination = By.name("toPort");
    By findFlights = By.xpath("//input[@value='Find Flights']");
    public void verifyHomePage() {
    		log.info("Verifying Home Page is loaded");
        Assert.assertTrue(driver.getTitle().contains("BlazeDemo"), "Home Page not loaded");
        log.info("Home Page loaded successfully");
    }
    public void searchFlights() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        log.info("Selecting departure city from dropdown");
        Select dep = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(departure)));
        dep.selectByVisibleText("Mexico City");
        log.info("Departure city selected: Mexico City");
        log.info("Selecting destination city from dropdown");
        Select dest = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(destination)));
        dest.selectByVisibleText("London");
        //dest.selectByVisibleText("");
        log.info("Destination city selected: London");
        log.info("Clicking Find Flights button");
        wait.until(ExpectedConditions.elementToBeClickable(findFlights)).click();
    }
}



    

  