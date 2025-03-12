package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.Scanner;
import base.ProjectSpecificationMethods;
import pages.HomePage;
import pages.RegistrationPage;
import pages.EmailVerificationPage;

public class TC001_RegistrationTest extends ProjectSpecificationMethods {

    @BeforeTest
    public void setup() {
        sheetname = "RegistrationTest";
        testName = "RegistrationTest";
        testDescription = "Testing the Registration functionality with manual email verification";
        testAuthor = "Sathya";
        testCategory = "Functional Testing";
    }

    @Test(dataProvider = "excelRead")
    public void testUserRegistrationAndEmailVerification(String username, String password, String fullName, String email) {
        HomePage homePage = new HomePage(driver);

        // Navigate to registration page
        RegistrationPage registrationPage = homePage.clickRegisterLink();

        // Fill in registration form
        registrationPage.enterUsername(username)
                .enterPassword(password)
                .enterConfirmPassword(password)
                .enterFullName(fullName)
                .enterEmail(email)
                .enterCaptchaManually()  // Captcha must be entered manually
                .checkTermsAndConditions()
                .clickRegister();

        // Ask the user to manually fetch the verification link from Mailinator
        Scanner scanner = new Scanner(System.in);
        System.out.println("Go to https://www.mailinator.com, check the inbox '" + email + "', and retrieve the verification link.");
        System.out.print("Enter the verification link here: ");
        String emailLink = scanner.nextLine();

        // Ensure the user has entered a link
        Assert.assertNotNull(emailLink, "Verification email was not received or link was not provided!");
        Assert.assertFalse(emailLink.trim().isEmpty(), "Verification link cannot be empty!");

        // Open the verification link in browser
        driver.get(emailLink);

        // Verify success message
        EmailVerificationPage emailVerificationPage = new EmailVerificationPage(driver);
        Assert.assertTrue(emailVerificationPage.isEmailVerificationSuccessful(), "Email verification failed!");

        // Click login link after verification
        emailVerificationPage.clickLoginLink();
    }
}
