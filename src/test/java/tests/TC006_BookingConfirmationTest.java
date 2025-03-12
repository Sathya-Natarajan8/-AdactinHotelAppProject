package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import base.ProjectSpecificationMethods;
import pages.BookHotelPage;
import pages.BookingConfirmationPage;

public class TC006_BookingConfirmationTest extends ProjectSpecificationMethods {

	@BeforeTest
	public void setup() {
		sheetname = "BookingConfirmationTest"; // Excel sheet name
		testName = "Booking Confirmation Test";
		testDescription = "Verify booking confirmation and order number";
		testAuthor = "Sathya";
		testCategory = "Functional Testing";
	}

	@Test(dataProvider = "excelRead")
	public void testBookingConfirmation(String firstName, String lastName, String address, String cardNumber,
			String cardType, String expMonth, String expYear, String cvv) {
		BookHotelPage bookHotelPage = new BookHotelPage(driver);
		bookHotelPage.enterFirstName(firstName).enterLastName(lastName).enterBillingAddress(address)
				.enterCreditCardNumber(cardNumber).selectCreditCardType(cardType).selectExpiryMonth(expMonth)
				.selectExpiryYear(expYear).enterCVVNumber(cvv).clickBookNow();

		BookingConfirmationPage bookingConfirmationPage = new BookingConfirmationPage(driver);
		Assert.assertTrue(bookingConfirmationPage.isBookingConfirmed(), "Booking confirmation message not displayed!");
		System.out.println("Booking Confirmed for: " + firstName + " " + lastName);

		String orderNumber = bookingConfirmationPage.getOrderNumber();
		Assert.assertNotNull(orderNumber, "Order number is not generated!");
		System.out.println("Order Number: " + orderNumber);

		bookingConfirmationPage.logout();
		Assert.assertTrue(driver.getTitle().contains("Login"), "Logout unsuccessful!");
	}
}
