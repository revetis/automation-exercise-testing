Feature: Positive and Negative Testing for Sign-In Functionality

  Background:
    Given User on the homepage.

  Scenario: Positive Sign In Test
    And User enters the valid email into the email input field.
    And User enters the valid password into the password input field.
    And User clicks on the "Login" button.
    Then User confirms that they are successfully logged in.