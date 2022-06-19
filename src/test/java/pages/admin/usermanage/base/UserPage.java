package pages.admin.usermanage.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import pages.admin.usermanage.subpage.SelectOrganizationPage;
import pages.admin.usermanage.subpage.SelectRolePage;

import java.util.List;

public abstract class UserPage extends BasePage{
    @CacheLookup
    @FindBy(xpath = "//*[@id=\"formobj\"]/table/tbody//input")
    protected List<WebElement> inputList;

    protected WebElement realName;

    @FindBy(xpath = "//*[@id=\"departSearch\"]/span/span")
    protected WebElement selectOrgButton;
    @FindBy(xpath = "//*[@id=\"departRedo\"]/span/span")
    protected WebElement cleanOrgButton;

    @FindBy(xpath = "//*[@id=\"formobj\"]/table/tbody/tr[6]/td[2]/a[1]/span/span")
    protected WebElement selectRoleButton;
    @FindBy(xpath = "//*[@id=\"formobj\"]/table/tbody/tr[6]/td[2]/a[2]/span/span")
    protected WebElement cleanRoleButton;

    protected WebElement cellPhone;
    protected WebElement officePhone;
    protected WebElement email;

    protected abstract void initWebElement();

    public UserPage(WebDriver fatherPageDriver){
        driver = fatherPageDriver;
        driver.switchTo().defaultContent();
        switchFrame();
    }

    public void switchFrame(){
        WebElement frame = driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td[2]/div/iframe"));
        driver.switchTo().frame(frame);
    }

    public SelectOrganizationPage clickSelectOrgButton(){
        selectOrgButton.click();
        return new SelectOrganizationPage(driver);
    }

    public SelectRolePage clickCheckRoleButton(){
        selectRoleButton.click();
        return new SelectRolePage(driver);
    }

    public UserPage setRealName(String realName) {
        this.realName.sendKeys(realName);
        return this;
    }

    public UserPage setCellPhone(String cellPhone) {
        this.cellPhone.sendKeys(cellPhone);
        return this;
    }

    public UserPage setOfficePhone(String officePhone) {
        this.officePhone.sendKeys(officePhone);
        return this;
    }

    public UserPage setEmail(String email) {
        this.email.sendKeys(email);
        return this;
    }

    public WebElement getRealName() {
        return realName;
    }

    public WebElement getCellPhone() {
        return cellPhone;
    }

    public WebElement getOfficePhone() {
        return officePhone;
    }

    public WebElement getEmail() {
        return email;
    }
}
