package com.revature.Steps;

import com.revature.TestRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class DeletePlanetsAndMoons {
    @Given("the user selects the planet option")
    public void the_user_selects_the_planet_option() {
        TestRunner.homePage.selectType("Planet");
    }

    @Given("the user enters valid planet name {string}")
    public void the_user_enters_valid_planet_name(String name) {
        // Write code here that turns the phrase above into concrete actions
        TestRunner.homePage.enterDeletePlanetName(name);
    }

    @Then("the user selects the moon option")
    public void the_user_selects_the_moon_option() {
        // Write code here that turns the phrase above into concrete actions
        TestRunner.homePage.selectType("Moon");
    }

    @Given("the user clicks the delete planet button")
    public void the_user_clicks_the_delete_planet_button() {
        // Write code here that turns the phrase above into concrete actions
        TestRunner.homePage.DeleteCelestial();
    }

    @Given("the moons owned by the planet are deleted {string}")
    public void the_moons_owned_by_the_planet_are_deleted(String id) {
        // Write code here that turns the phrase above into concrete actions
        List<WebElement> tableRows = TestRunner.homePage.getTableRows();
        int rowCount = tableRows.size();
        for(int i = 1; i < rowCount; i++){
            List<WebElement> column = tableRows.get(i).findElements(By.tagName("td"));
            if(column.get(0).getText().equals("moon") && column.get(3).getText().equals(id)){
                Assert.fail();
            }
        }
    }


    @Given("the user enters valid moon name {string}")
    public void the_user_enters_valid_moon_name(String name) {
        // Write code here that turns the phrase above into concrete actions
        TestRunner.homePage.enterDeleteMoonName(name);
    }

    @Then("the planet table is refreshed {string}")
    public void the_planet_table_is_refreshed(String name) {
        // Write code here that turns the phrase above into concrete actions
        List<WebElement> tableRows = TestRunner.homePage.getTableRows();
        int rowCount = tableRows.size();
        for(int i = 1; i < rowCount; i++){
            List<WebElement> column = tableRows.get(i).findElements(By.tagName("td"));
            if(column.get(2).getText().equals(name)){
                Assert.fail();
            }
        }
        Assert.assertTrue(true);
    }

    @Given("the user enters planet name {string}")
    public void the_user_enters_planet_name(String name) {
        // Write code here that turns the phrase above into concrete actions
        TestRunner.homePage.enterDeletePlanetName(name);
    }

    @Then("browser gives invalid planet error")
    public void browser_gives_invalid_planet_error() {
        // Write code here that turns the phrase above into concrete actions
        TestRunner.wait.until(ExpectedConditions.alertIsPresent());
        String alertText = TestRunner.driver.switchTo().alert().getText();
        TestRunner.driver.switchTo().alert().accept();
        Assert.assertEquals("Invalid planet name", alertText);
    }

    @Given("the user enters the moon name {string}")
    public void the_user_enters_the_moon_name(String name) {
        // Write code here that turns the phrase above into concrete actions
        TestRunner.homePage.enterDeleteMoonName(name);
    }

    @Given("the user clicks the delete moon button")
    public void the_user_clicks_the_delete_moon_button() {
        // Write code here that turns the phrase above into concrete actions
        TestRunner.homePage.DeleteCelestial();
    }

    @Then("browser gives invalid Moon error")
    public void browser_gives_invalid_Moon_error() {
        TestRunner.wait.until(ExpectedConditions.alertIsPresent());
        String alertText = TestRunner.driver.switchTo().alert().getText();
        TestRunner.driver.switchTo().alert().accept();
        Assert.assertEquals("Invalid moon name", alertText);
    }
}
