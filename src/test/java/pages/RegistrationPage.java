package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Scanner;
import base.ProjectSpecificationMethods;

import java.time.Duration;

public class RegistrationPage extends ProjectSpecificationMethods {
    WebDriver driver;

    @FindBy(xpath = "//td[text()='Username']/following-sibling::td//input") 
    WebElement usernameField;
    
    @FindBy(xpath = "//input[@name='password']") 
    WebElement passwordField;

    @FindBy(xpath = "//input[@id='re_password']")
    WebElement confirmPasswordField;
    
    @FindBy(xpath = "//input[@id='full_name']")
    WebElement fullNameTextField;

    @FindBy(xpath = "//input[@id='email_add']")
    WebElement emailField;
    
    @FindBy(xpath = "//input[@id='captcha-form']")
    WebElement captchaTextField;

    @FindBy(xpath = "//input[@id='tnc_box']")
    WebElement termsCheckbox;

    @FindBy(xpath = "//input[@id='Submit']")
    WebElement registerButton;  

    @FindBy(xpath = "//span[@class='reg_error']")
    WebElement errorMessage;

    @FindBy(xpath = "//div[contains(text(),'An email verification code has been sent')]")
    WebElement verificationMessage;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public RegistrationPage enterUsername(String username) {
        usernameField.sendKeys(username);
        return this;
    }

    public RegistrationPage enterPassword(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    public RegistrationPage enterConfirmPassword(String confirmPassword) {
        confirmPasswordField.sendKeys(confirmPassword);
        return this;
    }

    public RegistrationPage enterFullName(String fullName) {
        fullNameTextField.sendKeys(fullName);
        return this;
    }

    public RegistrationPage enterEmail(String email) {
        emailField.sendKeys(email);
        return this;
    }

     public RegistrationPage enterCaptchaManually() {
        System.out.println("Enter the CAPTCHA displayed on the registration page:");
        Scanner scanner = new Scanner(System.in);
        String captcha = scanner.nextLine();
        captchaTextField.sendKeys(captcha);
        return this;
    }


    public RegistrationPage checkTermsAndConditions() {
        if (!termsCheckbox.isSelected()) {
            termsCheckbox.click();
        }
        return this;
    }

    public RegistrationPage clickRegister() {
        registerButton.click();
        return this;
    }

    public boolean isVerificationMessageDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOf(verificationMessage)).isDisplayed();
    }
}
