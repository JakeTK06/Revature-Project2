package com.revature;

import com.revature.planetarium.Main;
import com.revature.poms.HomePage;
import com.revature.poms.LoginPage;
import com.revature.poms.RegistrationPage;
import com.revature.steps.UserRegistrationSteps;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features",
        glue = "com.revature.steps",
        plugin = {"pretty","html:src/test/resources/reports/html-report.html","json:src/test/resources/reports/json-report.json"}
)
public class TestRunner {

    public static WebDriver driver = null;
    public static WebDriverWait wait;
    public static HomePage homePage;
    public static LoginPage loginPage;
    public static RegistrationPage registrationPage;

    @BeforeClass
    public static void setup(){
//        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.addArguments("--headless");
        driver = new ChromeDriver(/*chromeOptions*/);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);
    }

    @AfterClass
    public static void tearDown(){
        if(driver != null){
            driver.quit();
        }
    }
}
