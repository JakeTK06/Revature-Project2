@US5
Feature: User Element Deletion Feature
  @MR5 @HappyPathTesting
  Scenario: Users logged in who provide valid moon inputs should be able to delete existing moons from the planetarium.
    Given the user is logged in on the home page
    And the user has moon toggled from dropdown
    When the user provides a moon name to delete "Luna"
    And the user clicks on the delete button
    Then the existing moon should be deleted

  @PR5 @HappyPathTesting
  Scenario: Users logged in who provide valid planet inputs should be able to delete existing planets and its moons
            from the planetarium.
    Given the user is logged in on the home page
    And the user has planet toggled from dropdown
    When the user provides a planet name to delete "Earth"
    And the user clicks on the delete button
    Then the existing planet and its moons should be deleted

  @PR5 @SadPathTesting
  Scenario: Users logged in who provide invalid planet inputs should not be able to delete existing planets from the
            planetarium.
    Given the user is logged in on the home page
    And the user has planet toggled from dropdown
    When the user provides a planet name to delete "George"
    And the user clicks on the delete button
    Then the user should get a browser alert saying "Invalid planet name"

  @MR5 @SadPathTesting
  Scenario: Users logged in who provide invalid moon inputs should not be able to delete existing moons from the
            planetarium.
    Given the user is logged in on the home page
    And the user has moon toggled from dropdown
    When the user provides a moon name to delete "George"
    And the user clicks on the delete button
    Then the user should get a browser alert saying "Invalid moon name"
