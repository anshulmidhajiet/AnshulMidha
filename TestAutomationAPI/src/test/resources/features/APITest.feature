#Author: anshulmidhajiet@gmail.com
Feature: Validate CoinDesk API response

  Scenario: Verify BPIs and GBP description
    Given I send a GET request to "https://api.coindesk.com/v1/bpi/currentprice.json"
    Then the response status code should be 200
    And the response should contain the BPIs "USD", "GBP", and "EUR"
    And the "GBP" description should be "British Pound Sterling"