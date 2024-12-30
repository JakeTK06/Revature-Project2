package com.revature.POMs;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class HomePagePom {

    private WebDriver driver = null;

    @FindBy(tagName = "Select")
    private WebElement celestialSelect;
    @FindBy(id = "greeting")
    private WebElement greetingHeader;
    @FindBy(id = "logoutButton")
    private WebElement logoutButton;
    @FindBy(tagName = "tr")
    private List<WebElement> tableRows;
    @FindBy(id = "deleteInput")
    private WebElement deleteInput;
    @FindBy(id = "deleteButton")
    private WebElement deleteButton;

    //Add new moon elements start here
    @FindBy(id = "moonNameInput")
    private WebElement moonNameInput;
    @FindBy(id = "orbitedPlanetInput")
    private WebElement orbitedPlanetInput;
    @FindBy(id = "moonImageInput")
    private WebElement moonImageInput;
    //Add new moon elements ends here

    //Add new planet elements start here
    @FindBy(id = "planetNameInput")
    private WebElement planetNameInput;
    @FindBy(id="planetImageInput")
    private WebElement planetImageInput;
    //Add new planet elements end here

    @FindBy(className = "submit-button")
    private WebElement submitButton;

    private Select select;

    public HomePagePom(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public List<WebElement> getTableRows(){
        return tableRows;
    }

    public void enterPlanetImg(String path){
        planetImageInput.sendKeys(path);
    }

    public void enterMoonImg(String path){
        moonImageInput.sendKeys(path);
    }

    public void selectType(String type){
        WebElement planetSelect = driver.findElement(By.tagName("select"));
        Select select = new Select(planetSelect);
        if(type.equals("Planet")){
            select.selectByIndex(1);
        }else{
            select.selectByIndex(0);
        }
    }

    public void enterMoonOrbitedPlanet(String planetid){
        orbitedPlanetInput.sendKeys(planetid);
    }

    public String getHomePageGreeting(){
        return greetingHeader.getText();
    }

    public void clickSubmitButton(){
        submitButton.click();
    }

    public int getNumberOfCelestialRows(){
        return tableRows.size()-1;
    }

    public void tryToAccessHomePageDirectly(){
        driver.get("http://localhost:8080/planetarium");
    }

    public void logout(){
        logoutButton.click();
    }

    public void enterPlanetName(String planetName){
        planetNameInput.sendKeys(planetName);
    }

    public void enterMoonName(String moonName){
        moonNameInput.sendKeys(moonName);
    }

    public void enterDeleteMoonName(String moonName){
        deleteInput.sendKeys(moonName);
    }

    public void enterDeletePlanetName(String planetName){
        deleteInput.sendKeys(planetName);
    }

    public void DeleteCelestial(){
        deleteButton.click();
    }
}
