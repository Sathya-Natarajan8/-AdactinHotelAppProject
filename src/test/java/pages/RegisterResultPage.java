package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.ProjectSpecificationMethods;

import java.time.Duration;

public class RegisterResultPage extends ProjectSpecificationMethods {
	WebDriver driver;

	@FindBy(xpath = "//td[@class='reg_success' and contains(text(), 'An email verification code has been sent')]")
	WebElement emailVerificationMessage;

	@FindBy(xpath = "//a[contains(text(),'Click here to login')]")
	WebElement loginLink;

	public RegisterResultPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public boolean isemailVerificationMessageDisplayed() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.visibilityOf(emailVerificationMessage)).isDisplayed();
	}

	public boolean isEmailVerificationSuccessful() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//body[contains(text(),'Thankyou for verifying your email address.')]")));
		return successMessage.isDisplayed();
	}

	public void clickLoginLink() {
		loginLink.click();
	}
}
