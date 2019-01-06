 
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber; 

/*
 * @version-0.1
 * @author-Aditi
 */

@RunWith(Cucumber.class) 
@CucumberOptions(
		 plugin={ "pretty", "json:target/cucumber-reports/Cucumber.json",
				 "junit:target/cucumber-reports/Cucumber.xml",
		 "html:target/cucumber-reports"},
		   features= "src/test/resources/feature",
		   glue="stepdefs",
		   monochrome = true)


public class RunTest { 
	
}