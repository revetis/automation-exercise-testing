package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.ContactUsPage;
import pages.HomePage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.Logger4j;
import utilities.ReusableMethods;

public class US03ContactUsFormStepDefinition {
    WebDriver driver = Driver.getDriver();
    ContactUsPage contactUsPage = new ContactUsPage();
    HomePage homePage = new HomePage();


    @Given("Verify that home page is visible successfully")
    public void verify_that_home_page_is_visible_successfully() {
        Logger4j.info("Verifying that home page is successfully loaded.");
        Assert.assertTrue(ReusableMethods.waitOfVisibility(homePage.categoryText).isDisplayed(), "Home Page failed to load! ERROR");
    }

    @Then("Verify {string} text is visible")
    public void verify_is_visible(String text) {
        Logger4j.info("Verifying that '" + text + "' header text is visible.");
        Assert.assertTrue(ReusableMethods.waitOfVisibility(contactUsPage.getHeaderText(text)).isDisplayed(), "'" + text + "' text is not visible! ERROR");
    }

    @Then("Enter name, email, subject and message")
    public void enter_name_email_subject_and_message() {
        Logger4j.info("Disabling form validation for the Contact Us Form.");
        ReusableMethods.disableFormValidation(contactUsPage.contactForm);

        Logger4j.info("Entering the value into the name input.");
        ReusableMethods.sendKeysToElement(contactUsPage.nameInput, ConfigReader.getProperty("name"));

        Logger4j.info("Entering the value into the email input.");
        ReusableMethods.sendKeysToElement(contactUsPage.emailInput, ConfigReader.getProperty("email"));

        Logger4j.info("Entering the value into the subject input.");
        ReusableMethods.sendKeysToElement(contactUsPage.subjectInput, ConfigReader.getProperty("subject"));

        Logger4j.info("Entering the value into the textarea input.");
        ReusableMethods.sendKeysToElement(contactUsPage.textArea, ConfigReader.getProperty("message"));
    }

    @Then("Upload file")
    public void upload_file() {
        Logger4j.info("The file is being uploaded to the file upload area.");
        ReusableMethods.sendKeysToElement(contactUsPage.uploadFile, ConfigReader.getProperty("uploadFilePath"));
    }

    @Then("Click Submit button")
    public void click_submit_button() {
        Logger4j.info("Clicking the submit button.");
        ReusableMethods.clickElement(contactUsPage.submitButton);
    }

    @Then("Click OK button")
    public void click_ok_button() {
        Logger4j.info("Accepting the JavaScript alert.");
        driver.switchTo().alert().accept();
    }

    @Then("Verify success message {string} is visible")
    public void verify_success_message_is_visible(String text) {
        Logger4j.info("Verifying that success message '" + text + "' is visible.");
        Assert.assertTrue(ReusableMethods.waitOfVisibility(contactUsPage.getSuccessMessage(text)).isDisplayed(), "Success Message '" + text + "' is not visible! ERROR");
    }

    @Then("Click home button and verify that landed to home page successfully")
    public void click_button_and_verify_that_landed_to_home_page_successfully() {
        Logger4j.info("Clicking the home button and verifying successful navigation to the home page.");
        ReusableMethods.clickElement(contactUsPage.homeButton);

        verify_that_home_page_is_visible_successfully();
    }
}