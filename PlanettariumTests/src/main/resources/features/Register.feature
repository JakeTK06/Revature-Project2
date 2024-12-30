Feature: Register Functionality
  Scenario Outline: Happy path
    Given the user is on the login page
    When the user clicks the register link
    When the user provides a valid register username "<userName>"
    And the user provides a valid register password "<passWord>"
    And the user submits the credentials
    Then the user should get a browser alert saying Register successful
    And the user should be redirected to the login page

    Examples:
    |userName|passWord|
    |MasterChief|Pi11arOfAutum|


  Scenario Outline: Sad Path Invalid username
    Given the user is on the login page
    When the user clicks the register link
    When the user provides a invalid register username "<userName>"
    And the user provides a invalid register password "<passWord>"
    And the user submits the credentials
    Then the user should get a browser alert saying Failed to register
    And the user should stay on the register page
    Examples:
      |userName|passWord|
      |Batman  |?NOM    |
      |Llanfairpwllgwyngyllgogerychwyrndrobwllllantysiliogogogoch|password|
      |1eet    |3at     |
      |;Manspider:|2catman:john|