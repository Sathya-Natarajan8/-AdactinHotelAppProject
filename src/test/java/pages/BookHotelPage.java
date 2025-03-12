package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.ProjectSpecificationMethods;

import java.time.Duration;

public class BookHotelPage extends ProjectSpecificationMethods {
	WebDriver driver;

	@FindBy(id = "first_name")
	private WebElement firstNameField;

	@FindBy(id = "last_name")
	private WebElement lastNameField;

	@FindBy(id = "address")
	private WebElement billingAddressField;

	@FindBy(id = "cc_num")
	private WebElement creditCardNumberField;

	@FindBy(id = "cc_type")
	private WebElement creditCardTypeDropdown;

	@FindBy(id = "cc_exp_month")
	private WebElement expiryMonthDropdown;

	@FindBy(id = "cc_exp_year")
	private WebElement expiryYearDropdown;

	@FindBy(id = "cc_cvv")
	private WebElement cvvNumberField;

	@FindBy(id = "book_now")
	private WebElement bookNowButton;

	@FindBy(id = "order_no")
	private WebElement orderNumberField;

	public BookHotelPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public BookHotelPage enterFirstName(String firstName) {
		firstNameField.sendKeys(firstName);
		return this;
	}

	public BookHotelPage enterLastName(String lastName) {
		lastNameField.sendKeys(lastName);
		return this;
	}

	public BookHotelPage enterBillingAddress(String address) {
		billingAddressField.sendKeys(address);
		return this;
	}

	public BookHotelPage enterCreditCardNumber(String cardNumber) {
		creditCardNumberField.sendKeys(cardNumber);
		return this;
	}

	public BookHotelPage selectCreditCardType(String cardType) {
		new org.openqa.selenium.support.ui.Select(creditCardTypeDropdown).selectByVisibleText(cardType);
		return this;
	}

	public BookHotelPage selectExpiryMonth(String month) {
		new org.openqa.selenium.support.ui.Select(expiryMonthDropdown).selectByVisibleText(month);
		return this;
	}

	public BookHotelPage selectExpiryYear(String year) {
		new org.openqa.selenium.support.ui.Select(expiryYearDropdown).selectByVisibleText(year);
		return this;
	}

	public BookHotelPage enterCVVNumber(String cvv) {
		cvvNumberField.sendKeys(cvv);
		return this;
	}

	public void clickBookNow() {
		bookNowButton.click();
	}

	public String getOrderNumber() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(orderNumberField));
		return orderNumberField.getAttribute("value");
	}

	public void enterBookingDetails(String firstName, String lastName, String billingAddress, String creditCardNumber,
			String creditCardType, String expiryMonth, String expiryYear, String cvv) {
		enterFirstName(firstName).enterLastName(lastName).enterBillingAddress(billingAddress)
				.enterCreditCardNumber(creditCardNumber).selectCreditCardType(creditCardType)
				.selectExpiryMonth(expiryMonth).selectExpiryYear(expiryYear).enterCVVNumber(cvv);
	}

}
