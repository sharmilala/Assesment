package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features",
        tags = "@Regression",
        glue={"helpers", "stepdefinitions"},
        plugin = {"pretty", "html:target/cucumber.html", "json:cucumber.json"}
)

public class TestRunner {

}
