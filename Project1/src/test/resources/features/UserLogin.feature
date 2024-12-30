@US2
Feature: User Login Feature
  As a user I want to securely access my account so I can interact with the Planetarium in a secure environment

  Background:
    Given The user is on the login page
  @SR3 @HappyPathTesting
  Scenario: Users can login to the planetarium with valid credentials
    * the user provides a username "Batman"
    * the user provides a password "Iamthenight1939"
    And the user clicks the login button
    Then the user should be redirected to the home page

  @SR3 @SadPathTesting
  Scenario Outline: Users should not be able to login to the planetarium with invalid credentials
    When the user provides a username "<username>"
    And the user provides a password "<password>"
    And the user clicks the login button
    Then the user should get a browser alert saying "<error message>"
    And the user should remain on the login page
    Examples:
      | username | password        | error message       |
      | Batman   | Hu1k		       | Invalid credentials |
      | Wanda	 | Iamthenight1939 | Invalid credentials |
      | Wanda	 | Hu1k		       | Invalid credentials |

  @SR1 @HappyPathTesting
  Scenario: The User's password should never be visible when logging in
    When the user provides a password "Iamthenight1939"
    Then the password should be hidden