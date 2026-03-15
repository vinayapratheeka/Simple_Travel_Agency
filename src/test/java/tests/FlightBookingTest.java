package tests;

import org.testng.Assert;
import org.testng.annotations.*;
import base.BaseClass;
import pages.*;
import listeners.TestListener;
import reports.ExtentManager;
import utils.CSVUtils;
import utils.ExcelUtils;
import utils.DBUtils;
import utils.RetryAnalyzer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.aventstack.extentreports.*;

@Listeners(TestListener.class)

public class FlightBookingTest extends BaseClass {
    Logger log = LogManager.getLogger(FlightBookingTest.class);
    ExtentReports extent = ExtentManager.getExtentReport();
    ExtentTest test;
    // Excel Data
    @DataProvider(name="excelData")
    public Object[][] getExcelData(){
        return ExcelUtils.getData();
    }
    // CSV Data
    @DataProvider(name="csvData")
    public Object[][] getCSVData(){
        return CSVUtils.getCSVData();
    }
    // Database Data
    @DataProvider(name="dbData")
    public Object[][] getDBData(){
        return DBUtils.getDBData();
    }
    // Excel Test
    @Test(dataProvider="excelData",retryAnalyzer=RetryAnalyzer.class,priority=1)
    public void bookFlightExcel(String name,String address,String city,
            String state,String zip,String card,String name_on_card) throws InterruptedException{
        executeFlightBooking(name,address,city,state,zip,card,name_on_card,"Excel Test");
    }
    // CSV Test
    @Test(dataProvider="csvData",retryAnalyzer=RetryAnalyzer.class,priority=2)
    public void bookFlightCSV(String name,String address,String city,
            String state,String zip,String card,String name_on_card) throws InterruptedException{
        executeFlightBooking(name,address,city,state,zip,card,name_on_card,"CSV Test");
    }
    // Database Test
    @Test(dataProvider="dbData",retryAnalyzer=RetryAnalyzer.class,priority=3)
    public void bookFlightDB(String name,String address,String city,
            String state,String zip,String card,String name_on_card) throws InterruptedException{
        executeFlightBooking(name,address,city,state,zip,card,name_on_card,"Database Test");
    }
    // Common Test Logic
    public void executeFlightBooking(String name,String address,String city,
            String state,String zip,String card,String name_on_card,String testName) throws InterruptedException{
        test = extent.createTest(testName);
        log.info("Running test : " + testName);
        HomePage home = new HomePage(driver);
        home.verifyHomePage();
        Thread.sleep(1500);
        home.searchFlights();
        Thread.sleep(1500);
        log.info("Flights searched");
        test.info("Flights searched");

        FlightsPage flights = new FlightsPage(driver);
        flights.verifyFlightsPage();
        Thread.sleep(1500);
        flights.selectFlight();
        Thread.sleep(1500);
        log.info("Flight selected");
        test.info("Flight selected");

        PurchasePage purchase = new PurchasePage(driver);
        purchase.verifyPurchasePage();
        Thread.sleep(1500);
        purchase.enterDetails(name,address,city,state,zip,card,name_on_card);
        Thread.sleep(1500);
        log.info("Passenger details entered");
        test.info("Passenger details entered");

        ConfirmationPage confirm = new ConfirmationPage(driver);
        String msg = confirm.getConfirmationMessage();
        Assert.assertTrue(msg.contains("Thank you"));
        log.info("Flight booking successful");
        test.pass("Flight booking successful");
    }
    @AfterSuite
    public void flushReport(){
        extent.flush();
    }
}