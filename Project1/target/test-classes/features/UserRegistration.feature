@US1
Feature: User Registration Feature
  As a new user I want to open an account with the Planetarium so I can save my celestial findings
  Background:
    Given The user is on the login page
    And The user clicks on the register link

  @UR1 @UR2 @UR3 @UR4 @UR5 @UR6 @HappyPathTesting
  Scenario: Users can register for the Planetarium with valid credentials
    When the user provides a username "Robert_Downey-Junior1"
    And the user provides a password "Iron_Man-2"
    And the user clicks the create button
    Then the user should get a browser alert saying "Account created successfully"
    And the user should be redirected to the login page


  @UR1 @UR2 @UR3 @UR4 @UR5 @UR6 @SadPathTesting
  Scenario Outline: Users should not be able to register for the Planetarium with invalid credentials
    When the user provides a username "<username>"
    And the user provides a password "<password>"
    And the user clicks the create button
    Then the user should get a browser alert saying "<error message>"
    And the user should remain on the register page
    Examples:
      | username                        | password                        | error message |
      | Batman		                    | Iron_Man-2		              | Invalid username |
      | Hulk		                    | Iron_Man-2		              | Invalid username |
      | IronManReallyRobertDownieJunior	| Iron_Man-2		              | Invalid username |
      | 3-D Man		                    | Iron_Man-2		              | Invalid username |
      | WhoIsMysterio??		            | Iron_Man-2		              | Invalid username |
      | Robert_Downey-Junior1           | Hu1k		                      | Invalid password |
      | Robert_Downey-Junior1           | Odin1sActuallyReallyPowerfulGod |	Invalid password |
      | Robert_Downey-Junior1           | 3ruceBannerFan12		          | Invalid password |
      | Robert_Downey-Junior1           | SpiderMan		                  | Invalid password |
      | Robert_Downey-Junior1           | spid3rman		                  | Invalid password |
      | Robert_Downey-Junior1           | SPID3RMAN		                  | Invalid password |
      | Robert_Downey-Junior1           | Spid3rman!		              | Invalid password |


  @SR1 @HappyPathTesting
  Scenario: The user's password should never be visible when creating an account
    When the user provides a password "Iron_Man-2"
    Then the password should be hidden
