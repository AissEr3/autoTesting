package pages.admin.usermanage.subpage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.admin.usermanage.base.UserPage;

public class EditUserPage extends UserPage {
    @FindBy(xpath = "//*[@id=\"formobj\"]/table/tbody/tr[4]/td[2]/a[1]/span/span")
    private WebElement selectRoleButton;
    @FindBy(xpath = "//*[@id=\"formobj\"]/table/tbody/tr[4]/td[2]/a[2]/span/span")
    private WebElement cleanRoleButton;

    public EditUserPage(WebDriver fatherPageDriver){
        super(fatherPageDriver);
        PageFactory.initElements(driver,this);
        initWebElement();
    }

    @Override
    protected void initWebElement() {
        realName    = inputList.get(0);
        cellPhone   = inputList.get(5);
        officePhone = inputList.get(6);
        email       = inputList.get(7);
    }

    @Override
    public UserPage setRealName(String realName) {
        this.realName.clear();
        return super.setRealName(realName);
    }

    @Override
    public UserPage setCellPhone(String cellPhone) {
        this.cellPhone.clear();
        return super.setCellPhone(cellPhone);
    }

    @Override
    public UserPage setOfficePhone(String officePhone) {
        this.officePhone.clear();
        return super.setOfficePhone(officePhone);
    }

    @Override
    public UserPage setEmail(String email) {
        this.email.clear();
        return super.setEmail(email);
    }

    @Override
    public SelectOrganizationPage clickSelectOrgButton() {
        cleanOrgButton.click();
        return super.clickSelectOrgButton();
    }

    @Override
    public SelectRolePage clickCheckRoleButton() {
        cleanRoleButton.click();
        selectRoleButton.click();
        return new SelectRolePage(driver);
    }
}
