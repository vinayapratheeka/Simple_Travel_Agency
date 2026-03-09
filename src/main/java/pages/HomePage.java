package pages;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

public class HomePage {

    WebDriver driver;
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    By departure = By.name("fromPort");
    By destination = By.name("toPort");
    By findFlights = By.xpath("//input[@value='Find Flights']");
    public void verifyHomePage() {
        Assert.assertTrue(driver.getTitle().contains("BlazeDemo"));
    }
    public void searchFlights() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Select dep = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(departure)));
        dep.selectByVisibleText("Mexico City");
        Select dest = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(destination)));
        dest.selectByVisibleText("London");
        wait.until(ExpectedConditions.elementToBeClickable(findFlights)).click();
    }
}


    

  