Feature: Verify Todo Persistence
  @Regression @PersistenceTodo
  Scenario: Validate that the todo items persist across page reloads or sessions.

    Given I am on the TodoMVC homepage
    And I have added a todo item
    When I reload the page or reopen the application
    Then I should see the previously added todo item

  @Regression @PersistenceTodo
  Scenario: Validate that after reloading the page, todos should still be visible and retain their completion status.

    Given I am on the TodoMVC homepage
    And I have added a todo item
    And I have completed the todo item
    When I reload the page
    Then I should see the previously added todo item with its completion status retained
