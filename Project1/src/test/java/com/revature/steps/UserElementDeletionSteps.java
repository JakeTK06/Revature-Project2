package com.revature.steps;

import com.revature.TestRunner;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class UserElementDeletionSteps {
    @When("the user clicks on the delete button")
    public void the_user_clicks_on_the_delete_button() {
        // Write code here that turns the phrase above into concrete actions
        TestRunner.homePage.click_delete_button();
    }

    @Then("the existing moon should be deleted")
    public void the_existing_moon_should_be_deleted() {
        // Write code here that turns the phrase above into concrete actions
        TestRunner.wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//tr"), 4));
        Assert.assertEquals(3, TestRunner.homePage.getNumberOfCelestialRows());
    }

    @Then("the existing planet and its moons should be deleted")
    public void theExistingPlanetAndItsMoonsShouldBeDeleted() {
        TestRunner.wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//tr"), 3));
        Assert.assertEquals(2, TestRunner.homePage.getNumberOfCelestialRows());
    }

    @When("the user provides a planet name to delete {string}")
    public void theUserProvidesAPlanetNameToDelete(String planet_name) {
        TestRunner.homePage.setDeleteInput(planet_name);
    }

    @When("the user provides a moon name to delete {string}")
    public void theUserProvidesAMoonNameToDelete(String moon_name) {
        TestRunner.homePage.setDeleteInput(moon_name);
    }
}
