package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.HeaderPage;
import pages.HomePage;
import pages.SignInPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.Logger4j;
import utilities.ReusableMethods;

public class US01SignInStepDefinition {

    WebDriver driver = Driver.getDriver();
    SignInPage signInPage = new SignInPage();
    HeaderPage headerPage = new HeaderPage();
    HomePage homePage = new HomePage();

    // ---------------- NAVIGATION ----------------

    @Given("User goes to the {string} address on the browser.")
    public void user_goes_to_the_address_on_the_browser(String url) {
        Logger4j.info("Navigating to '" + url + "' in the browser.");
        driver.get(url);
    }

    @Given("User clicks the {string} button in the header.")
    public void user_clicks_the_button_in_the_header(String buttonName) {
        Logger4j.info("Clicking the " + buttonName + " button in the header.");
        headerPage = new HeaderPage();
        try {
            ReusableMethods.clickElement(headerPage.getHeaderButton(buttonName));
        } catch (NoSuchElementException e) {
            ReusableMethods.clickElement(headerPage.getHeaderButton("Logout"));
            ReusableMethods.clickElement(headerPage.getHeaderButton(buttonName));
        }
        Logger4j.info("Clicked the " + buttonName + " button in the header.");
    }

    // ---------------- LOGIN FORM INPUTS ----------------

    @Given("User enters the valid email into the email input field.")
    public void user_enters_the_valid_email_into_the_email_input_field() {
        Logger4j.info("Entering valid email into the email input field.");
        ReusableMethods.sendKeysToElement(signInPage.loginEmailInput, ConfigReader.getProperty("email"));
        Logger4j.info("Entered valid email into the email input field.");
    }

    @Given("User enters the {string} into the email input field.")
    public void user_enters_the_into_the_email_input_field(String email) {
        Logger4j.info("Entering the email into the email input field.");
        ReusableMethods.sendKeysToElement(signInPage.loginEmailInput, email);
        Logger4j.info("Entered the email into the email input field.");
    }

    @Given("User enters the valid password into the password input field.")
    public void user_enters_the_valid_password_into_the_password_input_field() {
        Logger4j.info("Entering valid password into the password input field.");
        ReusableMethods.sendKeysToElement(signInPage.loginPasswordInput, ConfigReader.getProperty("password"));
        Logger4j.info("Entered valid password into the password input field.");
    }

    @Given("User enters the {string} into the password input field.")
    public void user_enters_the_into_the_password_input_field(String password) {
        Logger4j.info("Entering the password into the password input field.");
        ReusableMethods.sendKeysToElement(signInPage.loginPasswordInput, password);
        Logger4j.info("Entered the password into the password input field.");
    }

    // ---------------- LOGIN ACTION ----------------

    @Given("User clicks on the login button.")
    public void user_clicks_on_the_button() {
        Logger4j.info("Clicking the login button.");

        Logger4j.info("We are disabling browser validation to avoid potential conflicts.");
        ReusableMethods.disableFormValidation(signInPage.signinForm);

        ReusableMethods.waitOfClickable(signInPage.loginButton);
        ReusableMethods.clickElement(signInPage.loginButton);

        Logger4j.info("Clicked the login button.");
    }

    @And("User clicks the logout button in the header for logout.")
    public void user_clicks_the_logout_button_in_the_header_for_logout() {
        Logger4j.info("Clicking the logout button in the header for logout.");
        ReusableMethods.clickElement(headerPage.getHeaderButton("Logout"));
        Logger4j.info("Logout process completed.");
    }

    // ---------------- ASSERTIONS ----------------

    @Then("User verifies that they are successfully logged in.")
    public void user_verifies_that_they_are_successfully_logged_in() {
        Logger4j.info("Verifying that we are successfully logged in.");
        Assert.assertTrue(ReusableMethods.waitOfVisibility(headerPage.getHeaderButton("Logout")).isDisplayed(),
                "Login process is failed! ERROR");
        Logger4j.info("We are successfully logged in.");
    }

    @Then("User verifies that the warning message is displayed.")
    public void user_verifies_that_the_warning_message_is_displayed() {
        Logger4j.info("Verifying that the warning message is displayed.");
        boolean isVisible = ReusableMethods.waitOfVisibility(signInPage.signInWarningText).isDisplayed();
        Assert.assertTrue(isVisible, "Warning message is not displayed!");
        Logger4j.info("The warning message is displayed as expected.");
    }

    // ---------------- TEARDOWN ----------------

    @And("User closes the browser.")
    public void user_closes_the_browser() {
        Logger4j.info("The test are finished. Driver closing...");
        Driver.quitDriver();
    }
}