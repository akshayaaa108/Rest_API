package cucumber.Options;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features",
        glue = {"stepDefinations"},
        tags = "@smoke",
        plugin = {"pretty", "json:target/cucumber.json", "html:target/cucumber-html-report.html", "junit:target/cucumber.xml"}
)
public class TestRunner {

}
