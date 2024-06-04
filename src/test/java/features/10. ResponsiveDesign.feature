Feature: Verify the responsiveness of the design
  @Regression @ResponsiveDesign
  Scenario Outline: Validate that the application is responsive and works well on different screen sizes and devices.

    Given I am on the TodoMVC homepage
    When I resize the browser window to "<screen_size>"
    Then the application should display properly without layout issues

    Examples:
      |screen_size  |
      |tablet       |

  @Regression @ResponsiveDesign
  Scenario Outline: Validate that UI elements adjust and rearrange appropriately for optimal usability.

    Given I am on the TodoMVC homepage
    When I resize the browser window to "<screen_size>"
    Then UI elements should adjust and rearrange appropriately for optimal usability

    Examples:
      |screen_size  |
      |tablet       |