package bddApproachGmailTestCases;

import framework.PropertiesUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import pageObjects.ComposeEmailObjects;
import pageObjects.LoginEmailObjects;

import java.time.Duration;

public class ComposeEmailStepDefs {

    ComposeEmailObjects composeEmailObjects;
    LoginEmailObjects loginEmailObjects;
    WebDriver driver;
    PropertiesUtil propertiesUtil;

    @Given("I am on the Gmail page")
    public void i_am_on_the_gmail_page() {

        driver=new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(300));
        driver.get("https://www.gmail.com");

        composeEmailObjects= PageFactory.initElements(driver, ComposeEmailObjects.class);
        loginEmailObjects= PageFactory.initElements(driver, LoginEmailObjects.class);
        propertiesUtil=new PropertiesUtil();
    }


    @And("I enter the username")
    public void iEnterTheUsername() {
        loginEmailObjects.enterEmail(propertiesUtil.getEmailID());
        Assert.assertEquals(loginEmailObjects.getEmail(),propertiesUtil.getEmailID());
        loginEmailObjects.clickOnNext();
    }

    @And("I enter the password")
    public void iEnterThePassword() {
        loginEmailObjects.enterPassword(propertiesUtil.getPassword());
        Assert.assertEquals(loginEmailObjects.getPassword(),propertiesUtil.getPassword());
        loginEmailObjects.clickOnNext();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if(driver.findElement(By.tagName("body")).getText().contains("Verify it’s you"))
            throw new RuntimeException("Issue ---> Asking the end user to verify the phone number which cannot be automated");
    }

    @And("I click on the Compose button")
    public void i_click_on_the_compose_button() {

        composeEmailObjects.clickOnCompose();
    }

    @And("I enter the email address")
    public void i_enter_the_email_address() {
        composeEmailObjects.enterTo("harisandana123@gmail.com");
    }

    @And("I enter the invalid email address")
    public void i_enter_the_invalid_email_address() {
        composeEmailObjects.enterTo("harisandana123@.com");
    }

    @And("I enter the subject")
    public void enterSubject()
    {
        composeEmailObjects.enterSubject("Test Email");
    }

    @And("I enter the body of the email")
    public void enterBody()
    {
        composeEmailObjects.enterBody("This is a test email");
    }

    @And("I click on the send button")
    public void clickOnSend()
    {
        composeEmailObjects.clickOnSend();
    }

    @And("the email should be sent successfully")
    public void emailSentSuccessfully()
    {
        composeEmailObjects.clickOnSentMessages();
    }

}

