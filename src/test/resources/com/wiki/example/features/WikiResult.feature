Feature: Wiki Searching
  As a web surfer,
  I want to search Wiki,
  So that I can collect geo codes.

  @chrome
  Scenario: Geo codes Wiki search - chrome
    Given I open home wiki page
    When I search for today date
    And I wait for search result page loaded
    And I collect links from search result page
    And I look for continents on page
    And I look for countries on page
    And I look for cities on page
    And I click on tomorrow date on Calendar
    And I collect links from search result page
    And I look for continents on page
    And I look for countries on page
    And I look for cities on page
    Then I compare found continents
    Then I compare found countries
    Then I compare found cities

  @firefox
  Scenario: Geo codes Wiki search - firefox
    Given I open home wiki page
    When I search for today date
    And I wait for search result page loaded
    And I collect links from search result page
    And I look for continents on page
    And I look for countries on page
    And I look for cities on page
    And I click on tomorrow date on Calendar
    And I collect links from search result page
    And I look for continents on page
    And I look for countries on page
    And I look for cities on page
    Then I compare found continents
    Then I compare found countries
    Then I compare found cities
