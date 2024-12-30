package com.revature.poms;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {
    WebDriver driver;

    @FindBy(id = "usernameInput")
    private WebElement usernameInput;

    @FindBy(id = "passwordInput")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[@value='Create']")
    private WebElement createAccountButton;

    public RegistrationPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void provide_username(String username) {
        usernameInput.sendKeys(username);
    }

    public void provide_password(String password) {
        passwordInput.sendKeys(password);
    }

    public void create_new_account() {
        createAccountButton.submit();
    }

    public String get_passwordInput_type(){
        return passwordInput.getAttribute("type");
    }

    public void setUpRegisteredUser(){
        usernameInput.sendKeys("Robert_Downey-Junior1");
        passwordInput.sendKeys("Iron_Man-2");
        createAccountButton.submit();
    }
}
