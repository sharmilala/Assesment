Feature: Verify completing todo
  @Regression @CompletingTodo
  Scenario Outline: Verify that user is able to mark a todo item as completed by clicking on its corresponding checkbox.

    Given I am on the TodoMVC homepage
    When I type <todoTitle> in the input field
    And I press Enter
    When I mark <todoTitle> as completed
    Then the todo item should be marked as completed

    Examples:
      |todoTitle      |
      |Buy groceries  |

  @Regression @CompletingTodo
  Scenario Outline: Verify that user is able to differentiate a completed todo from a pending todos in the list.

    Given I am on the TodoMVC homepage
    When I type <todoItem1> in the input field
    And I press Enter
    When I type <todoItem2> in the input field
    And I press Enter
    When I mark <todoItem1> as completed
    Then I should see pending item count as <count>

    Examples:
      |todoItem1      | todoItem2     | count |
      |Buy groceries  | Buy Chocolate | 1     |

  @Regression @CompletingTodo
  Scenario: Verify that user has an option to toggle the completion status of a todo item.

  Scenario: Verify empty list message
    Given I am on the TodoMVC homepage
    When I toggle the completion status of a todo item
    Then the completion status of the todo item should be updated