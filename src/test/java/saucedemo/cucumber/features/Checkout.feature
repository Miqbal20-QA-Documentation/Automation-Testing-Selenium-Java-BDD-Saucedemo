Feature: Checkout Test

  @Postive @Checkout
  Scenario Outline: Verify Success User able to checkout an item
    Given User has logged in Saucedemo
    Then User choose one item and click Add to cart button
    And User go to checkout page
    And User click Checkout button
    And User fills <firstname> as First Name, <lastname> as Last Name, <postalcode> as Postal Code
    And User Click Continue button
    Then User Click on Finish button ot checkout item
    And Success message is showing up

    Examples:
      | firstname | lastname | postalcode |
      | Muhammad  | Iqbal    | 12345      |

  @Negative @Checkout
  Scenario Outline: Verify Failed User able to checkout an item
    Given User has logged in Saucedemo
    Then User choose one item and click Add to cart button
    And User go to checkout page
    And User click Checkout button
    And User fills <firstname> as First Name, <lastname> as Last Name, <postalcode> as Postal Code
    And User Click Continue button
    Then Error <error> as error message is showing up

    Examples:
      | firstname | lastname | postalcode | error                          |
      | Muhammad  | Iqbal    |            | Error: Postal Code is required |
      | Muhammad  |          | 12345      | Error: Last Name is required   |
      |           | Iqbal    | 12345      | Error: First Name is required  |
      | Muhammad  |          |            | Error: Last Name is required   |
      |           | Iqbal    |            | Error: First Name is required  |
      |           |          | 12345      | Error: First Name is required  |
      |           |          |            | Error: First Name is required  |

  @Postive @Checkout
  Scenario Outline: Verify Success User able to checkout all item
    Given User has logged in Saucedemo
    Then User choose all item and click Add to cart button
    And User go to checkout page
    And User click Checkout button
    And User fills <firstname> as First Name, <lastname> as Last Name, <postalcode> as Postal Code
    And User Click Continue button
    Then User Click on Finish button ot checkout item
    And Success message is showing up

    Examples:
      | firstname | lastname | postalcode |
      | Muhammad  | Iqbal    | 12345      |

  @Negative @Checkout
  Scenario Outline: Verify Failed User able to checkout all item
    Given User has logged in Saucedemo
    Then User choose all item and click Add to cart button
    And User go to checkout page
    And User click Checkout button
    And User fills <firstname> as First Name, <lastname> as Last Name, <postalcode> as Postal Code
    And User Click Continue button
    Then Error <error> as error message is showing up

    Examples:
      | firstname | lastname | postalcode | error                          |
      | Muhammad  | Iqbal    |            | Error: Postal Code is required |
      | Muhammad  |          | 12345      | Error: Last Name is required   |
      |           | Iqbal    | 12345      | Error: First Name is required  |
      | Muhammad  |          |            | Error: Last Name is required   |
      |           | Iqbal    |            | Error: First Name is required  |
      |           |          | 12345      | Error: First Name is required  |
      |           |          |            | Error: First Name is required  |
