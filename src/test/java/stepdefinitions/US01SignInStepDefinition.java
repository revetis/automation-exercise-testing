package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.HeaderPage;
import pages.HomePage;
import pages.SignInPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.Logger4j;

import java.time.Duration;

public class US01SignInStepDefinition {

    WebDriver driver = Driver.getDriver();
    SignInPage signInPage = new SignInPage();
    HeaderPage headerPage = new HeaderPage();
    HomePage homePage = new HomePage();
    WebDriverWait explicitWait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(2));

    @Given("User on the homepage.")
    public void user_on_the_homepage() {
        Logger4j.info("Verifying that we are on the homepage");
        boolean isWeAtHomePage = explicitWait.until(ExpectedConditions.visibilityOf(homePage.categoryText)).isDisplayed();

        Assert.assertTrue(isWeAtHomePage, "We are not at homepage! ERROR");

        Logger4j.info("We are on the homepage");
    }

    @Given("User clicks the {string} button in the header.")
    public void user_clicks_the_button_in_the_header(String buttonName) {
        Logger4j.info("Clicking the " + buttonName + " button in the header.");

        explicitWait.until(ExpectedConditions.elementToBeClickable(headerPage.getHeaderButton(buttonName))).click();

        Logger4j.info("Clicked the " + buttonName + " button in the header.");
    }

    @Given("User enters the valid email into the email input field.")
    public void user_enters_the_valid_email_into_the_email_input_field() {
        Logger4j.info("Clicking on the email input field to focus");

        explicitWait.until(ExpectedConditions.visibilityOf(signInPage.loginEmailInput)).click();

        Logger4j.info("Clicked the email input field.");


        Logger4j.info("Entering valid email into the email input field.");

        explicitWait.until(ExpectedConditions.visibilityOf(signInPage.loginEmailInput)).sendKeys(ConfigReader.getProperty("email"));

        Logger4j.info("Entered valid email into the email input field.");


    }

    @Given("User enters the valid password into the password input field.")
    public void user_enters_the_valid_password_into_the_password_input_field() {
        Logger4j.info("Clicking on the password input field to focus");

        explicitWait.until(ExpectedConditions.visibilityOf(signInPage.loginPasswordInput)).click();

        Logger4j.info("Clicked the password input field.");


        Logger4j.info("Entering valid password into the password input field.");

        explicitWait.until(ExpectedConditions.visibilityOf(signInPage.loginPasswordInput)).sendKeys(ConfigReader.getProperty("password"));

        Logger4j.info("Entered valid password into the password input field.");
    }

    @Given("User enters the {string} into the email input field.")
    public void user_enters_the_into_the_email_input_field(String email) {
        Logger4j.info("Clicking on the email input field to focus");

        explicitWait.until(ExpectedConditions.visibilityOf(signInPage.loginEmailInput)).click();

        Logger4j.info("Clicked the email input field.");


        Logger4j.info("Entering the email into the email input field.");

        explicitWait.until(ExpectedConditions.visibilityOf(signInPage.loginEmailInput)).sendKeys(email);

        Logger4j.info("Entered the email into the email input field.");

    }

    @Given("User enters the {string} into the password input field.")
    public void user_enters_the_into_the_password_input_field(String password) {
        Logger4j.info("Clicking on the password input field to focus");

        explicitWait.until(ExpectedConditions.visibilityOf(signInPage.loginPasswordInput)).click();

        Logger4j.info("Clicked the password input field.");


        Logger4j.info("Entering the password into the password input field.");

        explicitWait.until(ExpectedConditions.visibilityOf(signInPage.loginPasswordInput)).sendKeys(password);

        Logger4j.info("Entered the password into the password input field.");
    }

    @Given("User clicks on the login button.")
    public void user_clicks_on_the_button() {
        Logger4j.info("Clicking the login button.");

        explicitWait.until(ExpectedConditions.elementToBeClickable(signInPage.loginButton)).click();

        Logger4j.info("Clicked the login button.");
    }

    @Then("User verifies that they are successfully logged in.")
    public void user_verifies_that_they_are_successfully_logged_in() {
        Logger4j.info("Verifying that we are successfully logged in.");

        boolean isLoggedIn = explicitWait.until(ExpectedConditions.visibilityOf(headerPage.getHeaderButton("Logout"))).isDisplayed();

        Assert.assertTrue(isLoggedIn, "Login process is failed! ERROR");

        Logger4j.info("We are successfully logged in.");
    }

    @Then("User verifies that the login process is failed.")
    public void user_verifies_that_the_login_process_is_failed() {
        Logger4j.info("Verifying that we are not successfully logged in.");

        boolean isLoggedIn = explicitWait.until(ExpectedConditions.invisibilityOf(headerPage.getHeaderButton("Logout")));

        Assert.assertTrue(isLoggedIn, "Login process is not failed! ERROR");

        Logger4j.info("We are not successfully logged in.");
    }
}
