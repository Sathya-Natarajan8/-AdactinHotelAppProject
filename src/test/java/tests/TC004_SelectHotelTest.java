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
        sheetname = "SelectHotelTest";  // Sheet name in Excel (if used)
        testName = "Select Hotel Test";
        testDescription = "Testing the select hotel functionality";
        testAuthor = "Sathya";
        testCategory = "Functional Testing";
    }

    @Test
    public void testSelectHotel() {
        // Step 1: Perform a hotel search first
        SearchHotelPage searchHotelPage = new SearchHotelPage(driver);
        SelectHotelPage selectHotelPage = searchHotelPage
                .selectLocation("Sydney")
                .selectHotel("Hotel Creek")
                .selectRoomType("Deluxe")
                .selectNumberOfRooms("1 - One")
                .enterCheckInDate("12/03/2025")
                .enterCheckOutDate("15/03/2025")
                .selectAdultsPerRoom("2 - Two")
                .selectChildrenPerRoom("0 - None")
                .clickSearch();

        // Step 2: Verify hotel selection page is displayed
        Assert.assertTrue(selectHotelPage.isHotelSelectionSuccessful(), "Hotel selection page did not load properly!");

        // Step 3: Select a hotel and proceed
        selectHotelPage.selectHotel();
        selectHotelPage.clickContinue();

        // Step 4: Verify that the next page is loaded (replace with actual verification)
        Assert.assertTrue(selectHotelPage.isNavigationSuccessful(), "Failed to navigate to the next page!");
    }
}
