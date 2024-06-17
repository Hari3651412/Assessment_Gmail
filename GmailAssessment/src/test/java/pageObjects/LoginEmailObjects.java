package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class LoginEmailObjects {

    @FindBy(xpath = "//input[@aria-label='Email or phone']")
    private WebElement txt_Email;

    @FindBy(xpath = "//span[text()='Next']/..")
    private WebElement btn_Next;

    @FindBy(xpath = "//input[@aria-label='Enter your password']")
    private WebElement txt_Password;

    @FindBy(xpath = "//input[@aria-label='Enter your password']")
    private List<WebElement> txt_Password_Presence;

    @FindBy(xpath = "//span[text()='Not now']")
    private WebElement btn_NotNow;

    /***********************************************************************************************************************************************/

    public void enterEmail(String email)
    {
        txt_Email.sendKeys(email);
    }

    public String getEmail()
    {
        return txt_Email.getAttribute("value");
    }

    public void clickOnNext()
    {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        btn_Next.click();
    }

    public void enterPassword(String password)
    {
        while(txt_Password_Presence.size()==0)
        {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        txt_Password.sendKeys(password);
    }


    public String getPassword()
    {
        return txt_Password.getAttribute("value");
    }

    public void clickOnNotNow()
    {
        btn_NotNow.click();
    }

}
