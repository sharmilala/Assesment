Feature: Verify adding todo items
  @Regression @AddingTodos
  Scenario Outline: Validate that user is able to add a new todo item by typing in the input field at the top of the page and pressing enter.

    Given I am on the TodoMVC homepage
    When I type <todoTitle> in the input field
    And I press Enter
    Then the todo item <todoTitle> should be added to the list

    Examples:
      |todoTitle      |
      |Buy groceries  |

  @Regression @AddingTodos
  Scenario Outline: Validate that the todo item appears in the list below the input field with the entered title.

    Given I am on the TodoMVC homepage
    When I add a new todo item with title <todoTitle>
    Then the todo item with title <todoTitle> should appear in the list

    Examples:
      |todoTitle      |
      |Clean the house|

  @Regression @AddingTodos
  Scenario Outline: Validate that when user adds a todo, the input field gets cleared for adding another todo.

    Given I am on the TodoMVC homepage
    When I add a new todo item with title <todoTitle>
    Then the todo item with title <todoTitle> should appear in the list
    And the input field should be cleared

    Examples:
      |todoTitle      |
      |Clean the house|
