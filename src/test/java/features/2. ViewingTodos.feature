Feature: Verify viewing todo items
  @Regression @ViewingTodos
  Scenario Outline: Validate that user is able to view a list of all my todos.

    Given I am on the TodoMVC homepage
    When I type <todoTitle> in the input field
    And I press Enter
    Then I should see all my todos displayed

    Examples:
      |todoTitle      |
      |Buy groceries  |

  @Regression @ViewingTodos
  Scenario Outline: Validate that each todo item in the list display its title.

    Given I am on the TodoMVC homepage
    When I type <todoTitle> in the input field
    And I press Enter
    When I view the list of todos
    Then each todo item in the list should display its title

    Examples:
      |todoTitle      |
      |Buy groceries  |

