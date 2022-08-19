package eu.tsystems.mms.tic.testerra.test.cucumber.runner;

import eu.tsystems.mms.tic.testframework.report.TesterraListener;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Listeners;

@Listeners(TesterraListener.class)
@CucumberOptions(
        plugin = {"eu.tsystems.mms.tic.testerra.plugins.cucumber.TesterraReportPlugin"},
        features = "src/test/resources/features/theinternet/FormAuthentication.feature",
        glue = {
                // This package contains some Testerra Cucumber additionals
                "eu.tsystems.mms.tic.testerra.plugins.cucumber",
                // Add your step definition classes
                "eu.tsystems.mms.tic.testerra.test.cucumber.steps"
        }
)
public class RunFormAuthenticationTest extends AbstractTestNGCucumberTests {
}
