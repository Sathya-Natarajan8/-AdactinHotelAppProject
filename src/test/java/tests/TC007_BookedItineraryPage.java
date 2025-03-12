package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import base.ProjectSpecificationMethods;
import pages.BookedItineraryPage;

public class TC007_BookedItineraryPage extends ProjectSpecificationMethods {

    @BeforeTest
    public void setup() {
        sheetname = "BookedItineraryTest";  // Excel sheet name
        testName = "Booked Itinerary Test";
        testDescription = "Verify search and cancel booking functionality";
        testAuthor = "Sathya";
        testCategory = "Functional Testing";
    }

    @Test(dataProvider = "excelRead")
    public void testCancelBooking(String firstName, String lastName, String address, 
                                  String cardNumber, String cardType, 
                                  String expMonth, String expYear, String cvv) {
        // Step 1: Search for the Booking
        BookedItineraryPage bookedItineraryPage = new BookedItineraryPage(driver);
        bookedItineraryPage.searchBooking(cardNumber); // Assuming booking is linked to card number

        // Step 2: Cancel the Booking
        bookedItineraryPage.cancelBooking();
        Assert.assertTrue(driver.getPageSource().contains("No bookings found"), "Booking was not canceled properly!");
        System.out.println("Booking Canceled for: " + firstName + " " + lastName);

        // Step 3: Logout
        bookedItineraryPage.clickLogout();
        Assert.assertTrue(driver.getTitle().contains("Login"), "Logout unsuccessful!");
    }
}
