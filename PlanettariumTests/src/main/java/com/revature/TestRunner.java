package com.revature;
import com.revature.POMs.HomePagePom;
import com.revature.POMs.LoginPom;
import com.revature.POMs.RegisterPom;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features/HomePage.feature",
        glue = "com.revature.Steps",
        plugin = {"pretty"}

)
public class TestRunner {

    public static WebDriver driver = null;
    public static WebDriverWait wait = null;
    public static LoginPom loginPage;
    public static RegisterPom registerPage;
    public static HomePagePom homePage;

    @BeforeClass
    public static void setup(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        homePage = new HomePagePom(driver);
        loginPage = new LoginPom(driver);
        registerPage = new RegisterPom(driver);
    }

    @AfterClass
    public static void teardown(){
        if(driver != null) {
            driver.quit();
        }
    }
}
