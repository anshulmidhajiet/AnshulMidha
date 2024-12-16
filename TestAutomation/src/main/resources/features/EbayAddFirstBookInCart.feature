#Author: anshulmidhajiet@gmail.com
Feature: Add item to cart on eBay

  Scenario: Verify item can be added to cart
    Given I open the browser and navigate to "https://www.ebay.com"
    When I search for "book"
    And I Click on the first book in the list
    And I add the item to the cart
    Then the cart should display "1" item