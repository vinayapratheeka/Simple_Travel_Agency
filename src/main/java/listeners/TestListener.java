package listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;

import base.BaseClass;
import utils.ScreenshotUtil;
public class TestListener implements ITestListener {
    public void onTestFailure(ITestResult result) {
        ScreenshotUtil.captureScreenshot(BaseClass.driver, result.getName());
    }
}