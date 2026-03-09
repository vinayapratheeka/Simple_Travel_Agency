package utils;

import org.openqa.selenium.*;
import java.io.File;
import org.apache.commons.io.FileUtils;

public class ScreenshotUtil {
    public static void captureScreenshot(WebDriver driver,String testName) {
        try {
            TakesScreenshot ts = (TakesScreenshot)driver;
            File src = ts.getScreenshotAs(OutputType.FILE);
            File dest = new File("./Screenshots/" + testName + ".png");
            FileUtils.copyFile(src,dest);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}