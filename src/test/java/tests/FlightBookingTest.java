package tests;

import org.testng.Assert;
import org.testng.annotations.*;
import base.BaseClass;
import pages.*;
import listeners.TestListener;
import reports.ExtentManager;
import utils.ExcelUtils;

import com.aventstack.extentreports.*;
@Listeners(TestListener.class)

public class FlightBookingTest extends BaseClass {
    ExtentReports extent = ExtentManager.getExtentReport();
    ExtentTest test;
    @DataProvider(name="flightData")
    public Object[][] getData() {
        return ExcelUtils.getData();
    }
    @Test(dataProvider="flightData")
    public void bookFlightTest(String name, String address, String city,
            String state, String zip, String card, String name_on_card) throws InterruptedException{
        test = extent.createTest("Flight Booking Test");
        HomePage home = new HomePage(driver);
        home.verifyHomePage();
        home.searchFlights();
        test.info("Flights searched");
        Thread.sleep(1500);

        FlightsPage flights = new FlightsPage(driver);
        flights.verifyFlightsPage();
        flights.selectFlight();
        test.info("Flight selected");
        Thread.sleep(1500);

        PurchasePage purchase = new PurchasePage(driver);
        purchase.verifyPurchasePage();
        purchase.enterDetails(name, address, city, state, zip, card, name_on_card);
        test.info("Passenger details entered");
        Thread.sleep(1500);
        System.out.println(name + " " + address + " " + city);

        ConfirmationPage confirm = new ConfirmationPage(driver);
        String msg = confirm.getConfirmationMessage();
        Assert.assertTrue(msg.contains("Thank you"));
        test.pass("Flight booking successful");
        Thread.sleep(1500);
    }
    @AfterMethod
    public void flushReport(){
        extent.flush();
    }
}