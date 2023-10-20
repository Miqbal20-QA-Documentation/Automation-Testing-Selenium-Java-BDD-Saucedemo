Feature: Authentication Test

  @Positive @Auth
  Scenario Outline: Verify Success User Login
    Given User on Login Pages Saucedemo
    When User fills valid <username> as username and <password> as password
    And User click on Login button
    Then User redirect to Dashboard Page

    Examples:
    | username      | password     |
    | standard_user | secret_sauce |
    | problem_user  | secret_sauce |
    | error_user    | secret_sauce |
    | visual_user   | secret_sauce |

  @Negative @Auth
  Scenario Outline: Verify Failed User Login
    Given User on Login Pages Saucedemo
    When User fills valid <username> as username and <password> as password
    And User click on Login button
    Then User get message <error> as error

    Examples:
      | username         | password     | error           |
      | locked_out_user  | secret_sauce | Epic sadface: Sorry, this user has been locked out. |
      | standard_user    |              | Epic sadface: Password is required |
      |                  | secret_sauce | Epic sadface: Username is required |
      |                  |              | Epic sadface: Username is required |

    @Positive @Auth
    Scenario: Verify Success User Logout
      Given User has been login saucedemo
      When User click on navigation icon
      Then User click on Logout

