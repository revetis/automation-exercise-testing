@wip
Feature: Positive and Negative Testing for Sign-Up Functionality

  Background:
    Given User goes to the "https://automationexercise.com/" address on the browser.
    And User clicks the "Signup / Login" button in the header.

  # --- Positive ---
  Scenario: Positive Sign Up Test - Full Account Creation
    And User enters a valid name into the name input field.
    And User enters a valid, unused email into the sign up email input field.
    And User clicks on the signup button.
    Then User verifies that they are on the "Enter Account Information" page.
    And User selects the title.
    And User enters a valid password into the password input field.
    And User selects a date of birth.
    And User checks the newsletter checkbox.
    And User checks the special offers checkbox.
    And User enters the first name into the first name input field.
    And User enters the last name into the last name input field.
    And User enters the company name into the company input field.
    And User enters the address into the address input field.
    And User enters the second address into the address2 input field.
    And User selects the country.
    And User enters the state into the state input field.
    And User enters the city into the city input field.
    And User enters the zipcode into the zipcode input field.
    And User enters the mobile number into the mobile number input field.
    And User clicks on the create account button.
    Then User verifies that the account is successfully created.
    And User closes the browser.

  # --- Negative: Signup step (name/email) ---
  Scenario: Negative Sign Up Test - Existing Email
    And User enters a valid name into the name input field.
    And User enters an already registered email into the sign up email input field.
    And User clicks on the signup button.
    Then User verifies that the "Email Address already exist!" warning message is displayed.
    And User closes the browser.

  Scenario Outline: Negative Sign Up Test - Client-Side Validation on Name/Email
    And User enters the "<name>" into the name input field.
    And User enters the "<email>" into the sign up email input field.
    And User clicks on the signup button.
    Then User verifies that the browser blocks the form submission due to invalid input.
    And User closes the browser.

    Examples:
      | name  | email               |
      | Sam   | samttre55gmailcom   |
      | Sam   | @gmail.com          |
      |       | samttre55@gmail.com |
      | Sam   |                     |

  # --- Negative: Enter Account Information step (required fields) ---
  Scenario Outline: Negative Account Creation Test - Missing Required Field
    And User enters a valid name into the name input field.
    And User enters a valid, unused email into the sign up email input field.
    And User clicks on the signup button.
    Then User verifies that they are on the "Enter Account Information" page.
    And User fills in all account information fields except "<missing_field>".
    And User clicks on the create account button.
    Then User verifies that the browser blocks the form submission due to invalid input.
    And User closes the browser.

    Examples:
      | missing_field   |
      | password        |
      | first_name      |
      | last_name       |
      | address1        |
      | state           |
      | city            |
      | zipcode         |
      | mobile_number    |

  Scenario Outline: Negative Account Creation Test - Invalid Field Format
    And User enters a valid name into the name input field.
    And User enters a valid, unused email into the sign up email input field.
    And User clicks on the signup button.
    Then User verifies that they are on the "Enter Account Information" page.
    And User fills in all account information fields with valid data.
    And User overrides the "<field>" input with "<invalid_value>".
    And User clicks on the create account button.
    Then User verifies that the account is not created due to invalid "<field>".
    And User closes the browser.

    Examples:
      | field         | invalid_value |
      | mobile_number | abcdefg       |
      | zipcode       | !!!!!         |