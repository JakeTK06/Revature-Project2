Feature: Homepage Functionality
  Scenario: Checking if the planet table is displaying correct data happy path
    Given the user is on the homepage
    And the celestial objects table has valid data

  Scenario: OutLine: Checking if the planet table is displaying correct data sad path
    Given the user is on the homepage
    And the celestial objects table has invalid data

