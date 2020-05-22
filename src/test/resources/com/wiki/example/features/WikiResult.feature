Feature: Wiki Searching
  As a web surfer,
  I want to search Wiki,
  So that I can collect geo codes.

  @chrome
  Scenario: Geo codes Wiki search, collects links - chrome
    Given I open home wiki page
    When I search for today date
    And I wait for search result page loaded
    And I collect links on Search Results Page
    And I look for continents on Search Results Page
    And I look for countries on Search Results Page
    And I look for cities on Search Results Page
    And I click on tomorrow date on Calendar on Search Results Page
    And I collect links on Search Results Page
    And I look for continents on Search Results Page
    And I look for countries on Search Results Page
    And I look for cities on Search Results Page
    Then I compare found continents on Search Results Page
    Then I compare found countries on Search Results Page
    Then I compare found cities on Search Results Page

  @chrome
  Scenario: Geo codes Wiki search - chrome
    Given I open home wiki page
    When I search for today date
    And I wait for search result page loaded
    Then I check header name is correct on Search Results Page
