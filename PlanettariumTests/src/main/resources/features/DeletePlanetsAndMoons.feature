Feature: Deleting planets and moons
  Scenario Outline: Planet Happy Path
    Given the user is on the homepage
    And the user selects the planet option
    And the user enters valid planet name "<planetName>"
    And the user clicks the delete planet button
    And the moons owned by the planet are deleted "<id>"
    And the planet table is refreshed "<planetName>"

    Examples:
    |planetName|id|
    |Earth     |1 |

  Scenario Outline: Moon happy path
    Given the user is on the homepage
    Then the user selects the moon option
    And the user enters valid moon name "<moonName>"
    Then the user clicks the delete moon button
    And the planet table is refreshed "<moonName>"

    Examples:
    |moonName|
    |Luna|
    |Titan|


  Scenario Outline: Planet sad path
    Given the user is on the homepage
    And the user selects the planet option
    And the user enters planet name "<planetName>"
    Then the user clicks the delete planet button
    And browser gives invalid planet error

    Examples:
    |planetName|
    |lentokonesuihkuturbiinimoottoriapumekaanikkoaliupseerioppilas|
    |::Broken planet NAme[]                                       |
    |earth                                                        |

  Scenario Outline:Moon Sad Path
    Given the user is on the homepage
    And the user enters the moon name "<moonName>"
    Given the user clicks the delete moon button
    Then browser gives invalid Moon error

    Examples:
    |moonName|
    |lentokonesuihkuturbiinimoottoriapumekaanikkoaliupseerioppilas|
    |::bad name                                                   |