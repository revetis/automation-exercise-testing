@wip
Feature: Search Product

    Scenario: Search for the product and verify that the searched product is displayed
      Given User goes to the "https://automationexercise.com/" address on the browser.
      Then Verify that home page is visible successfully
      And User clicks the "Products" button in the header.
      Then Verify that user is navigated to All Products page successfully.
      And Enter "s" in search input.
      And Click search button.
      Then Verify the "s" product and all the related products is visible.
