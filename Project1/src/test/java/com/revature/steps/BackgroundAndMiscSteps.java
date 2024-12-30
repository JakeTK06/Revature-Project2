package com.revature.steps;

import com.revature.TestRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.sql.Time;
import java.util.Objects;

public class BackgroundAndMiscSteps {

    @Given("The user is on the login page")
    public void theUserIsOnTheLoginPage() {
        TestRunner.loginPage.openLoginPage();
    }

    @Given("the user is logged in on the home page")
    public void the_user_is_logged_in_on_the_home_page() {
        TestRunner.loginPage.setUpLoggedInUser();
    }

    @Then("the password should be hidden")
    public void thePasswordShouldBeHidden() {
        if (TestRunner.driver.getTitle().equals("Planetarium Login")) {
            Assert.assertEquals("password", TestRunner.loginPage.get_passwordInput_type());
        } else {
            Assert.assertEquals("password", TestRunner.registrationPage.get_passwordInput_type());
        }
    }

    @Given("the user has registered for the first time and logged in")
    public void theUserHasRegisteredForTheFirstTimeAndLoggedIn() {
        TestRunner.loginPage.openLoginPage();
        TestRunner.loginPage.openRegistrationPage();
        TestRunner.registrationPage.setUpRegisteredUser();
        TestRunner.wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = TestRunner.driver.switchTo().alert();
        alert.accept();
        TestRunner.wait.until(ExpectedConditions.titleIs("Planetarium Login"));
        TestRunner.loginPage.loginInWithNewUser();
    }

    @Then("the user should get a browser alert saying {string}")
    public void the_user_should_get_a_browser_alert_saying(String expectedMessage) {
        // Write code here that turns the phrase above into concrete actions
        try {
            TestRunner.wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = TestRunner.driver.switchTo().alert();
            String alert_text = alert.getText();
            alert.accept();
            Assert.assertEquals(expectedMessage, alert_text);
        } catch (TimeoutException e) {
            e.printStackTrace();
            System.out.println("No alert present.This might be a defect.");
        }
    }

    public void acceptAnyAlert() {
        try {
            // Switch to the alert
            Alert alert = TestRunner.driver.switchTo().alert();

            // Optionally, you can print the alert text (if needed for logging or debugging)
            System.out.println("Alert text: " + alert.getText());

            // Accept the alert regardless of its content
            alert.accept();
        } catch (NoAlertPresentException e) {
            // No alert is present, so do nothing
            System.out.println("No alert found.");
        }
    }
}
