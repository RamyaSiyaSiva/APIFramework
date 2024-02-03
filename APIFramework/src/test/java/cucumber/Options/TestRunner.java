

package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/features/placeValidations.feature",
plugin ={"json:target/jsonReports/cucumber-report.json","html:target/cucumber-reports.html"},
glue= {"stepDefinations"},
tags= "@Addplace or @Deleteplace or @Regression")
public class TestRunner {
 
//compile test verify
}
