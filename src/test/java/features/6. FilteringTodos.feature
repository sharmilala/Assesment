Feature: Verify Filter Todo Item
  @Regression @FilterTodo
  Scenario Outline: Validate that user is able to filter todos based on their completion status (e.g., all, active, completed).

    Given I am on the TodoMVC homepage
    When I type <todoItem1> in the input field
    And I press Enter
    When I type <todoItem2> in the input field
    And I press Enter
    And I have multiple todos with different completion status
    When I filter todos by status "<status>"
    Then I should see only todos with status "<status>"

    Examples:
      |todoItem1      | todoItem2     | status      |
      |Buy groceries  | Buy Chocolate | completed    |

  @Regression @FilterTodo
  Scenario Outline: Validate that the filtered view should only display todos matching the selected status.

    Given I am on the TodoMVC homepage
    And I have multiple todos with different completion status
    When I filter todos by status "<status>"
    Then I should see only todos with status "<status>"
    And the filtered view should only display todos matching the selected status

    Examples:
      |status      |
      |completed   |

  @Regression @FilterTodo
  Scenario Outline: Validate that there are buttons to switch between different filter options.

    Given I am on the TodoMVC homepage
    And I have multiple todos with different completion status
    When I filter todos by status "<status>"
    Then I should see only todos with status "<status>"
    And the filtered view should only display todos matching the selected status
    And there should be buttons to switch between different filter options

    Examples:
      |status      |
      |completed   |