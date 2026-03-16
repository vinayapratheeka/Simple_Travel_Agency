package pages;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class ConfirmationPage {
    WebDriver driver;
    Logger log = LogManager.getLogger(ConfirmationPage.class);
    public ConfirmationPage(WebDriver driver) {
        this.driver = driver;
    }
    By message = By.tagName("h1");
    public String getConfirmationMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        log.info("Fetching confirmation message from confirmation page - msg received");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(message)).getText();
    }
}