package com.revature.POMs;

import com.revature.Steps.Register;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPom {
    private WebDriver driver = null;

    public RegisterPom(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Input field to for the username
    @FindBy(id = "usernameInput")
    private WebElement usernameField;
    //Input field for the password
    @FindBy(id = "passwordInput")
    private WebElement passwordField;
    @FindBy(xpath = "//input[@type='submit']")
    private WebElement registerButton;
    @FindBy(linkText = "login")
    private WebElement LoginLink;

    public String getAlertText(){
        try {
            //Try to get the text from an alert if present
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            alert.accept();
            return alertText;
        }
        catch (NoAlertPresentException e){
            //If no alert is present then print the error message to the console and return error message
            System.out.println("No alert present ");
            return "No alert present";
        }
    }

    public void clickLoginLink(){
        LoginLink.click();
    }

    public void goToRegisterPage() {
        driver.get("http://localhost:8080/register");
    }

    public void setUserName(String username){
        usernameField.sendKeys(username);
    }

    public void setPassword(String password){
        passwordField.sendKeys(password);
    }

    public void register() {
        registerButton.click();
    }
}
