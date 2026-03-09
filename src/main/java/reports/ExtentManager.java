package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    public static ExtentReports getExtentReport() {
        ExtentSparkReporter reporter =
                new ExtentSparkReporter("./Reports/ExtentReport.html");
        reporter.config().setReportName("BlazeDemo Automation Report");
        reporter.config().setDocumentTitle("Automation Test Results");
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        return extent;
    }
}