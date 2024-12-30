package com.revature.steps;

import com.revature.TestRunner;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.Alert;

public class UserRegistrationSteps {

    @Given("The user clicks on the register link")
    public void the_user_clicks_on_the_register_link() {
        // Write code here that turns the phrase above into concrete actions
        TestRunner.loginPage.openRegistrationPage();
    }
    @When("the user provides a username {string}")
    public void the_user_provides_a_username(String username) {
        // Write code here that turns the phrase above into concrete actions
        TestRunner.registrationPage.provide_username(username);

    }

    @When("the user provides a password {string}")
    public void the_user_provides_a_password(String password) {
        // Write code here that turns the phrase above into concrete actions
        TestRunner.registrationPage.provide_password(password);
    }

    @Then("the user should be redirected to the login page")
    public void the_user_should_be_redirected_to_the_login_page() {
        // Write code here that turns the phrase above into concrete actions
        TestRunner.wait.until(ExpectedConditions.not(ExpectedConditions.titleIs("Account Creation")));
        Assert.assertEquals("Planetarium Login", TestRunner.driver.getTitle());
    }

    @Then("the user should remain on the register page")
    public void the_user_should_remain_on_the_register_page() {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertEquals("Account Creation", TestRunner.driver.getTitle());

    }

    @And("the user clicks the create button")
    public void theUserClicksTheCreateButton() {
        TestRunner.registrationPage.create_new_account();
    }
}
