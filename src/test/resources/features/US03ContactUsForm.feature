Feature: Contact US Form

  Background:
    Given User goes to the "https://automationexercise.com/" address on the browser.

  Scenario: Contact US Form Verification Positive
    And Verify that home page is visible successfully
    And User clicks the "Contact us" button in the header.
    Then Verify "Get In Touch" text is visible
    And Enter name, email, subject and message
    And Upload file
    And Click Submit button
    And Click OK button
    Then Verify success message 'Success! Your details have been submitted successfully.' is visible
    And Click home button and verify that landed to home page successfully
