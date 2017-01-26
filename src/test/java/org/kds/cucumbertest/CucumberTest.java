package org.kds.cucumbertest;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by KDS on 1/22/2017.
 */

@RunWith(Cucumber.class)
@CucumberOptions(format = {
        "json:target/cucumber/account_service.json",
        "html:target/cucumber/account_service.html",
        "usage:target/cucumber/account_service-usage.json","junit:target/cucumber/account_service-results.xml",
        "pretty"
}, tags = {"~@ignored"}, features = "src/test/resources/org.kds.cucumbertest")
public class CucumberTest {
}
