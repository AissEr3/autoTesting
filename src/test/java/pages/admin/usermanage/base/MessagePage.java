package pages.admin.usermanage.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MessagePage extends BasePage{
    @FindBy(xpath = "/html/body/div[1]/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td[2]/div")
    protected WebElement hintInformation;

    public MessagePage(WebDriver fatherPageDriver){
        driver = fatherPageDriver;
        driver.switchTo().defaultContent();
        PageFactory.initElements(driver,this);
    }

    public WebElement getHintInformation() {
        return hintInformation;
    }
}
