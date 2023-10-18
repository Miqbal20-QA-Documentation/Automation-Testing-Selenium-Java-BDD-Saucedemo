Feature: Saucedemo Cart Test

  @Positive @Cart
  Scenario: Verify Success User able to add an item to cart
    Given User on Dashboard Pages Saucedemo
    When User choose an item and click Add to cart button
    And User click shopping cart badges
    Then User redirect to Your Cart Page
    And Item that have been added before is displayed in the cart


  @Positive @Cart
  Scenario: Verify Success User able to add all items to cart
    Given User on Dashboard Pages Saucedemo
    When User choose items and click Add to cart button
    And User click shopping cart badges
    Then User redirect to Your Cart Page
    And All Item that have been added before are displayed in the cart