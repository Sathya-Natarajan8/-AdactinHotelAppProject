package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.ProjectSpecificationMethods;

import java.time.Duration;

public class LoginPageResult extends ProjectSpecificationMethods {
	WebDriver driver;

	@FindBy(xpath = "//td[contains(text(),'Welcome to Adactin Group of Hotels')]")
	private WebElement welcomeMessage;

	@FindBy(xpath = "//span[contains(@class, 'login_error')]")
	private WebElement errorMessage;

	public LoginPageResult(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public boolean isLoginSuccessful() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		return wait.until(ExpectedConditions.visibilityOf(welcomeMessage)).isDisplayed();
	}

	public boolean isLoginFailed() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		return wait.until(ExpectedConditions.visibilityOf(errorMessage)).isDisplayed();
	}

	public String getErrorMessage() {
		return errorMessage.getText();
	}
}
