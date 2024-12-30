package com.revature.steps;

import com.revature.TestRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class UserElementViewSteps {

    @Given("the user is not logged in")
    public void the_user_is_not_logged_in() {
        TestRunner.loginPage.openLoginPage();
    }

    @When("the user tries to directly access the home page")
    public void the_user_tries_to_directly_access_the_home_page() {
        TestRunner.homePage.tryToAccessHomePageDirectly();
    }

    @Then("they should see their planets and moons")
    public void they_should_see_their_planets_and_moons() {
        try{
            TestRunner.wait.until(ExpectedConditions.titleIs("Home"));
            Assert.assertEquals(
                    String.format(
                            "Expected 'Welcome to the Home Page Batman, but got %s",
                            TestRunner.homePage.getHomePageGreeting()
                    ),
                    "Welcome to the Home Page Batman",
                    TestRunner.homePage.getHomePageGreeting());
            Assert.assertEquals(4, TestRunner.homePage.getNumberOfCelestialRows());
        } finally {
            TestRunner.homePage.logout();
        }
    }

    @Then("the user should be denied access")
    public void the_user_should_be_denied_access() {
        Assert.assertNotEquals("Home",
                TestRunner.driver.getTitle());
    }

    @Then("there should be no visible celestial bodies")
    public void thereShouldBeNoVisibleCelestialBodies() {
        Assert.assertEquals(0, TestRunner.homePage.getNumberOfCelestialRows());
    }

    @When("the user presses the logout button")
    public void theUserPressesTheLogoutButton() {
        TestRunner.homePage.logout();
    }

    /*
    Found a defect. When a new user signs up and logins in, they for some reason see moons that they do not own, but
                    have been added in previously.
    Additionally, it seems the "owner" of the planet is deemed based on the PRIMARY_KEY of the user (or the order the
                    user was registered to the planetarium. By default, all users will see Batman's moons.
    After further inspecting, only moons/planets added by the Batman account are visible. Elements created by other
                    other users are not visible.
     */

}