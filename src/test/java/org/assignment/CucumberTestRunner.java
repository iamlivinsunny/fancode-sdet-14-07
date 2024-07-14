package org.assignment;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/feature/TodoTaskCompletionVerification.feature"},
        glue = {"org.assignment.step_definition"},
        plugin = {"pretty", "html:src/test/resources/execution-reports/cucumber.html", "json:target/cucumber-html-reports/cucumber.json", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"})
public class CucumberTestRunner {
}
