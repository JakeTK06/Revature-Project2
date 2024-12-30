package com.revature.poms;

import com.revature.TestRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class HomePage {
    private WebDriver driver;

    @FindBy(id = "greeting")
    private WebElement greetingHeader;

    @FindBy(id = "logoutButton")
    private WebElement logoutButton;

    @FindBy(xpath = "//tr")
    private List<WebElement> tableRows;

    @FindBy(id = "locationSelect")
    private WebElement select;

    @FindBy(id = "moonNameInput")
    private WebElement moonNameInput;

    @FindBy(id = "orbitedPlanetInput")
    private WebElement orbitedPlanetInput;

    @FindBy(id = "deleteInput")
    private WebElement deleteInput;

    @FindBy(id = "deleteButton")
    private WebElement deleteButton;

    @FindBy(id = "planetNameInput")
    private WebElement planetNameInput;

    @FindBy(id = "planetImageInput")
    private WebElement planetImageInput;

    @FindBy(id = "moonImageInput")
    private WebElement moonImageInput;

    @FindBy(xpath = "html/body/div[1]/div[2]/button")
    private WebElement submitButton;


    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getHomePageGreeting(){
        return greetingHeader.getText();
    }

    public int getNumberOfCelestialRows(){
        return tableRows.size() - 1;
    }

    public void tryToAccessHomePageDirectly(){
        driver.get("http://localhost:8080/planetarium");
    }

    public void logout(){
        logoutButton.click();
    }

    public void select_planet() {
        Select dropdown = new Select(select);
        dropdown.selectByValue("planet");
    }

    public void select_moon() {
        Select dropdown = new Select(select);
        dropdown.selectByValue("moon");
    }

    public void setDeleteInput(String celestial_body_name) {
        deleteInput.sendKeys(celestial_body_name);
    }

    public void setMoonNameInput(String moonName){
        moonNameInput.sendKeys(moonName);
    }

    public void setOrbitedPlanetInput(String orbitedPlanetId){
        orbitedPlanetInput.sendKeys(orbitedPlanetId);
    }

    public void setPlanetNameInput(String planetName){
        planetNameInput.sendKeys(planetName);
    }

    public void setMoonImageInput(String path){
        moonImageInput.sendKeys(path);

    }

    public void setPlanetImageInput(String path){
        planetImageInput.sendKeys(path);
    }


    public void click_delete_button(){
        deleteButton.click();
    }

    public void click_submit_button(){
        TestRunner.wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        submitButton.click();
    }


}
