package com.revature.Steps;

import com.revature.TestRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class HomePage {
    private boolean isTableValid;

    private List<List<String>> buildValidData(){
        List<List<String>> validData = new ArrayList<>();
        validData.add(List.of("planet", "1", "Earth", "1"));
        validData.add(List.of("planet", "2", "Mars", "1"));
        validData.add(List.of("moon", "2", "Luna", "1"));
        validData.add(List.of("moon", "3", "Titan", "2"));
        return validData;
    }

    private List<List<String>> buildInvalidData(){
        List<List<String>> invalidData = new ArrayList<>();
        invalidData.add(List.of("planet", "1", "Earth", "1"));
        invalidData.add(List.of("planet", "2", "Mars", "1"));
        invalidData.add(List.of("moon", "2", "Luna", "1"));
        return invalidData;
    }

    @Given("the celestial objects table has valid data")
    public void the_celestial_objects_table_has_valid_data() {
        // Write code here that turns the phrase above into concrete actions
        List<WebElement> tableRows = TestRunner.homePage.getTableRows();
        int rowCount = tableRows.size();
        List<List<String>> testData = buildValidData();
        for(int i = 1; i < rowCount; i++){
            List<WebElement> columm = tableRows.get(i).findElements(By.tagName("td"));
            for(WebElement cell : columm){
                if(!cell.getText().equals(testData.get(i-1).get(i-1))){
                    Assert.fail();
                }
            }
        }
        Assert.assertTrue(true);
    }



    @Given("the celestial objects table has invalid data")
    public void the_celestial_objects_table_has_invalid_data() {
        // Write code here that turns the phrase above into concrete actions
        // Write code here that turns the phrase above into concrete actions
        List<WebElement> tableRows = TestRunner.homePage.getTableRows();
        int rowCount = tableRows.size();
        List<List<String>> testData = buildInvalidData();
        for(int i = 1; i < rowCount; i++){
            List<WebElement> columm = tableRows.get(i).findElements(By.tagName("td"));
            for(WebElement cell : columm){
                if(!cell.getText().equals(testData.get(i-1).get(i-1))){
                    Assert.fail();
                }
            }
        }
        Assert.assertTrue(true);
    }

}
