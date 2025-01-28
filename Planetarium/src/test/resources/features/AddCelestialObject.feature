Feature: Add planet or moon
  As a user I want to add new planets and moons to the Planetarium so I can update my findings

  Background:
    Given the user is logged in
    And the user is on the home page

  Scenario Outline: User can add a new planet with valid data
    When  the user inputs planet "<planet>"
    And   the user adds a planet image of file type "<image type>"
    And   the user submits planet creation data
    Then  the planet should be created
    And   the table should refresh with an extra planet

  Examples:
    |planet|image type|
    |Venus -55_|      |
    |Venus -55_|JPEG  |
    |Venus -55_|PNG   |

  Scenario Outline: User cannot add a new planet with invalid data
    When  the user inputs planet "<planet>"
    And   the user adds a planet image of file type "<image type>"
    And   the user submits planet creation data
    Then  the user should get a browser alert saying "<alert>"

    # For image empty image type check that image input is null in step
  Examples:
    |planet|image type|alert|
    |                               |   |Invalid planet name|
    |Planet Name That-is 1 long_name|   |Invalid planet name|
    |Exciting!! planet              |   |Invalid planet name|
    |Earth                          |   |Invalid planet name|
    |Venus -55_                     |GIF|Invalid file type  |
    |Earth                          |GIF|Invalid planet name|
    |Earth                          |JPEG|Invalid planet name|
    |Earth                          |PNG|Invalid planet name|

  Scenario Outline: User can add a new moon with valid data
    When  the user inputs moon name "<moon>"
    And   the user inputs planet that own the moon "<owner num>"
    And   the user adds a moon image of file type "<image type>"
    And   the user submits moon creation data
    Then  the moon should be created
    And   the table should be refresh with an extra moon

  Examples:
    |moon|owner num|image type|
    |quasi-moon 1 Zoozve_|1|  |
    |quasi-moon 1 Zoozve_|1|JPEG|
    |quasi-moon 1 Zoozve_|1|PNG|



  Scenario Outline: User cannot add a new moon with invalid data
    When  the user inputs moon name "<moon>"
    And   the user inputs planet that own the moon "<owner num>"
    And   the user adds a moon image of file type "<image type>"
    And   the user submits moon creation data
    Then the user should get a browser alert saying "<alert>"

  Examples:
    |moon|owner num|image type|alert|
    |                               |1    |   |Invalid moon name|
    |Moon name that-is way_way2 long|1    |   |Invalid moon name|
    |Exciting!! Moon                |1    |   |Invalid moon name|
    |Luna                           |1    |   |Invalid moon name|
    |quasi-moon 1 Zoozve_           |7    |   |Invalid planet ID|
    |Luna                           |7    |   |Invalid moon name|
    |quasi-moon 1 Zoozve_           |1    |GIF|Invalid file type|
    |Luna                           |1    |GIF|Invalid moon name|
    |quasi-moon 1 Zoozve_           |7    |GIF|Invalid planet ID|
    |Luna                           |7    |GIF|Invalid moon name|
    |Luna                           |1    |JPEG|Invalid moon name|
    |quasi-moon 1 Zoozve_           |7    |JPEG|Invalid planet ID|
    |Luna                           |7    |JPEG|Invalid moon name|
    |Luna                           |1    |PNG|Invalid moon name|
    |quasi-moon 1 Zoozve_           |7    |PNG|Invalid planet ID|
    |Luna                           |7    |PNG|Invalid moon name|