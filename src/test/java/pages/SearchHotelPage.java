package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import base.ProjectSpecificationMethods;
import java.time.Duration;

public class SearchHotelPage extends ProjectSpecificationMethods {
	WebDriver driver;

	// Page Elements - Locators
	@FindBy(xpath = "//td/select[@id='location']")
	private WebElement locationDropdown;

	@FindBy(xpath = "//select[@id='hotels']")
	private WebElement hotelsDropdown;

	@FindBy(xpath = "//select[@id='room_type']")
	private WebElement roomTypeDropdown;

	@FindBy(xpath = "//select[@id='room_nos']")
	private WebElement numberOfRoomsDropdown;

	@FindBy(xpath = "//input[@id='datepick_in']")
	private WebElement checkInDateField;

	@FindBy(xpath = "//input[@id='datepick_out']")
	private WebElement checkOutDateField;

	@FindBy(xpath = "//select[@id='adult_room']")
	private WebElement adultsPerRoomDropdown;

	@FindBy(xpath = "//select[@id='child_room']")
	private WebElement childrenPerRoomDropdown;

	@FindBy(xpath = "//input[@id='Submit']")
	private WebElement searchButton;

	@FindBy(xpath = "//td[text()='Select Hotel ']")
	private WebElement searchSuccessMessage;

	// Constructor
	public SearchHotelPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public SearchHotelPage selectLocation(String location) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		// Wait until the dropdown is visible
		WebElement locationDropdown = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='location']")));

		// Wait until the dropdown contains at least one option
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//select[@id='location']/option"), 1));

		// Select the location
		Select select = new Select(locationDropdown);
		select.selectByVisibleText(location);

		return this; // Returning 'this' allows method chaining
	}

	public SearchHotelPage selectHotel(String hotel) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		wait.until(ExpectedConditions.visibilityOf(hotelsDropdown));

		for (int i = 0; i < 2; i++) { // Retry once if necessary
			try {
				new Select(hotelsDropdown).selectByVisibleText(hotel);
				return this;
			} catch (StaleElementReferenceException e) {
				System.out.println("Retrying selectHotel() due to stale element");
			}
		}
		return this;
	}

	public SearchHotelPage selectRoomType(String roomType) {
		new Select(roomTypeDropdown).selectByVisibleText(roomType);
		return this;
	}

	public SearchHotelPage selectNumberOfRooms(String numberOfRooms) {
		new Select(numberOfRoomsDropdown).selectByVisibleText(numberOfRooms);
		return this;
	}

	public SearchHotelPage enterCheckInDate(String checkInDate) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].value='" + checkInDate + "';", checkInDateField);
		return this;
	}

	public SearchHotelPage enterCheckOutDate(String checkOutDate) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].value='" + checkOutDate + "';", checkOutDateField);
		return this;
	}

	public SearchHotelPage selectAdultsPerRoom(String adults) {
		new Select(adultsPerRoomDropdown).selectByVisibleText(adults);
		return this;
	}

	public SearchHotelPage selectChildrenPerRoom(String children) {
		new Select(childrenPerRoomDropdown).selectByVisibleText(children);
		return this;
	}

	public SelectHotelPage clickSearch() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(searchButton));
		searchButton.click();
		return new SelectHotelPage(driver);
	}

	public boolean isSearchSuccessful() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.visibilityOf(searchSuccessMessage)).isDisplayed();
	}

	public void searchForHotel(String location, String hotel, String roomType, int numberOfRooms, String checkIn,
			String checkOut, int adults, int children) {
		selectLocation(location).selectHotel(hotel).selectRoomType(roomType)
				.selectNumberOfRooms(String.valueOf(numberOfRooms)).enterCheckInDate(checkIn)
				.enterCheckOutDate(checkOut).selectAdultsPerRoom(String.valueOf(adults))
				.selectChildrenPerRoom(String.valueOf(children)).clickSearch();
	}
}
