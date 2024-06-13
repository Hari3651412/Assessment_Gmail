package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ComposeEmailObjects {

    @FindBy(xpath = "//div[text()='Compose']")
    private WebElement btn_Compose;

    @FindBy(xpath = "//div[text()='Compose']")
    private List<WebElement> btn_Compose_Presence;

    @FindBy(xpath = "//span[@data-tooltip='Select contacts']/parent::div/parent::td/following-sibling::td/descendant::input")
    private WebElement txt_To;

    @FindBy(xpath = "//input[@name='subjectbox']")
    private WebElement txt_Subject;

    @FindBy(xpath = "//div[@aria-label='Message Body']")
    private WebElement txt_Body;

    @FindBy(xpath = "//div[text()='Send']")
    private WebElement btn_Send;

    @FindBy(xpath = "//div[contains(@aria-label,'Insert emoji')]")
    private WebElement btn_InsertEmoji;

    @FindBy(xpath = "//div[text()='RECENTLY USED']/../following-sibling::div/descendant::img")
    private List<WebElement> img_Emoji;

    @FindBy(xpath = "//div[contains(@data-tooltip,'Discard')]")
    private WebElement btn_Discard;

    @FindBy(xpath = "//span[contains(@aria-label,'Add Cc') and text()='Cc']")
    private WebElement btn_Cc;

    @FindBy(xpath = "//span[contains(@aria-label,'Add Bcc') and text()='Bcc']")
    private WebElement btn_Bcc;

    @FindBy(xpath = "//span[text()='OK']/..")
    private WebElement btn_OK;

    @FindBy(xpath = "//div[@data-tooltip='Sent']/descendant::div/span")
    private WebElement tab_SentMessages;

    @FindBy(xpath = "//img[@data-tooltip='Save & close']")
    private WebElement btn_SaveAndClose;

    /*************************************************************************************************************************************/

    public void clickOnCompose()
    {
        while(btn_Compose_Presence.size()==0)
        {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        btn_Compose.click();
    }

    public void enterTo(String to)
    {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        txt_To.sendKeys(to);
    }

    public void enterSubject(String subject)
    {
        txt_Subject.sendKeys(subject);
    }

    public void enterBody(String body)
    {
        txt_Body.sendKeys(body);
    }

    public void clickOnSend()
    {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        btn_Send.click();
    }

    public void clickOnInsertEmoji()
    {
        btn_InsertEmoji.click();
    }

    public void selectEmoji()
    {
        img_Emoji.stream().findAny().get().click();
    }

    public void clickOnDiscard()
    {
        btn_Discard.click();
    }

    public void clickOnCloseButton()
    {
        btn_SaveAndClose.click();
    }

    public void clickOnCc()
    {
        btn_Cc.click();
    }

    public void clickOnBcc()
    {
        btn_Bcc.click();
    }

    public void clickOnOK()
    {
        btn_OK.click();
    }

    public void clickOnSentMessages()
    {
        tab_SentMessages.click();
    }
}
