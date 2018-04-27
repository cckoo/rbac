package cn.cckoo.rbac.steps;

import cn.cckoo.rbac.driver.Driver;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

public class HelloWorldSteps {

    @Autowired
    Driver driver;

    @When("^view the dashboard$")
    public void view_the_dashboard() throws Throwable {
        driver.navigateTo("/");
    }

    @Then("^\"([^\"]*)\" message should be displayed$")
    public void message_should_be_displayed(String message) throws Throwable {
        driver.waitForTextPresent(message);
    }
}
