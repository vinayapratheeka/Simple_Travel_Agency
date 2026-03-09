package pages;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class ConfirmationPage {
    WebDriver driver;
    public ConfirmationPage(WebDriver driver) {
        this.driver = driver;
    }
    By message = By.tagName("h1");
    public String getConfirmationMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(message)).getText();
    }
}