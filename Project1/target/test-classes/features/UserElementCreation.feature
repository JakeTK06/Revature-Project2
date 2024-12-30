@US4
Feature: User Element Creation Feature
  @MR1 @MR2 @MR3 @MR4 @MR6 @HappyPathTesting
  Scenario: Users logged in who provide valid moon inputs should be able to add new moons to the planetarium.
    Given the user is logged in on the home page
    And the user has moon toggled from dropdown
    When the user provides a name for the moon "M 0 0 N V2"
    And the user provides the id of the planet it orbits "1"
    And the user presses the submit button_without_image
    Then the new moon should be added to the database

  @PR1 @PR2 @PR3 @PR4 @PR5 @HappyPathTesting
  Scenario: Users logged in who provide valid planet inputs should be able to add new planets to the planetarium.
    Given the user is logged in on the home page
    And the user has planet toggled from dropdown
    When the user provides a name for the planet "V _ 0 r M 1 r"
    And the user presses the submit button_without_image
    Then the new planet should be added to the database

  @PR6 @SadPathTesting
  Scenario: Users logged in who provide invalid planet image formats should not be able to add new planets to the
            planetarium.
    Given the user is logged in on the home page
    And the user has planet toggled from dropdown
    When the user provides a name for the planet "V _ 0 r M 1 r"
    And the user provides an image of the planet "C:\Users\harsh\Desktop\Revature\MyFiles\Project1\src\test\resources\Celestial-Images\Resume_CS.pdf"
    And the user presses the submit button_with_image
    Then the user should get a browser alert saying "Invalid file type"
    And the user should remain on the home page

  @PR6 @HappyPathTesting
  Scenario: Users logged in who provide valid planet image formats should be able to add new planets to the
  planetarium with the selected image.
    Given the user is logged in on the home page
    And the user has planet toggled from dropdown
    When the user provides a name for the planet "V _ 0 r M 1 r"
    And the user provides an image of the planet "C:\Users\harsh\Desktop\Revature\MyFiles\Project1\src\test\resources\Celestial-Images\court_diagram.jpg"
    And the user presses the submit button_with_image
    Then the new planet should be added to the database

  @MR7 @SadPathTesting
  Scenario: Users logged in who provide invalid moon image formats should not be able to add new moons to the
            planetarium.
    Given the user is logged in on the home page
    And the user has moon toggled from dropdown
    When the user provides a name for the moon "M 0 0 N V2"
    And the user provides the id of the planet it orbits "1"
    And the user provides an image of the moon "C:\Users\harsh\Desktop\Revature\MyFiles\Project1\src\test\resources\Celestial-Images\Resume_CS.pdf"
    And the user presses the submit button_with_image
    Then the user should get a browser alert saying "Invalid file type"
    And the user should remain on the home page

  @MR7 @HappyPathTesting
  Scenario: Users logged in who provide valid moon image formats should not be able to add new moons to the
  planetarium.
    Given the user is logged in on the home page
    And the user has moon toggled from dropdown
    When the user provides a name for the moon "M 0 0 N V2"
    And the user provides the id of the planet it orbits "1"
    And the user provides an image of the moon "C:\Users\harsh\Desktop\Revature\MyFiles\Project1\src\test\resources\Celestial-Images\court_diagram.jpg"
    And the user presses the submit button_with_image
    Then the new moon should be added to the database

  @MR1 @MR2 @MR3 @MR4 @SadPathTesting
  Scenario Outline: Users logged in who provide invalid moon inputs should not be able to add moons to the planetarium.
    Given the user is logged in on the home page
    And the user has moon toggled from dropdown
    When the user provides a moon name "<name>"
    And the user provides a planet id "<id>"
    And the user presses the submit button_without_image
    Then the user should get a browser alert saying "<error message>"
    And the user should remain on the home page
    Examples:
      | name                             | id      | error message     |
      | M 0 0 N V2                       | Jupiter | Invalid planet id |
      |                                  | 1       | Invalid moon name |
      | InsertMoonWithReallyLongNameHere | 1       | Invalid moon name |
      | WowANewMoon!                     | 1       | Invalid moon name |
      | Luna                             | 1       | Invalid moon name |

  @PR1 @PR2 @PR3 @PR4 @SadPathTesting
  Scenario Outline: Users logged in who provide invalid planet inputs should not be able to add planets to the
                    planetarium.
    Given the user is logged in on the home page
    And the user has planet toggled from dropdown
    When the user provides a planet name "<name>"
    And the user presses the submit button_without_image
    Then the user should get a browser alert saying "<error message>"
    And the user should remain on the home page
    Examples:
      | name | error message |
      |                                 | Invalid planet name |
      | PlanetWithReallyReallyLongName_ | Invalid planet name |
      | WowANewPlanet!                  | Invalid planet name |
      | Earth                           | Invalid planet name |

