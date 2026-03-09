package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseClass {
	public static WebDriver driver;
	public static Logger log = LogManager.getLogger(BaseClass.class);
	@BeforeMethod
	@Parameters("browser")
	public void setup(@Optional("edge")String browser){
		log.info("Launching browser");
		if(browser.equalsIgnoreCase("chrome")){
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("edge")){
			//WebDriverManager.edgedriver().clearDriverCache().setup();
			System.setProperty("webdriver.edge.driver",
					"C:\\Users\\HP\\OneDrive\\Desktop\\Selenium Integration\\edgedriver_win64\\msedgedriver.exe");
			driver = new EdgeDriver();
		}
		else if(browser.equalsIgnoreCase("firefox")){
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		log.info("Browser launched successfully: " + browser);
		driver.get("https://blazedemo.com");
		log.info("Application launched");
		}
	@AfterMethod
	public void tearDown(){
		log.info("Closing browser");
		if(driver != null){
			driver.quit();
			driver = null;
		}
	}
}