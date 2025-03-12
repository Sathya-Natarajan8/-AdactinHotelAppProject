package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import base.ProjectSpecificationMethods;
import pages.SearchHotelPage;
import pages.SelectHotelPage;
import pages.BookHotelPage;

public class TC005_BookHotelTest extends ProjectSpecificationMethods {

	@BeforeTest
	public void setup() {
		sheetname = "BookHotelTest";
		testName = "Book Hotel Test";
		testDescription = "Testing the booking functionality";
		testAuthor = "Sathya";
		testCategory = "Functional Testing";
	}

	@Test
	public void testBookHotel() {
		SearchHotelPage searchHotelPage = new SearchHotelPage(driver);
		SelectHotelPage selectHotelPage = searchHotelPage.selectLocation("Sydney").selectHotel("Hotel Creek")
				.selectRoomType("Deluxe").selectNumberOfRooms("1 - One").enterCheckInDate("12/03/2025")
				.enterCheckOutDate("15/03/2025").selectAdultsPerRoom("2 - Two").selectChildrenPerRoom("0 - None")
				.clickSearch();

		BookHotelPage bookHotelPage = selectHotelPage.selectHotel().clickContinue();

		bookHotelPage.enterFirstName("John").enterLastName("Doe").enterBillingAddress("123 Street, Sydney")
				.enterCreditCardNumber("4111111111111111").selectCreditCardType("VISA").selectExpiryMonth("April")
				.selectExpiryYear("2027").enterCVVNumber("123").clickBookNow();
		String orderNumber = bookHotelPage.getOrderNumber();
		Assert.assertNotNull(orderNumber, "Booking failed! Order number was not generated.");
		System.out.println("Booking Successful! Order Number: " + orderNumber);
	}
}
