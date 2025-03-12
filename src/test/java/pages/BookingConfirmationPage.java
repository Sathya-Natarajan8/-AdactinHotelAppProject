package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import base.ProjectSpecificationMethods;

public class BookingConfirmationPage extends ProjectSpecificationMethods {
    WebDriver driver;

    @FindBy(xpath = "//td[contains(text(),'Booking Confirmation')]")
    WebElement bookingConfirmationMsg;

    @FindBy(id = "order_no")
    WebElement orderNumber;

    @FindBy(id = "logout")
    WebElement logoutButton;

    public BookingConfirmationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isBookingConfirmed() {
        return bookingConfirmationMsg.isDisplayed();
    }

    public String getOrderNumber() {
        return orderNumber.getAttribute("value");
    }

    public void logout() {
        logoutButton.click();
    }
}

