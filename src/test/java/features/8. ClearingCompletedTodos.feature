Feature: Clearing Completed Todo
  @Regression @ClearingCompletedTodo
  Scenario: Verify that user is able to clear all completed todos with a single action (e.g., clicking on a "Clear Completed" button).

    Given I am on the TodoMVC homepage
    And I have added multiple todos
    And I have completed some todos
    When I click on the "Clear Completed" button
    Then all completed todos should be removed from the list
