package com.revature.POMs;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPom {

    private WebDriver driver = null;

    public LoginPom(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Input field for the username
    @FindBy(id = "usernameInput")
    private WebElement usernameField;
    //input Field for the password
    @FindBy(id = "passwordInput")
    private WebElement passwordField;
    //Button to submit credentials to server
    @FindBy(xpath = "//input[@type='submit']")
    private WebElement loginButton;
    //Link to the register page
    @FindBy(linkText = "Create an Account")
    private WebElement registerLink;

    public void goToLoginPage() {
        driver.get("http://localhost:8080/");
    }

    public void clickRegisterLink(){
        registerLink.click();
    }

    public void setUserName(String username){
        usernameField.sendKeys(username);
    }

    public void setPassword(String password){
        passwordField.sendKeys(password);
    }

    public void login() {
        loginButton.click();
    }

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
}
