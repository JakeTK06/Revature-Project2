package com.revature.poms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private WebDriver driver;

    @FindBy(id = "usernameInput")
    private WebElement usernameInput;

    @FindBy(id = "passwordInput")
    private WebElement passwordInput;

    @FindBy(tagName = "input")
    private WebElement loginButton;

    @FindBy(xpath = "html/body/div/form/a")
    private WebElement createAccountLink;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setUpLoggedInUser(){
        driver.get("http://localhost:8080/");
        usernameInput.sendKeys("Batman");
        passwordInput.sendKeys("Iamthenight1939");
        loginButton.submit();
    }

    public void loginInWithNewUser(){
        usernameInput.sendKeys("Robert_Downey-Junior1");
        passwordInput.sendKeys("Iron_Man-2");
        loginButton.submit();
    }

    public void openLoginPage(){
        driver.get("http://localhost:8080/");
    }

    public void openRegistrationPage(){
        createAccountLink.click();
    }

    public void login(){
        loginButton.submit();
    }

    public String get_passwordInput_type(){
        return passwordInput.getAttribute("type");
    }
}
