package com.revature.Steps;

import com.revature.TestRunner;
import org.junit.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Register {
    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        // Go directly to the login page
        if(TestRunner.loginPage == null){
            TestRunner.setup();
        }
        TestRunner.loginPage.goToLoginPage();
    }

    @When("the user clicks the register link")
    public void the_user_clicks_the_register_link() {
        // Click the link to the register page
        TestRunner.loginPage.clickRegisterLink();
    }


    @When("the user provides a valid register username {string}")
    public void the_user_provides_a_valid_register_username(String username) {
        //Happy path the user enters a valid username
        TestRunner.registerPage.setUserName(username);
    }

    @When("the user provides a valid register password {string}")
    public void the_user_provides_a_valid_register_password(String password) {
        //Happy path the user enters a valid password
        TestRunner.registerPage.setPassword(password);
    }

    @When("the user submits the credentials")
    public void the_user_submits_the_credentials() {
        //Click the register button to submit credentials
        TestRunner.registerPage.register();
    }

    @Then("the user should get a browser alert saying Register successful")
    public void the_user_should_get_a_browser_alert_saying_Register_successful() {
        //Check to make sure that the browser alert is present and is the correct alert
        TestRunner.wait.until(ExpectedConditions.alertIsPresent());
        String alertText = TestRunner.registerPage.getAlertText();
        Assert.assertEquals("Account created successfully", alertText);
    }

    @Then("the user should be redirected to the login page")
    public void the_user_should_be_redirected_to_the_login_page() {
        //Verify the user is on the login page using the pages title
        Assert.assertEquals("Planetarium Login", TestRunner.driver.getTitle());
    }

    @When("the user provides a invalid register username {string}")
    public void the_user_provides_a_invalid_register_username(String username) {
        // Write code here that turns the phrase above into concrete actions
        TestRunner.registerPage.setUserName(username);
    }

    @When("the user provides a invalid register password {string}")
    public void the_user_provides_a_invalid_register_password(String password) {
        // Write code here that turns the phrase above into concrete actions
        TestRunner.registerPage.setPassword(password);
    }

    @Then("the user should get a browser alert saying Failed to register")
    public void the_user_should_get_a_browser_alert_saying_Failed_to_register() {
        // Write code here that turns the phrase above into concrete actions
        String alertText = TestRunner.registerPage.getAlertText();
        boolean textCorrect = alertText.equals("Invalid username") || alertText.equals("Invalid password");
        Assert.assertTrue(textCorrect);
    }

    @Then("the user should stay on the register page")
    public void the_user_should_stay_on_the_register_page() {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertEquals("Account Creation", TestRunner.driver.getTitle());
    }
}
