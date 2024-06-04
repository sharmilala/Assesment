Feature: Verify deleting Todo Item
  @Regression @DeletingTodo
  Scenario Outline: Validate that user is able to delete a todo item by clicking on its corresponding delete button (cross icon)

    Given I am on the TodoMVC homepage
    When I type <todoTitle> in the input field
    And I press Enter
    Then the todo item <todoTitle> should be added to the list
    When I delete the todo <todoTitle>
    Then the todo <todoTitle> should be deleted



    Examples:
      |todoTitle      |
      |Buy groceries  |

