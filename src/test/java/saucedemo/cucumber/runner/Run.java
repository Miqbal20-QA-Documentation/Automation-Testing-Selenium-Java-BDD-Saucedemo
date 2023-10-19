package saucedemo.cucumber.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/saucedemo/cucumber/features",
        glue = "saucedemo.cucumber.steps",
        plugin = {"html:src/test/java/saucedemo/cucumber/reports/HTML_report.html"}
)

public class Run {


}
