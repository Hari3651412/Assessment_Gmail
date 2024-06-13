package tddApproachGmailTestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import pageObjects.ComposeEmailObjects;
import pageObjects.LoginEmailObjects;

import java.time.Duration;

public class ComposeEmail {

    WebDriver driver;
    LoginEmailObjects loginEmailObjects;
    ComposeEmailObjects emailObjects;

    @BeforeSuite
    public void killBrowsers()
    {
        try
        {
            Runtime.getRuntime().exec("TASKKILL /F /IM chromedriver.exe /T");
            Runtime.getRuntime().exec("TASKKILL /F /IM chrome.exe /T");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @BeforeClass
    public void setUpDriver()
    {
        driver=new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(300));

        loginEmailObjects= PageFactory.initElements(driver, LoginEmailObjects.class);
        emailObjects= PageFactory.initElements(driver, ComposeEmailObjects.class);
    }

    @BeforeMethod
    public void launchApp()
    {
        if(driver.getCurrentUrl().contains("data"))
        driver.get("https://www.gmail.com");
    }

    @Test(description = "Login to the Gmail Application",priority = 1)
    public void loginToTheApplication()
    {
        loginEmailObjects.enterEmail("harisandanay");
        loginEmailObjects.clickOnNext();

        loginEmailObjects.enterPassword("15ne1a05b8");
        loginEmailObjects.clickOnNext();

        if(driver.findElement(By.tagName("body")).getText().contains("With passkeys you can now use your fingerprint, face, or screen lock to verify it’s really you."))
        loginEmailObjects.clickOnNotNow();
    }

    @Test(description = "Positive Test Case",priority = 2,dependsOnMethods = "loginToTheApplication")
    public void composeEmail()
    {
        emailObjects.clickOnCompose();

        emailObjects.enterTo("harisandana123@gmail.com");

        emailObjects.enterSubject("Incubyte");

        emailObjects.enterBody("Automation QA test for Incubyte");

        emailObjects.clickOnInsertEmoji();
        emailObjects.selectEmoji();
        emailObjects.clickOnInsertEmoji();

        emailObjects.clickOnSend();
        emailObjects.clickOnSentMessages();
    }


    @Test(description = "Negative Test Case -> Subject",priority = 3,dependsOnMethods = "loginToTheApplication")
    public void composeEmail_NegativeSubject()
    {
        emailObjects.clickOnCompose();

        emailObjects.enterBody("Automation QA test for Incubyte");

        emailObjects.clickOnSend();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if(driver.findElement(By.tagName("body")).getText().contains("Please specify at least one recipient."))
        {
            emailObjects.clickOnOK();
        }

        emailObjects.clickOnCloseButton();
    }


    @Test(description = "Negative Test Case -> Body",priority = 4,dependsOnMethods = "loginToTheApplication")
    public void composeEmail_Negativ()
    {
        emailObjects.clickOnCompose();

        emailObjects.enterTo("hari.com");

        emailObjects.enterBody("Automation QA test for Incubyte");

        emailObjects.clickOnSend();


        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if(driver.findElement(By.tagName("body")).getText().contains(" Please make sure that all addresses are properly formed."))
        {
            emailObjects.clickOnOK();
        }

        emailObjects.clickOnCloseButton();
    }

}
