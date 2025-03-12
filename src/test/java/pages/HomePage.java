package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
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

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public RegistrationPage clickRegisterLink() {
		registerLink.click();
		return new RegistrationPage(driver);
	}

	public LoginPage clickLoginLink() {
		loginLink.click();
		return new LoginPage(driver);
	}

	public boolean isUserLoggedIn() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {
			return wait.until(ExpectedConditions.visibilityOf(welcomeMessage)).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public void clickBookedItinerary() {
		bookedItineraryLink.click();
	}

	public void clickLogout() {
		logoutLink.click();
		driver.switchTo().alert().accept();
	}

	public boolean isLogoutSuccessful() {
		return loginPageUsernameField.isDisplayed();
	}

}
