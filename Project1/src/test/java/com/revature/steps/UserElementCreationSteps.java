package com.revature.steps;

import com.revature.TestRunner;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class UserElementCreationSteps {
    @When("the user provides a name for the moon {string}")
    public void the_user_provides_a_name_for_the_moon(String moon_name) {
        // Write code here that turns the phrase above into concrete actions
        TestRunner.homePage.setMoonNameInput(moon_name);
    }

    @When("the user provides the id of the planet it orbits {string}")
    public void the_user_provides_the_id_of_the_planet_it_orbits(String planet_id) {
        // Write code here that turns the phrase above into concrete actions
        TestRunner.homePage.setOrbitedPlanetInput(planet_id);
    }

    @When("the user presses the submit button_without_image")
    public void the_user_presses_the_submit_button() {
        // Write code here that turns the phrase above into concrete actions
        TestRunner.homePage.click_submit_button();
    }

    @Then("the new moon should be added to the database")
    public void the_new_moon_should_be_added_to_the_database() {
        // Write code here that turns the phrase above into concrete actions
        TestRunner.wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//tr"), 6));
        Assert.assertEquals(5, TestRunner.homePage.getNumberOfCelestialRows());
    }

    @When("the user provides a name for the planet {string}")
    public void the_user_provides_a_name_for_the_planet(String planetName) {
        // Write code here that turns the phrase above into concrete actions
        TestRunner.homePage.setPlanetNameInput(planetName);
    }

    @Then("the new planet should be added to the database")
    public void the_new_planet_should_be_added_to_the_database() {
        // Write code here that turns the phrase above into concrete actions
        TestRunner.wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//tr"), 6));
        Assert.assertEquals(5, TestRunner.homePage.getNumberOfCelestialRows());
    }

    @When("the user provides an image of the planet {string}")
    public void the_user_provides_an_image_of_the_planet(String path) {
        // Write code here that turns the phrase above into concrete actions
        TestRunner.homePage.setPlanetImageInput(path);
    }

    @Then("the user should remain on the home page")
    public void the_user_should_remain_on_the_home_page() {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertEquals("Home", TestRunner.driver.getTitle());
    }

    @When("the user provides an image of the moon {string}")
    public void the_user_provides_an_image_of_the_moon(String path) {
        // Write code here that turns the phrase above into concrete actions
        TestRunner.homePage.setMoonImageInput(path);
    }

    @When("the user provides a moon name {string}")
    public void the_user_provides_a_moon_name(String moon_name) {
        // Write code here that turns the phrase above into concrete actions
        TestRunner.homePage.setMoonNameInput(moon_name);
    }

    @When("the user provides a planet id {string}")
    public void the_user_provides_a_planet_id(String id) {
        // Write code here that turns the phrase above into concrete actions
        TestRunner.homePage.setOrbitedPlanetInput(id);
    }

    @When("the user provides a planet name {string}")
    public void the_user_provides_a_planet_name(String planet_name) {
        // Write code here that turns the phrase above into concrete actions
        TestRunner.homePage.setPlanetNameInput(planet_name);
    }

    @And("the user has moon toggled from dropdown")
    public void theUserHasMoonToggledFromDropdown() {
        TestRunner.homePage.select_moon();
    }

    @And("the user has planet toggled from dropdown")
    public void theUserHasPlanetToggledFromDropdown() {
        TestRunner.homePage.select_planet();
    }

    @And("the user presses the submit button_with_image")
    public void theUserPressesTheSubmitButton_with_image() {
        TestRunner.homePage.click_submit_button();
    }

    /*
    When we try to add an image without the correct format, we do not get an alert. This is another defect.
     */
}
