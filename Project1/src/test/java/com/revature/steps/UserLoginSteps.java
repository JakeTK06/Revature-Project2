package com.revature.steps;

import com.revature.TestRunner;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class UserLoginSteps {
    @Given("the user should be redirected to the home page")
    public void the_user_should_be_redirected_to_the_home_page() {
        // Write code here that turns the phrase above into concrete actions
        TestRunner.wait.until(ExpectedConditions.titleIs("Home"));
        Assert.assertEquals("Home", TestRunner.driver.getTitle());
    }

    @Then("the user should remain on the login page")
    public void the_user_should_remain_on_the_login_page() {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertEquals("Planetarium Login", TestRunner.driver.getTitle());
    }

    @And("the user clicks the login button")
    public void theUserClicksTheLoginButton() {
        TestRunner.loginPage.login();
    }
}
