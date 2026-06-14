package stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pages.SignInPage;
import utilities.Driver;

public class US01SignInStepDefinition {

    WebDriver driver = Driver.getDriver();
    SignInPage signInPage = new SignInPage();

    @Given("User on the homepage.")
    public void user_on_the_homepage() {}

    @Given("User clicks the {string} button in the header.")
    public void user_clicks_the_button_in_the_header(String string) {}

    @Given("User enters the valid email into the email input field.")
    public void user_enters_the_valid_email_into_the_email_input_field() {}

    @Given("User enters the valid password into the password input field.")
    public void user_enters_the_valid_password_into_the_password_input_field() {}

    @Given("User enters the {string} into the email input field.")
    public void user_enters_the_into_the_email_input_field(String string) {}

    @Given("User enters the {string} into the password input field.")
    public void user_enters_the_into_the_password_input_field(String string) {}

    @Given("User clicks on the {string} button.")
    public void user_clicks_on_the_button(String string) {}

    @Then("User verifies that they are successfully logged in.")
    public void user_verifies_that_they_are_successfully_logged_in() {}

    @Then("User verifies that the login process is failed.")
    public void user_verifies_that_the_login_process_is_failed() {}
}
