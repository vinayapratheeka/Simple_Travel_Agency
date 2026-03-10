package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseClass {
	public static WebDriver driver;
	public static Logger log = LogManager.getLogger(BaseClass.class);
	@BeforeMethod
	@Parameters("browser")
	public void setup(@Optional("firefox")String browser){
		log.info("Launching browser");
		boolean isJenkins = System.getenv("JENKINS_HOME") != null;
		if(browser.equalsIgnoreCase("chrome")){
			WebDriverManager.chromedriver().setup();
			//driver = new ChromeDriver();
			ChromeOptions options = new ChromeOptions();
			if(isJenkins) {
				options.addArguments("--headless");
				options.addArguments("--disable-gpu");
				options.addArguments("--window-size=1920,1080");
				options.addArguments("--disable-dev-shm-usage");
				options.addArguments("--no-sandbox");
			}

			driver = new ChromeDriver(options);
		}
		else if(browser.equalsIgnoreCase("edge")){
			//WebDriverManager.edgedriver().clearDriverCache().setup();
			System.setProperty("webdriver.edge.driver",
					"C:\\Users\\HP\\OneDrive\\Desktop\\Selenium Integration\\edgedriver_win64\\msedgedriver.exe");
			//driver = new EdgeDriver();
			EdgeOptions options = new EdgeOptions();
			if(isJenkins) {
				options.addArguments("--headless");
				options.addArguments("--disable-gpu");
				options.addArguments("--window-size=1920,1080");
				options.addArguments("--disable-dev-shm-usage");
				options.addArguments("--no-sandbox");
			}
			driver = new EdgeDriver(options);

		}
		else if(browser.equalsIgnoreCase("firefox")){
			WebDriverManager.firefoxdriver().setup();
			//driver = new FirefoxDriver();
			FirefoxOptions options = new FirefoxOptions();
			if(isJenkins) {
				options.addArguments("--headless");
			}
			driver = new FirefoxDriver(options);
		}
		driver.manage().window().maximize();
		log.info("Browser launched successfully: " + browser);
		driver.get("https://blazedemo.com");
		log.info("Application launched");
		}
	/*public void setup(String browser){
		log.info("Launching browser");
		if(browser.equalsIgnoreCase("chrome")){
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			}
		else if(browser.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver",
					"C:\\Users\\HP\\OneDrive\\Desktop\\Selenium Integration\\edgedriver_win64\\msedgedriver.exe");
			driver = new EdgeDriver();
			}
		else if(browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			}
		driver.manage().window().maximize();
		log.info("Browser launched successfully: " + browser);
		driver.get("https://blazedemo.com");
		log.info("Application launched");
	}*/
	@AfterMethod
	public void tearDown(){
		log.info("Closing browser");
		if(driver != null){
			driver.quit();
		}
	}
}