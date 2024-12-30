Feature:Adding new planets and moons
  Scenario Outline: Planet Happy Path
    Given the user is on the homepage
    Then the user selects the planet option
    And the user enters a valid planet name "<validPlanetName>"
    And the user enters a valid planet image "<img>"
    And the user clicks the add planet button
    Then the planet table is updated planet "<validPlanetName>"

    Examples:
    |validPlanetName|img|
    |Zanath          |src/main/resources/Celestial-Images/planet-1.jpg|

  Scenario Outline: Moon Happy Path
    Given the user is on the homepage
    Then the user selects the moon option
    And the user enters a valid moon name "<validMoonName>"
    And the moon has a valid owner planet "<ownerPlanet>"
    And the user enters a valid moon image "<img>"
    And the user clicks the add moon button
    Then the planet table is updated moon "<validMoonName>"

    Examples:
      |validMoonName|ownerPlanet|img|
      |Pios         |2           |xx |
      |Algeras      |1           |xx |

  Scenario Outline:Sad Planet Path
    Given the user is on the homepage
    Then the user selects the planet option
    And the user enters an invalid name planet "<invalidPlanetName>"
    And the user enters a planet image "<img>"
    And the user clicks the add planet button
    Then the browser gives the alert "<message>"

    Examples:
    |invalidPlanetName|img|message|
    |lentokonesuihkuturbiinimoottoriapumekaanikkoaliupseerioppilas| | |
    |::Broken planet Name[]                                       | | |

  Scenario Outline:Sad Moon Path
    Given the user is on the homepage
    Then the user selects the moon option
    And the user clicks the add planet button
    And the user enters an invalid moon name "<invalidMoonName>"
    And the user add the owner planet id "<id>"
    And the user add the planets image "<img>"
    Then the browser gives the alert "<message>"

    Examples:
      |invalidMoonName|id|message|
      |lentokonesuihkuturbiinimoottoriapumekaanikkoaliupseerioppilas|1|Invalid moon name|
      |::bad name;;                                                 |1|Invalid moon name|
      |ValidMoon                                                    |500|Invalid planet id|