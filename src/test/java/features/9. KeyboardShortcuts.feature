Feature: Verify Keyboard Shortcuts
  @Regression @KeyboardShortcutsTodo
  Scenario: Validate that the application supports keyboard shortcuts for common actions
    Given I am on the TodoMVC homepage
    When I press the keyboard shortcut for adding a todo
    Then a new todo should be added to the list
    When I press the keyboard shortcut for marking a todo as completed
    Then the todo should be marked as completed
    When I press the keyboard shortcut for editing a todo
    Then I should be able to edit the todo
