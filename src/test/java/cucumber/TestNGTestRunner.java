package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="C:/Users/RAJU PONNAM/eclipse-workspace/SeleniumFrameworkDesign2/src/test/java/cucumber",glue="rahulshetyacademy.stepDefinations",monochrome=false,tags="@ErrorValidation",plugin= {"html:target/cucumber.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests{

}
