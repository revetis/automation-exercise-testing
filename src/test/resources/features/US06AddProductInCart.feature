@wip
Feature: Add Product In Cart

  Scenario: Add Product In Cart Positive Scenario
    Given User goes to the "http://automationexercise.com" address on the browser.
    Then Verify that home page is visible successfully
    When  User clicks the "Products" button in the header.
    And Hover over first product.
    And Click add to cart button for the product at 0 index.
    And Click continue shopping button.
    And Hover over second product.
    And Click add to cart button for the product at 1 index.
    And Click view cart button.
    Then Verify both products are added to Cart.
    And Verify their prices, quantity and total price.
