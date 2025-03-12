package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethods;
import pages.HomePage;
import pages.LoginPage;



public class TC002_LoginPageTest extends ProjectSpecificationMethods {

    @BeforeTest
    public void setup() {
        sheetname = "LoginTest";
        testName = "Login Test";
        testDescription = "Testing the login functionality with valid and invalid credentials";
        testAuthor = "Sathya";
        testCategory = "Functional Testing";
    }

    @Test(dataProvider = "excelRead")
    public void testUserLogin(String username, String password, String expectedResult) {
        HomePage homePage = new HomePage(driver);

        // Navigate to login page
        LoginPage loginPage = new LoginPage(driver);

        // Perform login
        loginPage.enterUsername(username)
                 .enterPassword(password)
                 .clickLogin();

     // Verify login result
        if (expectedResult.equalsIgnoreCase("Success")) {
            Assert.assertTrue(loginPage.isLoginSuccessful(), "Login failed for valid credentials!");
        } else {
            Assert.assertTrue(loginPage.isLoginFailed(), "Login should have failed but passed!");
            System.out.println("Error Message Displayed: " + loginPage.getErrorMessage());
        }
    }
}

