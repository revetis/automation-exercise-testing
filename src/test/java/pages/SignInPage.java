package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class SignInPage {

    public SignInPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    // --- SignIn Form (login page) ---

    @FindBy(xpath = "//input[@data-qa='login-email']")
    public WebElement loginEmailInput;

    @FindBy(xpath = "//input[@data-qa='login-password']")
    public WebElement loginPasswordInput;

    @FindBy(xpath = "//button[@data-qa='login-button']")
    public WebElement loginButton;

    @FindBy(xpath = "//form[@action='/login']")
    public WebElement signinForm;

    @FindBy(xpath = "//p[contains(text(),'Your email or password is incorrect!')]")
    public WebElement signInWarningText;



}
