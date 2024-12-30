package com.revature.Steps;

import com.revature.TestRunner;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Login {
    @When("the user provides a valid login username {string}")
    public void the_user_provides_a_valid_login_username(String username) {
        // Write code here that turns the phrase above into concrete actions
        TestRunner.loginPage.setUserName(username);
    }

    @When("the user provides a valid login password {string}")
    public void the_user_provides_a_valid_login_password(String password) {
        // Write code here that turns the phrase above into concrete actions
        TestRunner.loginPage.setPassword(password);
    }

    @When("the user hits the login button")
    public void the_user_hits_the_login_button() {
        // Write code here that turns the phrase above into concrete actions
        TestRunner.loginPage.login();
    }

    @Then("the user is redirected to the home page {string}")
    public void the_user_is_redirected_to_the_home_page(String message) {
        Assert.assertEquals("Welcome to the Home Page " + message, TestRunner.homePage.getHomePageGreeting());
    }

    @When("the user provides a invalid login username {string}")
    public void the_user_provides_a_invalid_login_username(String username) {
        // Write code here that turns the phrase above into concrete actions
        TestRunner.loginPage.setUserName(username);
    }

    @When("the user provides a invalid login password {string}")
    public void the_user_provides_a_invalid_login_password(String password) {
        // Write code here that turns the phrase above into concrete actions
        TestRunner.loginPage.setPassword(password);
    }

    @Then("the user stays on the login page")
    public void the_user_stays_on_the_login_page() {
        // Write code here that turns the phrase above into concrete actions
        TestRunner.wait.until(ExpectedConditions.alertIsPresent());
        String alertText = TestRunner.loginPage.getAlertText();
        Assert.assertEquals("Invalid credentials", alertText);
    }
}
