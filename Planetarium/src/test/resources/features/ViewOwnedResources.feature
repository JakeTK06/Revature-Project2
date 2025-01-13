Feature: View users planets and moons
  As a user I want to see my planets and moons added to the Planetarium so I can track my findings

  Scenario: Login should allow for viewing owned resources
    Given the user is logged in
    And the user is on the home page
    Then the user should see their planets and moons

  Scenario: Users not logged in should not be able to view the home page and its resources
    Given the user is not logged in
    When the user tries to directly access the home page
    Then the user should be denied access

  Scenario: Users should not be able to see other user's moons and planets
    Given the user has registered for the first time and logged in
    Then there should be no visible celestial bodies

  Scenario: Users who log out should not still be able to view their celestial bodies
    Given the user is logged in
    When the user presses the logout button
    Then the user should be redirected to the login page