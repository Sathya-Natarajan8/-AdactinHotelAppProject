package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.ProjectSpecificationMethods;

import java.time.Duration;

public class EmailVerificationPage extends ProjectSpecificationMethods {
    WebDriver driver;

    @FindBy(xpath = "//*[contains(text(),'Thank you for verifying your email address')]")
    WebElement verificationSuccessMessage;

    @FindBy(xpath = "//a[contains(text(),'Click here to login')]")
    WebElement loginLink;

    public EmailVerificationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isEmailVerificationSuccessful() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Increased from 10 to 20 seconds
        wait.until(ExpectedConditions.visibilityOf(verificationSuccessMessage));
        return verificationSuccessMessage.isDisplayed();
    }

    public void clickLoginLink() {
        loginLink.click();
    }
}
