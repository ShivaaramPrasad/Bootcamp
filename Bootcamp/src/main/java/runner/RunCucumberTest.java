package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features= {"src/main/resources/features/Assessment.feature"},
glue= {"steps"},monochrome=true)

public class RunCucumberTest extends AbstractTestNGCucumberTests {


	}

 