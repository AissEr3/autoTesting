package pages.admin.usermanage;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.admin.usermanage.subPage.AddUserPage;

public class UserManageHomePage {
    private WebDriver driver;

    @FindBy(xpath = "//*[@id=\"null\"]/span/span")
    private WebElement addUserButton;

    public UserManageHomePage(WebDriver fatherPageDriver){
        driver = fatherPageDriver;
        switchFrame();
        PageFactory.initElements(driver,this);
    }

    public void switchFrame(){
        driver.switchTo().frame(driver.findElement(By.xpath("//*[@id=\"content-main\"]/iframe[2]")));
    }

    public AddUserPage clickAddUserButton(){
        addUserButton.click();
        return new AddUserPage(driver);
    }
}