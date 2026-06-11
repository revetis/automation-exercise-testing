package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features", glue = "stepdefinitions", tags = "@wip", dryRun = false, plugin = {
        "html:target/cucumber-reports.html",
        "json:target/cucumber-reports.json",
        "junit:target/cucumber-reports.xml" })
public class Runner extends AbstractTestNGCucumberTests {

}
