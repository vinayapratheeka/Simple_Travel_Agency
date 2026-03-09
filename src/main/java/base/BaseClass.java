package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
    public static WebDriver driver;
    public static Logger log = LogManager.getLogger();
    @BeforeMethod
    public void setup() {
	    	log.info("Launching browser");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://blazedemo.com/");
        log.info("Navigated to BlazeDemo website");
    }
    @AfterMethod
    public void tearDown() {
    		log.info("Closing browser");
        driver.quit();
    }
}