package tests;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import base.ProjectSpecificationMethods;
import pages.LoginPage;
import pages.SearchHotelPage;
import pages.SelectHotelPage;

public class TC003_SearchFunctionalityTest extends ProjectSpecificationMethods {

	@BeforeTest
	public void setup() {
		sheetname = "SearchHotelTest";
		testName = "Search Hotel Test";
		testDescription = "Testing the search hotel functionality using Excel data";
		testAuthor = "Sathya";
		testCategory = "Functional Testing";
	}

	public void loginBeforeTest() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterUsername("yourUsername").enterPassword("yourPassword").clickLogin();
	}

	@Test(dataProvider = "excelRead")
	public void testSearchHotel(String location, String hotel, String roomType, String numberOfRooms,
			String checkInDate, String checkOutDate, String adults, String children) {

		SearchHotelPage searchHotelPage = new SearchHotelPage(driver);

		SelectHotelPage selectHotelPage = searchHotelPage.selectLocation(location).selectHotel(hotel)
				.selectRoomType(roomType).selectNumberOfRooms(numberOfRooms).enterCheckInDate(checkInDate)
				.enterCheckOutDate(checkOutDate).selectAdultsPerRoom(adults).selectChildrenPerRoom(children)
				.clickSearch();

		Assert.assertTrue(selectHotelPage.isHotelSelectionSuccessful(), "Hotel selection page did not load properly!");
	}

	@AfterTest
	public void tearDown() throws InterruptedException {
		Thread.sleep(5000);
		driver.quit();
	}

}
