package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import base.ProjectSpecificationMethods;
import pages.SearchHotelPage;
import pages.SelectHotelPage;

public class TC004_SelectHotelTest extends ProjectSpecificationMethods {

	@BeforeTest
	public void setup() {
		sheetname = "SelectHotelTest";
		testName = "Select Hotel Test";
		testDescription = "Testing the select hotel functionality";
		testAuthor = "Sathya";
		testCategory = "Functional Testing";
	}

	@Test
	public void testSelectHotel() {
		SearchHotelPage searchHotelPage = new SearchHotelPage(driver);
		SelectHotelPage selectHotelPage = searchHotelPage.selectLocation("Sydney").selectHotel("Hotel Creek")
				.selectRoomType("Deluxe").selectNumberOfRooms("1 - One").enterCheckInDate("12/03/2025")
				.enterCheckOutDate("15/03/2025").selectAdultsPerRoom("2 - Two").selectChildrenPerRoom("0 - None")
				.clickSearch();

		Assert.assertTrue(selectHotelPage.isHotelSelectionSuccessful(), "Hotel selection page did not load properly!");

		selectHotelPage.selectHotel();
		selectHotelPage.clickContinue();

		Assert.assertTrue(selectHotelPage.isNavigationSuccessful(), "Failed to navigate to the next page!");
	}
}
