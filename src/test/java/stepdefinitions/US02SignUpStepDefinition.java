package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.HeaderPage;
import pages.SignUpPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.Logger4j;
import utilities.ReusableMethods;

public class US02SignUpStepDefinition {
    WebDriver driver = Driver.getDriver();
    SignUpPage signUpPage = new SignUpPage();
    HeaderPage headerPage = new HeaderPage();

    // ---------------- SIGNUP STEP (name/email) ----------------

    @Given("User enters a valid name into the name input field.")
    public void user_enters_a_valid_name_into_the_name_input_field() {
        Logger4j.info("Disabling native form validation on the signup form.");
        ReusableMethods.disableFormValidation(signUpPage.signupForm);

        Logger4j.info("Entering valid name into the name input field.");
        ReusableMethods.sendKeysToElement(signUpPage.signupName, ConfigReader.getProperty("name"));
        Logger4j.info("Entered valid name into the name input field.");
    }

    @Given("User enters a valid, unused email into the sign up email input field.")
    public void user_enters_a_valid_unused_email_into_the_sign_up_email_input_field() {
        String uniqueEmail = ReusableMethods.generateUniqueEmail("tester", "testmail.com");
        Logger4j.info("Generated unique email: " + uniqueEmail);

        Logger4j.info("Entering unique email into the sign up email input field.");
        ReusableMethods.sendKeysToElement(signUpPage.signupEmail, uniqueEmail);
        Logger4j.info("Entered unique email into the sign up email input field.");
    }

    @Given("User enters an already registered email into the sign up email input field.")
    public void user_enters_an_already_registered_email_into_the_sign_up_email_input_field() {
        Logger4j.info("Entering already registered email into the sign up email input field.");
        ReusableMethods.sendKeysToElement(signUpPage.signupEmail, ConfigReader.getProperty("email"));
        Logger4j.info("Entered already registered email into the sign up email input field.");
    }

    @Given("User enters the {string} into the name input field.")
    public void user_enters_the_into_the_name_input_field(String name) {
        Logger4j.info("Entering '" + name + "' into the name input field.");
        ReusableMethods.sendKeysToElement(signUpPage.signupName, name);
        Logger4j.info("Entered '" + name + "' into the name input field.");
    }

    @Given("User enters the {string} into the sign up email input field.")
    public void user_enters_the_into_the_sign_up_email_input_field(String email) {
        Logger4j.info("Entering '" + email + "' into the sign up email input field.");
        ReusableMethods.sendKeysToElement(signUpPage.signupEmail, email);
        Logger4j.info("Entered '" + email + "' into the sign up email input field.");
    }

    @And("User clicks on the signup button.")
    public void user_clicks_on_the_signup_button() {
        Logger4j.info("Clicking the signup button.");
        ReusableMethods.clickElement(signUpPage.signupButton);
        Logger4j.info("Clicked the signup button.");
    }

    @Then("User verifies that the {string} warning message is displayed.")
    public void user_verifies_that_the_warning_message_is_displayed(String warningText) {
        Logger4j.info("Verifying that the warning message '" + warningText + "' is displayed.");
        Assert.assertTrue(ReusableMethods.waitOfVisibility(signUpPage.getWarningElement(warningText)).isDisplayed());
        Logger4j.info("The warning message '" + warningText + "' is displayed as expected.");
    }

    @Then("User verifies that the browser blocks the form submission due to invalid input.")
    public void user_verifies_that_the_browser_blocks_the_form_submission_due_to_invalid_input() {
        Logger4j.info("Verifying that the browser blocked the form submission.");
        headerPage = new HeaderPage();
        Assert.assertTrue(headerPage.getHeaderButton("Signup / Login").isDisplayed());

        Logger4j.info("Browser blocked the submission as expected.");
    }

    // ---------------- ENTER ACCOUNT INFORMATION STEP ----------------

    @Then("User verifies that they are on the {string} page.")
    public void user_verifies_that_they_are_on_the_page(String expectedPageTitle) {
        Logger4j.info("Verifying that the user is on the '" + expectedPageTitle + "' page.");
        boolean isVisible = ReusableMethods.waitOfVisibility(signUpPage.getPageTitleElement(expectedPageTitle)).isDisplayed();
        Assert.assertTrue(isVisible, "Expected to be on the '" + expectedPageTitle + "' page, but title was not found.");
        Logger4j.info("Confirmed the user is on the '" + expectedPageTitle + "' page.");
    }

    @Then("User selects the title.")
    public void user_selects_the_title() {
        Logger4j.info("Selecting the title (Mr).");
        ReusableMethods.clickElement(signUpPage.titleMr);
        Logger4j.info("Title selected.");
    }

    @Then("User enters a valid password into the password input field.")
    public void user_enters_a_valid_password_into_the_password_input_field() {
        Logger4j.info("Entering valid password into the password input field.");
        ReusableMethods.sendKeysToElement(signUpPage.password, ConfigReader.getProperty("password"));
        Logger4j.info("Entered valid password into the password input field.");
    }

    @Then("User selects a date of birth.")
    public void user_selects_a_date_of_birth() {
        Logger4j.info("Selecting date of birth.");
        ReusableMethods.selectByValue(signUpPage.dobDays, "15");
        ReusableMethods.selectByValue(signUpPage.dobMonths, "5");
        ReusableMethods.selectByValue(signUpPage.dobYears, "1995");
        Logger4j.info("Date of birth selected.");
    }

    @Then("User checks the newsletter checkbox.")
    public void user_checks_the_newsletter_checkbox() {
        Logger4j.info("Checking the newsletter checkbox.");
        ReusableMethods.checkIfNotSelected(signUpPage.newsletterCheckbox);
        Logger4j.info("Newsletter checkbox checked.");
    }

    @Then("User checks the special offers checkbox.")
    public void user_checks_the_special_offers_checkbox() {
        Logger4j.info("Checking the special offers checkbox.");
        ReusableMethods.checkIfNotSelected(signUpPage.optinCheckbox);
        Logger4j.info("Special offers checkbox checked.");
    }

    @Then("User enters the first name into the first name input field.")
    public void user_enters_the_first_name_into_the_first_name_input_field() {
        Logger4j.info("Entering first name into the first name input field.");
        ReusableMethods.sendKeysToElement(signUpPage.firstName, ConfigReader.getProperty("name"));
        Logger4j.info("Entered first name into the first name input field.");
    }

    @Then("User enters the last name into the last name input field.")
    public void user_enters_the_last_name_into_the_last_name_input_field() {
        Logger4j.info("Entering last name into the last name input field.");
        ReusableMethods.sendKeysToElement(signUpPage.lastName, ConfigReader.getProperty("lastName"));
        Logger4j.info("Entered last name into the last name input field.");
    }

    @Then("User enters the company name into the company input field.")
    public void user_enters_the_company_name_into_the_company_input_field() {
        Logger4j.info("Entering company name into the company input field.");
        ReusableMethods.sendKeysToElement(signUpPage.company, ConfigReader.getProperty("company"));
        Logger4j.info("Entered company name into the company input field.");
    }

    @Then("User enters the address into the address input field.")
    public void user_enters_the_address_into_the_address_input_field() {
        Logger4j.info("Entering address into the address input field.");
        ReusableMethods.sendKeysToElement(signUpPage.address1, ConfigReader.getProperty("address1"));
        Logger4j.info("Entered address into the address input field.");
    }

    @Then("User enters the second address into the address2 input field.")
    public void user_enters_the_second_address_into_the_address2_input_field() {
        Logger4j.info("Entering second address into the address2 input field.");
        ReusableMethods.sendKeysToElement(signUpPage.address2, ConfigReader.getProperty("address2"));
        Logger4j.info("Entered second address into the address2 input field.");
    }

    @Then("User selects the country.")
    public void user_selects_the_country() {
        Logger4j.info("Selecting the country.");
        ReusableMethods.selectByVisibleText(signUpPage.country, ConfigReader.getProperty("country"));
        Logger4j.info("Country selected.");
    }

    @Then("User enters the state into the state input field.")
    public void user_enters_the_state_into_the_state_input_field() {
        Logger4j.info("Entering state into the state input field.");
        ReusableMethods.sendKeysToElement(signUpPage.state, ConfigReader.getProperty("state"));
        Logger4j.info("Entered state into the state input field.");
    }

    @Then("User enters the city into the city input field.")
    public void user_enters_the_city_into_the_city_input_field() {
        Logger4j.info("Entering city into the city input field.");
        ReusableMethods.sendKeysToElement(signUpPage.city, ConfigReader.getProperty("city"));
        Logger4j.info("Entered city into the city input field.");
    }

    @Then("User enters the zipcode into the zipcode input field.")
    public void user_enters_the_zipcode_into_the_zipcode_input_field() {
        Logger4j.info("Entering zipcode into the zipcode input field.");
        ReusableMethods.sendKeysToElement(signUpPage.zipcode, ConfigReader.getProperty("zipcode"));
        Logger4j.info("Entered zipcode into the zipcode input field.");
    }

    @Then("User enters the mobile number into the mobile number input field.")
    public void user_enters_the_mobile_number_into_the_mobile_number_input_field() {
        Logger4j.info("Entering mobile number into the mobile number input field.");
        ReusableMethods.sendKeysToElement(signUpPage.mobileNumber, ConfigReader.getProperty("mobileNumber"));
        Logger4j.info("Entered mobile number into the mobile number input field.");
    }

    @Then("User clicks on the create account button.")
    public void user_clicks_on_the_create_account_button() {
        Logger4j.info("Disabling native form validation before submitting.");
        ReusableMethods.disableFormValidation(signUpPage.signupForm);

        Logger4j.info("Clicking the create account button.");
        ReusableMethods.clickElement(signUpPage.createAccountButton);
        Logger4j.info("Clicked the create account button.");
    }

    @Then("User verifies that the account is successfully created.")
    public void user_verifies_that_the_account_is_successfully_created() {
        Logger4j.info("Verifying that the account was successfully created.");
        signUpPage = new SignUpPage();
        Assert.assertTrue(ReusableMethods.waitOfVisibility(signUpPage.accountCreatedTitle).isDisplayed(),
                "Account creation failed!");
        Logger4j.info("Account successfully created.");
    }

    // ---------------- NEGATIVE: MISSING / INVALID FIELD ----------------

    private static final String[] ACCOUNT_TEXT_FIELDS = {
            "password", "first_name", "last_name", "company",
            "address1", "address2", "state", "city", "zipcode", "mobile_number"
    };

    @Then("User fills in all account information fields except {string}.")
    public void user_fills_in_all_account_information_fields_except(String excludedField) {
        Logger4j.info("Filling all account information fields except '" + excludedField + "'.");
        fillAllAccountFields(excludedField);
        Logger4j.info("Finished filling all account information fields except '" + excludedField + "'.");
    }

    @Then("User fills in all account information fields with valid data.")
    public void user_fills_in_all_account_information_fields_with_valid_data() {
        Logger4j.info("Filling all account information fields with valid data.");
        fillAllAccountFields(null);
        Logger4j.info("Finished filling all account information fields with valid data.");
    }

    @Then("User overrides the {string} input with {string}.")
    public void user_overrides_the_input_with(String field, String invalidValue) {
        Logger4j.info("Overriding the '" + field + "' input with '" + invalidValue + "'.");
        fillField(field, invalidValue);
        Logger4j.info("Overrode the '" + field + "' input with '" + invalidValue + "'.");
    }

    @Then("User verifies that the account is not created due to invalid {string}.")
    public void user_verifies_that_the_account_is_not_created_due_to_invalid(String field) {
        Logger4j.info("Verifying that the account was NOT created due to invalid " + field);

        String currentUrl = driver.getCurrentUrl();
        assert currentUrl != null;
        Assert.assertFalse(currentUrl.contains("account_created"),
                "Account was unexpectedly created despite invalid " + field + "! Current URL: " + currentUrl);

        Logger4j.info("Account creation correctly blocked due to invalid " + field);
    }

    /**
     * Fills every account information field with valid default data,
     * always selecting title/DOB/country and checking both checkboxes.
     * If excludedField is non-null, that single field is skipped
     * entirely (used to trigger a missing-required-field validation).
     *
     * @param excludedField field key to skip filling, or null to fill everything
     */
    private void fillAllAccountFields(String excludedField) {
        Logger4j.info("Selecting title before filling account fields.");
        ReusableMethods.clickElement(signUpPage.titleMr);

        for (String field : ACCOUNT_TEXT_FIELDS) {
            if (field.equals(excludedField)) {
                Logger4j.info("Skipping field: " + field);
                continue;
            }
            Logger4j.info("Filling field: " + field);
            fillField(field, defaultValueFor(field));
        }

        if (!"country".equals(excludedField)) {
            Logger4j.info("Selecting country.");
            ReusableMethods.selectByVisibleText(signUpPage.country, ConfigReader.getProperty("country"));
        } else {
            Logger4j.info("Skipping field: country");
        }

        Logger4j.info("Selecting date of birth.");
        ReusableMethods.selectByValue(signUpPage.dobDays, "15");
        ReusableMethods.selectByValue(signUpPage.dobMonths, "5");
        ReusableMethods.selectByValue(signUpPage.dobYears, "1995");

        Logger4j.info("Checking newsletter and special offers checkboxes.");
        ReusableMethods.checkIfNotSelected(signUpPage.newsletterCheckbox);
        ReusableMethods.checkIfNotSelected(signUpPage.optinCheckbox);
    }

    /**
     * Fills a single account information field, identified by its
     * data-qa style key, with the given value. Used both for normal
     * population and for overriding a single field with an invalid value.
     *
     * @param field the field key (e.g. "first_name", "zipcode")
     * @param value the value to type into that field
     */
    private void fillField(String field, String value) {
        switch (field) {
            case "password" -> ReusableMethods.clearAndSendKeys(signUpPage.password, value);
            case "first_name" -> ReusableMethods.clearAndSendKeys(signUpPage.firstName, value);
            case "last_name" -> ReusableMethods.clearAndSendKeys(signUpPage.lastName, value);
            case "company" -> ReusableMethods.clearAndSendKeys(signUpPage.company, value);
            case "address1" -> ReusableMethods.clearAndSendKeys(signUpPage.address1, value);
            case "address2" -> ReusableMethods.clearAndSendKeys(signUpPage.address2, value);
            case "state" -> ReusableMethods.clearAndSendKeys(signUpPage.state, value);
            case "city" -> ReusableMethods.clearAndSendKeys(signUpPage.city, value);
            case "zipcode" -> ReusableMethods.clearAndSendKeys(signUpPage.zipcode, value);
            case "mobile_number" -> ReusableMethods.clearAndSendKeys(signUpPage.mobileNumber, value);
            default -> throw new IllegalArgumentException("Unknown account field: " + field);
        }
    }

    /**
     * Returns the default valid value for a given account field,
     * sourced from the config file where available.
     *
     * @param field the field key
     * @return the default valid value for that field
     */
    private String defaultValueFor(String field) {
        return switch (field) {
            case "password" -> ConfigReader.getProperty("password");
            case "first_name" -> ConfigReader.getProperty("name");
            case "last_name" -> ConfigReader.getProperty("lastName");
            case "company" -> ConfigReader.getProperty("company");
            case "address1" -> ConfigReader.getProperty("address1");
            case "address2" -> ConfigReader.getProperty("address2");
            case "state" -> ConfigReader.getProperty("state");
            case "city" -> ConfigReader.getProperty("city");
            case "zipcode" -> ConfigReader.getProperty("zipcode");
            case "mobile_number" -> ConfigReader.getProperty("mobileNumber");
            default -> throw new IllegalArgumentException("Unknown account field: " + field);
        };
    }
}