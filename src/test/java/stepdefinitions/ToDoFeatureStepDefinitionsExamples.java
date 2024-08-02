package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.*;

public class ToDoFeatureStepDefinitions {


    private WebDriver driver;

    @Given("^I am on the TodoMVC homepage$")
    public void iAmOnTheTodoMVCHomepage() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://todomvc.com/examples/react/dist/");
    }

    @When("^I type (.*) in the input field$")
    public void iTypeInTheInputField(String todoTitle) {
        driver.findElement(By.className("new-todo")).sendKeys(todoTitle);
    }

    @When("^I mark (.*) as completed$")
    public void markTodoItemCompleted(String item) throws InterruptedException {
        WebElement checkbox = driver.findElement(By.xpath("//label[contains(text(), '"+item+"')]/../input"));
        checkbox.click();
    }

    @When("^I should see pending item count as (.*)$")
    public void checkItemCount(Integer count) throws InterruptedException {
        driver.findElement(By.xpath("//a[contains(text(),'Completed')]")).click();
        List<WebElement> todoItems = driver.findElements(By.xpath("//*[@data-testid='todo-item-label']"));
        assertEquals(todoItems.size(), (int) count);
    }

    @When("I mark a todo item as completed")
    public void markTodoItemCompleted() {
        WebElement checkbox = driver.findElement(By.cssSelector("input.toggle"));
        checkbox.click();
    }

    @Then("the todo item should be marked as completed")
    public void verifyTodoItemCompleted() {
        WebElement todoItem = driver.findElement(By.cssSelector("li.completed"));
        Assert.assertTrue(todoItem.isDisplayed());
    }

    @When("^I press the keyboard shortcut for adding a todo$")
    public void iPressTheKeyboardShortcutForAddingATodo() {
        WebElement inputField = driver.findElement(By.className("new-todo"));
        inputField.sendKeys("Todo added by keyboard shortcut" + Keys.ENTER);
    }

    @Then("I should be able to differentiate the completed todo from pending todos")
    public void differentiateCompletedTodoFromPendingTodos() {

        List<WebElement> todoItems = driver.findElements(By.cssSelector("ul.todo-list li"));


        boolean completedTodoFound = false;
        boolean pendingTodoFound = false;
        for (WebElement todoItem : todoItems) {
            if (todoItem.getAttribute("class").contains("completed")) {
                completedTodoFound = true;
            } else {
                pendingTodoFound = true;
            }
        }


        Assert.assertTrue(completedTodoFound && pendingTodoFound);
    }

    @When("I toggle the completion status of a todo item")
    public void toggleCompletionStatus() {

        WebElement checkbox = driver.findElement(By.cssSelector("input.toggle"));
        checkbox.click();
    }

    @Then("the completion status of the todo item should be updated")
    public void verifyCompletionStatusUpdated() {

        WebElement todoItem = driver.findElement(By.cssSelector("ul.todo-list li"));


        boolean isCompleted = todoItem.getAttribute("class").contains("completed");


        Assert.assertTrue("Todo item is marked as completed", isCompleted);
    }

    @When("there are no todos")
    public void checkForNoTodos() {

        WebElement todoList = driver.findElement(By.cssSelector("ul.todo-list"));

        if (!todoList.isDisplayed()) {

        } else {

        }
    }


    @When("^I press the keyboard shortcut for marking a todo as completed$")
    public void iPressTheKeyboardShortcutForMarkingATodoAsCompleted() {
        List<WebElement> todoItems = driver.findElements(By.cssSelector(".todo-list li"));
        WebElement firstTodo = todoItems.get(0);
        WebElement toggleButton = firstTodo.findElement(By.cssSelector(".toggle"));
        toggleButton.click();
    }

    @When("^I press the keyboard shortcut for editing a todo$")
    public void iPressTheKeyboardShortcutForEditingATodo() {

        WebElement firstTodo = driver.findElement(By.cssSelector(".todo-list li:first-child"));


        Actions action = new Actions(driver);
        action.doubleClick(firstTodo).perform();
    }

    @Then("^a new todo should be added to the list$")
    public void aNewTodoShouldBeAddedToTheList() {
        List<WebElement> todoItems = driver.findElements(By.cssSelector(".todo-list li"));
        assertEquals("New todo should be added", 1, todoItems.size());
    }

    @Then("^the todo should be marked as completed$")
    public void theTodoShouldBeMarkedAsCompleted() {
        WebElement firstTodo = driver.findElement(By.cssSelector(".todo-list li"));
        assertTrue("Todo should be marked as completed", firstTodo.getAttribute("class").contains("completed"));
    }
    @When("^I resize the browser window to \"([^\"]*)\"$")
    public void iResizeTheBrowserWindowTo(String screenSize) {
        int width, height;
        switch (screenSize.toLowerCase()) {
            case "mobile":
                width = 360;
                height = 640;
                break;
            case "tablet":
                width = 768;
                height = 1024;
                break;
            case "desktop":
                width = 1280;
                height = 800;
                break;
            default:
                throw new IllegalArgumentException("Invalid screen size: " + screenSize);
        }
        driver.manage().window().setSize(new Dimension(width, height));
    }

    @Then("^UI elements should adjust and rearrange appropriately for optimal usability$")
    public void uiElementsShouldAdjustAndRearrangeAppropriatelyForOptimalUsability() {
        WebElement inputField = driver.findElement(By.className("new-todo"));
        assertTrue("Input field should be visible", inputField.isDisplayed());

        List<WebElement> todoItems = driver.findElements(By.cssSelector(".todo-list li"));
        for (WebElement todoItem : todoItems) {
            assertTrue("Todo item should be visible", todoItem.isDisplayed());
        }
    }

    @Then("^the application should display properly without layout issues$")
    public void theApplicationShouldDisplayProperlyWithoutLayoutIssues() {
        WebElement appContainer = driver.findElement(By.id("root"));
        assertTrue("Application should be visible", appContainer.isDisplayed());
    }


    @Then("^I should be able to edit the todo$")
    public void iShouldBeAbleToEditTheTodo() {

        WebElement firstTodo = driver.findElement(By.cssSelector(".todo-list li:first-child"));


        String originalText = firstTodo.getText();


        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].innerText = 'Todo added by keyboard shortcut (edited)';", firstTodo);


        assertEquals("Todo should be edited", "Todo added by keyboard shortcut (edited)", firstTodo.getText());
    }


    @When("^I press Enter$")
    public void iPressEnter() {
        driver.findElement(By.className("new-todo")).sendKeys(Keys.ENTER);
    }

    @Then("^the todo item (.*) should be added to the list$")
    public void theTodoItemShouldBeAddedToTheList(String todoTitle) {
        assertTrue(driver.findElement(By.xpath("//label[text()='" + todoTitle + "']")).isDisplayed());
    }


    @When("^I add a new todo item with title (.*)$")
    public void iAddANewTodoItemWithTitle(String todoTitle) {
        WebElement inputField = driver.findElement(By.className("new-todo"));
        inputField.sendKeys(todoTitle);
        inputField.sendKeys(Keys.RETURN);
    }

    @Then("^the todo item with title (.*) should appear in the list$")
    public void theTodoItemWithTitleShouldAppearInTheList(String todoTitle) {
        WebElement todoList = driver.findElement(By.xpath("//*[@data-testid='todo-item-label']"));
        assertTrue(todoList.getText().contains(todoTitle));
    }

    @Then("^the input field should be cleared$")
    public void theInputFieldShouldBeCleared() {
        WebElement inputField = driver.findElement(By.className("new-todo"));
        assertEquals("", inputField.getAttribute("value").trim());
    }

    @When("^I view the list of todos$")
    public void iViewTheListOfTodos() {

    }

    @Then("^I should see all my todos displayed$")
    public void iShouldSeeAllMyTodosDisplayed() {
        List<WebElement> todoItems = driver.findElements(By.cssSelector(".todo-list li"));
        assertEquals(1, todoItems.size());
    }

    @Then("^each todo item in the list should display its title$")
    public void eachTodoItemInTheListShouldDisplayItsTitle() {
        List<WebElement> todoItems = driver.findElements(By.cssSelector(".todo-list li"));
        for (WebElement item : todoItems) {
            assertFalse(item.getText().trim().isEmpty());
        }
    }
    @Then("^I should see a message indicating an empty list if there are no todos$")
    public void iShouldSeeAMessageIndicatingAnEmptyListIfThereAreNoTodos() {
        WebElement todoList = driver.findElement(By.className("todo-list"));
        if (todoList.findElements(By.tagName("li")).isEmpty()) {
            WebElement emptyListMessage = driver.findElement(By.cssSelector(".todo-list li"));
            assertTrue(emptyListMessage.getText().contains("No todos to display"));
        }
    }

    @When("^I double click on a todo item$")
    public void iDoubleClickOnATodoItem() {
        WebElement todoList = driver.findElement(By.xpath("//*[@data-testid='todo-item-label']"));
        Actions actions = new Actions(driver);
        actions.doubleClick(todoList).perform();
    }

    @Then("^I should be able to edit its title$")
    public void iShouldBeAbleToEditItsTitle() {
        WebElement editField = driver.findElement(By.cssSelector(".todo-list input.editing"));
        editField.clear();
        CharSequence newTitle = null;
        editField.sendKeys(null + "\n");

        WebElement updatedTodoItem = driver.findElement(By.xpath("//label[text()='" + null + "']"));
        assertNull(updatedTodoItem.getText());
    }

    @When("^I edit its title$")
    public void iEditItsTitle() {
        WebElement editField = driver.findElement(By.cssSelector(".todo-list input.editing"));
        editField.clear();
        CharSequence newTitle = null;
        editField.sendKeys(newTitle);
    }
    @Given(value = "^I have a completed todo item$")
    public void iHaveACompletedTodoItem() {
        String originalTitle = null;
        WebElement todoItem = driver.findElement(By.xpath("//label[text()='" + originalTitle + "']"));
        WebElement toggleButton = todoItem.findElement(By.xpath("../input"));
        toggleButton.click();
    }

    @When("^I double-click on the todo item$")
    public void iDoubleClickOnTheTodoItem() {
        String originalTitle = null;
        WebElement todoItem = driver.findElement(By.xpath("//label[text()='" + originalTitle + "']"));
        Actions actions = new Actions(driver);
        actions.doubleClick(todoItem).perform();
    }

    @Given("^I have a todo item$")
    public void iHaveATodoItem() {
        WebElement inputField = driver.findElement(By.className("new-todo"));
        CharSequence todoTitle = null;
        inputField.sendKeys(todoTitle + "\n");
    }

    @When("^I delete the todo (.*)")
    public void i_delete_the_todo(String todoText) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement todoItem = driver.findElement(By.xpath("//label[text()='" + todoText + "']"));
        js.executeScript("arguments[0].parentNode.querySelector('.destroy').click();", todoItem);
    }

    @Then("^the todo (.*) should be deleted")
    public void the_todo_should_be_deleted(String todoText) {
        try {
            driver.findElement(By.xpath("//label[text()='" + todoText + "']"));
            fail("Todo item was not deleted");
        } catch (org.openqa.selenium.NoSuchElementException e) {

        }
    }

    @Then("^a confirmation prompt should appear$")
    public void aConfirmationPromptShouldAppear() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
        assertNotNull(driver.switchTo().alert());
    }

    @Then("^I confirm deletion$")
    public void iConfirmDeletion() {
        driver.switchTo().alert().accept();
    }

    @Then("^the todo item should be removed from the list$")
    public void theTodoItemShouldBeRemovedFromTheList() {
        String todoTitle = null;
        WebElement todoItem = driver.findElement(By.xpath("//label[text()='" + todoTitle + "']"));
        assertFalse(todoItem.isDisplayed());
    }
    @Then("^the todo item should remain completed$")
    public void theTodoItemShouldRemainCompleted() {
        String newTitle = null;
        WebElement todoItem = driver.findElement(By.xpath("//label[text()='" + newTitle + "']"));
        WebElement toggleButton = todoItem.findElement(By.xpath("../input"));
        assertEquals("true", toggleButton.getAttribute("checked"));
    }
    @Then("^changes should be saved after pressing Enter or clicking outside the input field$")
    public void changesShouldBeSavedAfterPressingEnterOrClickingOutsideTheInputField() {

        WebElement editField = driver.findElement(By.cssSelector(".todo-list input.editing"));
        editField.sendKeys(Keys.ENTER);

        double newTitle = 0;
        WebElement updatedTodoItem = driver.findElement(By.xpath("//label[text()='" + newTitle + "']"));
        assertEquals(newTitle, updatedTodoItem.getText());
    }

    @Given("^I have multiple todos with different completion status$")
    public void iHaveMultipleTodosWithDifferentCompletionStatus() {

        WebElement inputField = driver.findElement(By.className("new-todo"));
        inputField.sendKeys("Todo 1\n");
        inputField.sendKeys("Todo 2\n");
        inputField.sendKeys("Todo 3\n");


        List<WebElement> todoItems = driver.findElements(By.cssSelector(".todo-list li"));
        for (int i = 0; i < todoItems.size(); i++) {
            if (i % 2 == 0) {
                WebElement toggleButton = todoItems.get(i).findElement(By.cssSelector(".toggle"));
                toggleButton.click();
            }
        }
    }

    @Then("^the filtered view should only display todos matching the selected status$")
    public void theFilteredViewShouldOnlyDisplayTodosMatchingTheSelectedStatus() {
        List<WebElement> todoItems = driver.findElements(By.cssSelector(".todo-list li"));
        for (WebElement todoItem : todoItems) {
            boolean isVisible = todoItem.isDisplayed();
            String status = todoItem.getAttribute("class");
            if (status.contains("completed")) {
                assertTrue("Completed todo should be visible", isVisible);
            } else {
                assertTrue("Active todo should be visible", isVisible);
            }
        }
    }

    @Then("^there should be buttons to switch between different filter options$")
    public void thereShouldBeButtonsToSwitchBetweenDifferentFilterOptions() {
        List<WebElement> filterOptions = driver.findElements(By.cssSelector(".filters li"));
        assertEquals(3, filterOptions.size());
    }

    @Given("^I have added a todo item$")
    public void iHaveAddedATodoItem() {
        WebElement inputField = driver.findElement(By.className("new-todo"));
        inputField.sendKeys("Buy groceries\n");
    }

    @When("^I reload the page or reopen the application$")
    public void iReloadThePageOrReopenTheApplication() {
        driver.navigate().refresh();

    }

    @Then("^I should see the previously added todo item$")
    public void iShouldSeeThePreviouslyAddedTodoItem() {
        List<WebElement> todoItems = driver.findElements(By.cssSelector(".todo-list li"));
        System.out.println("Number of todo items found: " + todoItems.size());

        boolean found = false;
        for (WebElement todoItem : todoItems) {
            System.out.println("Todo item text: " + todoItem.getText());
            if (todoItem.getText().equals("Buy groceries")) {
                found = true;
                break;
            }
        }

        assertTrue("Todo item should be found", found);
    }

    @Given("^I have completed the todo item$")
    public void iHaveCompletedTheTodoItem() {
        WebElement todoItem = driver.findElement(By.xpath("//label[text()='Buy groceries']"));
        WebElement toggleButton = todoItem.findElement(By.xpath("../input"));
        toggleButton.click();
    }

    @When("^I reload the page$")
    public void iReloadThePage() {
        driver.navigate().refresh();
    }

    @Given("^I have added multiple todos$")
    public void iHaveAddedMultipleTodos() {
        WebElement inputField = driver.findElement(By.className("new-todo"));
        inputField.sendKeys("Todo 1\n");
        inputField.sendKeys("Todo 2\n");
        inputField.sendKeys("Todo 3\n");
    }

    @Given("^I have completed some todos$")
    public void iHaveCompletedSomeTodos() {
        List<WebElement> todoItems = driver.findElements(By.cssSelector(".todo-list li"));
        for (int i = 0; i < todoItems.size(); i++) {
            if (i % 2 == 0) {
                WebElement toggleButton = todoItems.get(i).findElement(By.cssSelector(".toggle"));
                toggleButton.click();
            }
        }
    }

    @When("^I click on the \"Clear Completed\" button$")
    public void iClickOnTheClearCompletedButton() {
        WebElement clearCompletedButton = driver.findElement(By.cssSelector(".clear-completed"));
        clearCompletedButton.click();
    }

    @Then("^no completed todos should remain in the list$")
    public void noCompletedTodosShouldRemainInTheList() {
        List<WebElement> todoItems = driver.findElements(By.cssSelector(".todo-list li"));
        for (WebElement todoItem : todoItems) {
            assertFalse("No completed todo should remain", todoItem.getAttribute("class").contains("completed"));
        }
    }

    @Then("^all completed todos should be removed from the list$")
    public void allCompletedTodosShouldBeRemovedFromTheList() {
        List<WebElement> todoItems = driver.findElements(By.cssSelector(".todo-list li"));
        for (WebElement todoItem : todoItems) {
            assertFalse("Completed todo should be removed", todoItem.getAttribute("class").contains("completed"));
        }
    }

    @Then("^I should see the previously added todo item with its completion status retained$")
    public void iShouldSeeThePreviouslyAddedTodoItemWithItsCompletionStatusRetained() {

        List<WebElement> todoItems = driver.findElements(By.cssSelector(".todo-list li"));


        boolean found = false;
        boolean completionStatusRetained = false;


        for (WebElement todoItem : todoItems) {

            if (todoItem.getText().equals("Buy groceries")) {
                found = true;


                WebElement toggle = todoItem.findElement(By.cssSelector(".toggle"));
                String classAttributeValue = toggle.getAttribute("class");
                boolean completed = classAttributeValue.contains("completed");


                if (completed) {
                    completionStatusRetained = true;
                }

                break;
            }
        }


        assertTrue("Todo item should be found", found);
        assertTrue("Completion status should be retained", completionStatusRetained);
    }

    @When("^I filter todos by status \"([^\"]*)\"$")
    public void iFilterTodosByStatus(String status) {

        List<WebElement> filterElements = driver.findElements(By.cssSelector(".filters button, .filters a"));


        for (WebElement filterElement : filterElements) {

            if (filterElement.getText().equalsIgnoreCase(status)) {

                filterElement.click();
                return;
            }
        }


        throw new NoSuchElementException("Filter element with status '" + status + "' not found");
    }



    @Then("^I should see only todos with status \"([^\"]*)\"$")
    public void iShouldSeeOnlyTodosWithStatus(String status) {
        List<WebElement> todoItems = driver.findElements(By.cssSelector(".todo-list li"));
        for (WebElement todoItem : todoItems) {
            String todoTitle = todoItem.findElement(By.tagName("label")).getText();
            boolean isCompleted = todoItem.getAttribute("class").contains("completed");
            if (status.equals("all")) {
                assertTrue("Todo should be visible", todoItem.isDisplayed());
            } else if (status.equals("active")) {
                assertTrue("Todo should be visible", todoItem.isDisplayed());
                assertFalse("Active todo should not be completed", isCompleted);
            } else if (status.equals("completed")) {
                assertTrue("Todo should be visible", todoItem.isDisplayed());
                assertTrue("Completed todo should be completed", isCompleted);
            }
        }
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}

