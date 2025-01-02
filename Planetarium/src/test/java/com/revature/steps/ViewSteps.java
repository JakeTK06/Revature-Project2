package com.revature.steps;

import com.revature.TestRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ViewSteps {

    @Given("the user is logged in")
    public void the_user_is_logged_in() {
        TestRunner.loginPage.setupLoggedInUser();
    }

    @Given("the user is on the home page")
    public void the_user_is_on_the_home_page() {
        // TODO: Look into if you can go to home page with the login cached
//        TestRunner.homePage.tryToAccessHomePAgeDirectly();
        System.out.println("The user is on the home page");

    }

    @Then("the user should see their planets and moons")
    public void the_user_should_see_their_planets_and_moons() {
        // TODO: switch from try/finally to using a Cucumber hook to log out the user
        try{
            TestRunner.wait.until(ExpectedConditions.titleIs("Home"));
            // was having issues with test running faster than test could load all the planets
            TestRunner.wait.until(ExpectedConditions.numberOfElementsToBe(By.tagName("tr"), 5));
            Assert.assertEquals(4, TestRunner.homePage.getNumberOfCelestialRows());
            Assert.assertEquals(String.format("Expected 'Welcome to the Home Page Batman, but got %s",
                            TestRunner.homePage.getHomePageGreeting()),
                    "Welcome to the Home Page Batman",
                    TestRunner.homePage.getHomePageGreeting());
        } finally {
            TestRunner.homePage.logout();
        }

    }

    @Given("the user is not logged in")
    public void the_user_is_not_logged_in() {
        TestRunner.loginPage.openLoginPage();
    }

    @When("the user tries to directly access the home page")
    public void the_user_tries_to_directly_access_the_home_page() {

        TestRunner.homePage.tryToAccessHomePAgeDirectly();
    }

    @Then("the user should be denied access")
    public void the_user_should_be_denied_access() {
        Assert.assertNotEquals("Home", TestRunner.driver.getTitle());
    }

    @Given("the user has registered for the first time and logged in")
    public void the_user_has_registered_for_the_first_time_and_logged_in() {
        String username = "Super_man-2001";
        String password = "Krypton-was_2000";

        TestRunner.loginPage.openLoginPage();
        TestRunner.loginPage.clickRegistrationLink();

        // Register user
        TestRunner.registrationPage.setUsername(username);
        TestRunner.registrationPage.setPassword(password);
        TestRunner.wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = TestRunner.driver.switchTo().alert();
        alert.accept();

        // Login user
        TestRunner.wait.until(ExpectedConditions.titleIs("Planetarium Login"));
        TestRunner.loginPage.setUsernameInput(username);
        TestRunner.loginPage.setPasswordInput(password);
        TestRunner.loginPage.submitCredentials();
    }

    @Then("there should be no visible celestial bodies")
    public void there_should_be_no_visible_celestial_bodies() {
        Assert.assertEquals(0, TestRunner.homePage.getNumberOfCelestialRows());
    }

    @When("the user presses the logout button")
    public void the_user_presses_the_logout_button() {
        TestRunner.homePage.logout();
    }

    @Then("the user should be redirected to the login page")
    public void the_user_should_be_redirected_to_the_login_page() {
        // Write code here that turns the phrase above into concrete actions
        TestRunner.wait.until(ExpectedConditions.not(ExpectedConditions.titleIs("Account Creation")));
        Assert.assertEquals("Planetarium Login", TestRunner.driver.getTitle());
    }
}
