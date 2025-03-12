package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.ProjectSpecificationMethods;

import java.time.Duration;

public class SelectHotelPage extends ProjectSpecificationMethods {
	WebDriver driver;

	@FindBy(xpath = "//input[@id='radiobutton_0']")
	private WebElement selectHotelRadioButton;

	@FindBy(xpath = "//input[@id='continue']")
	private WebElement continueButton;

	@FindBy(xpath = "///td[text()='Book A Hotel ']")
	private WebElement bookHotelPageHeader;

	// Constructor
	public SelectHotelPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public SelectHotelPage selectHotel() {
		selectHotelRadioButton.click();
		return this;
	}

	public BookHotelPage clickContinue() {
		continueButton.click();
		return new BookHotelPage(driver);
	}

	public boolean isHotelSelectionSuccessful() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.visibilityOf(bookHotelPageHeader)).isDisplayed();
	}

	public boolean isNavigationSuccessful() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {
			return wait.until(ExpectedConditions.visibilityOf(bookHotelPageHeader)).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

}
