package com.revature.steps;

import com.revature.TestRunner;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SharedSteps {

    // User Registration
    // User Login
    // Delete
    // Add
    @Then("the user should get a browser alert saying {string}")
    public void the_user_should_get_a_browser_alert_saying(String expectedMessage) {
        try {
            TestRunner.wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = TestRunner.driver.switchTo().alert();
            String alert_text = alert.getText();
            System.out.println(alert_text);
            alert.accept();
            Assert.assertEquals(expectedMessage, alert_text);
        } catch (NoAlertPresentException e) {
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
