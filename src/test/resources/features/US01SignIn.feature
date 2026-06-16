@wip
Feature: Positive and Negative Testing for Sign-In Functionality

  Background:
    Given User on the homepage.
    And User clicks the "Signup / Login" button in the header.

  Scenario: Positive Sign In Test
    And User enters the valid email into the email input field.
    And User enters the valid password into the password input field.
    And User clicks on the "Login" button.
    Then User verifies that they are successfully logged in.

  Scenario Outline: Negative Sign In Test
    And User enters the "<email>" into the email input field.
    And User enters the "<password>" into the password input field.
    And User clicks on the login button.
    Then User verifies that the login process is failed.

    Examples:
      | email               | password |
      | samttre55@gmailcom  | Abc1234! |
      | samttre55gmail.com  | Abc1234! |
      | @gmail.com          | Abc1234! |
      | samttre55@.com      | Abc1234! |
      | samttre55@gmail.com | 1        |
      | samttre55@gmail.com | abcdefgh |
      | samttre55@gmail.com | 12345678 |
      | samttre55@gmail.com | ABCDEFGH |
      | samttre55@gmail.com | !@#$%^&* |
      | samttre55@gmail.com |          |
      |                     | Abc1234! |
