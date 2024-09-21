@contact_us
Feature: Web driver University Contact Us Page

  Background:
    Given I access the web driver university contact us page

  Scenario Outline:
    When I enter a specific name <firstname>
    And I enter a specific lastname <lastname>
    And I enter a specific email <email>
    And I enter a comment <comment>
    And I click on button submit
    Then I should be presented with contact us submission successful message
    Examples:
      | firstname | lastname | email            | comment |
      | mar       | chen     | mar@dummy.com    | 20      |
      | wow       | here     | towhee@dummy.com | 5       |