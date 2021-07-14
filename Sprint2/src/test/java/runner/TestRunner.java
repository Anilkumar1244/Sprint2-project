package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/SprintFeature/Signin.feature",
                 glue= {"stepdefinitions"},
                 dryRun=false,
                 monochrome = true)
public class TestRunner {

}

