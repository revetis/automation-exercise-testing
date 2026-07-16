package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.ConfigReader;
import utilities.Driver;

import java.time.Duration;

public class SignUpPage {

    public SignUpPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    WebDriverWait explicitWait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(Long.parseLong(ConfigReader.getProperty("explicitWaitTimeout"))));

    // --- New User Signup form (login page) ---
    @FindBy(xpath = "//button[@data-qa='signup-button']")
    public WebElement signupButton;

    @FindBy(xpath = "//form[@action='/signup']")
    public WebElement signupForm;

    @FindBy(xpath = "//input[@data-qa='signup-name']")
    public WebElement signupName;

    @FindBy(xpath = "//input[@data-qa='signup-email']")
    public WebElement signupEmail;

    public WebElement getWarningElement(String warningText) {
        return explicitWait.until(ExpectedConditions.visibilityOf(Driver.getDriver().findElement(By.xpath("//p[.='" + warningText + "']"))));
    }

    // --- Enter Account Information page ---
    @FindBy(id = "id_gender1")
    public WebElement titleMr;

    @FindBy(id = "id_gender2")
    public WebElement titleMrs;

    @FindBy(id = "password")
    public WebElement password;

    @FindBy(id = "days")
    public WebElement dobDays;

    @FindBy(id = "months")
    public WebElement dobMonths;

    @FindBy(id = "years")
    public WebElement dobYears;

    @FindBy(id = "newsletter")
    public WebElement newsletterCheckbox;

    @FindBy(id = "optin")
    public WebElement optinCheckbox;

    @FindBy(id = "first_name")
    public WebElement firstName;

    @FindBy(id = "last_name")
    public WebElement lastName;

    @FindBy(id = "company")
    public WebElement company;

    @FindBy(id = "address1")
    public WebElement address1;

    @FindBy(id = "address2")
    public WebElement address2;

    @FindBy(id = "country")
    public WebElement country;

    @FindBy(id = "state")
    public WebElement state;

    @FindBy(id = "city")
    public WebElement city;

    @FindBy(id = "zipcode")
    public WebElement zipcode;

    @FindBy(id = "mobile_number")
    public WebElement mobileNumber;

    @FindBy(xpath = "//button[@data-qa='create-account']")
    public WebElement createAccountButton;

    @FindBy(xpath = "//b[contains(text(),'Account Created!')]")
    public WebElement accountCreatedTitle;

    public WebElement getPageTitleElement(String titleText) {
        return Driver.getDriver().findElement(By.xpath("//h2[contains(., '" + titleText + "')]"));
    }
}