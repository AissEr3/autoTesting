package pages.admin.usermanage.subpage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.admin.usermanage.base.BasePage;

public class EnableUserPage extends BasePage {
    @FindBy(xpath = "/html/body/div[1]/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td[2]/div")
    private WebElement hintInformation;

    public EnableUserPage(WebDriver fatherPageDriver){
        driver = fatherPageDriver;
        driver.switchTo().defaultContent();
        PageFactory.initElements(driver,this);
    }

    public WebElement getHintInformation() {
        return hintInformation;
    }
}
