Feature: Login functionality
  Scenario Outline:Happy Path
    Given the user is on the login page
    When the user provides a valid login username "<userName>"
    And  the user provides a valid login password "<passWord>"
    Then the user hits the login button
    And the user is redirected to the home page "<message>"

    Examples:
    |userName|passWord|message|
    |Batman  |Iamthenight1939|Batman|

  Scenario Outline: Sad Path
    Given the user is on the login page
    When the user provides a invalid login username "<userName>"
    And  the user provides a invalid login password "<passWord>"
    And the user hits the login button
    And the user stays on the login page

    Examples:
    |userName|passWord|
    |Manbat  |Iamnotthenight|
    |NotUser |Iamthenight1939|