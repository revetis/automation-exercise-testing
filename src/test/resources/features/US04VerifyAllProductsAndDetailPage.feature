@wip

Feature: Verify All Products And Detail Page

  Background: 
    Given User goes to the "https://automationexercise.com/" address on the browser.
    Then Verify that home page is visible successfully
  
  Scenario: Verify Products And Detail Page Elements
    When User clicks the "Products" button in the header.
    Then Verify that user is navigated to All Products page successfully.
    And Verify that the products list is visible.
    When Click on View Product button of first product.
    Then Verify that user is landed to product detail page.
    And Verify that product name is visible.
    And Verify that category is visible.
    And Verify that price is visible.
    And Verify that availability is visible.
    And Verify that condition is visible.
    And Verify that brand is visible.