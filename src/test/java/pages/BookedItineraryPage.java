package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import base.ProjectSpecificationMethods;

public class BookedItineraryPage extends ProjectSpecificationMethods {
	WebDriver driver;

	@FindBy(id = "order_id_text")
	WebElement searchBookingField;

	@FindBy(id = "search_hotel_id")
	WebElement searchButton;

	@FindBy(name = "ids[]")
	public WebElement selectBookingCheckbox;

	@FindBy(name = "cancelall")
	WebElement cancelButton;

	@FindBy(xpath = "//a[contains(text(),'Logout')]")
	WebElement logoutLink;

	public BookedItineraryPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void searchBooking(String orderID) {
		searchBookingField.sendKeys(orderID);
		searchButton.click();
	}

	public void cancelBooking() {
		selectBookingCheckbox.click();
		cancelButton.click();
		driver.switchTo().alert().accept();
	}

	public void clickLogout() {
		logoutLink.click();
		driver.switchTo().alert().accept();
	}

}
