@login @regression
Feature: Web driver University Login Page

  Background:
    Given I access the web driver university login page

  Scenario Outline: Validate Successful and Unsuccessful login
    When I enter a username <username>
    And I enter a password <password>
    And I click on the login button
    Then I should be presented with the following login validation message <loginValidationMessage>
    Examples:
      | username  | password     | loginValidationMessage |
      | chen      | Wow          | validation failed      |
      | webdriver | webdriver123 | validation succeeded   |
