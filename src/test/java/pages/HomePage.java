package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration; // ✅ Correct import
import base.ProjectSpecificationMethods;

public class HomePage extends ProjectSpecificationMethods {
	WebDriver driver;

	@FindBy(xpath = "//a[contains(text(),'New User Register Here')]")
	private WebElement registerLink;

	@FindBy(xpath = "//input[@id='login']")
	private WebElement loginLink;

	@FindBy(xpath = "//td[contains(text(), 'Welcome')]")
	private WebElement welcomeMessage;

	@FindBy(linkText = "Booked Itinerary")
	WebElement bookedItineraryLink;

	@FindBy(xpath = "//a[contains(text(),'Logout')]")
	WebElement logoutLink;

	private WebElement loginPageUsernameField;

	// Constructor
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Click Register Link
	public RegistrationPage clickRegisterLink() {
		registerLink.click();
		return new RegistrationPage(driver);
	}

	// Click Login Link
	public LoginPage clickLoginLink() {
		loginLink.click();
		return new LoginPage(driver);
	}

	// Check if the User is Logged In
	public boolean isUserLoggedIn() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // ✅ Correct Duration import
		try {
			return wait.until(ExpectedConditions.visibilityOf(welcomeMessage)).isDisplayed();
		} catch (Exception e) {
			return false; // If the element is not found within timeout
		}
	}

	public void clickBookedItinerary() {
		bookedItineraryLink.click();
	}

	public void clickLogout() {
		logoutLink.click();
		driver.switchTo().alert().accept(); // Handle logout alert
	}

	public boolean isLogoutSuccessful() {
		return loginPageUsernameField.isDisplayed(); // If login page is shown, logout was successful
	}

}
