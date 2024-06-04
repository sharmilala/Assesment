Feature: Verify editing Todo Item
  @Regression @EditingTodo
  Scenario Outline: Validate that user is able to edit the title of a todo item by double-clicking on it.

    Given I am on the TodoMVC homepage
    When I type <todoTitle1> in the input field
    And I press Enter
    Then the todo item <todoTitle1> should be added to the list
    When I double click on a todo item
    Then I type <todoTitle2> in the input field
    And I press Enter
    Then the todo item <todoTitle2> should be added to the list

    Examples:
      |todoTitle1     | todoTitle2    |
      |Buy groceries  | Buy chocolate |


  @Regression @EditingTodo
  Scenario Outline: Validate that editing a todo item does not affect its completion status

    Given I am on the TodoMVC homepage
    When I type <todoTitle1> in the input field
    And I press Enter
    Then the todo item <todoTitle1> should be added to the list
    When I mark <todoTitle1> as completed
    Then I double click on a todo item
    When I type <todoTitle2> in the input field
    Then I should see pending item count as <count>

    Examples:
      |todoTitle1     | todoTitle2    |count |
      |Buy groceries  | Buy chocolate | 1    |
