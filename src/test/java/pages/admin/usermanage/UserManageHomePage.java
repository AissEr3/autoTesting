package pages.admin.usermanage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.admin.usermanage.subpage.*;

import java.util.List;

public class UserManageHomePage {
    private WebDriver driver;
    private SelectUserBy selectUserBy;

    @FindBy(xpath = "//*[@id=\"null\"]/span/span")
    private List<WebElement> buttons;

    private WebElement addUserButton;
    private WebElement editUserButton;
    private WebElement resetPasswordButton;
    private WebElement disableUserButton;
    private WebElement enableUserButton;

    // 定位到选中的删除按钮
    private WebElement locateSelectUserDelete;

    public UserManageHomePage(WebDriver fatherPageDriver){
        driver = fatherPageDriver;
        switchFrame();
        PageFactory.initElements(driver,this);
        selectUserBy = new SelectUserBy();
        initWebElement();
    }

    public void initWebElement(){
        addUserButton       = buttons.get(0);
        editUserButton      = buttons.get(1);
        resetPasswordButton = buttons.get(2);
        disableUserButton   = buttons.get(3);
        enableUserButton    = buttons.get(4);
    }

    public void switchFrame(){
        driver.switchTo().frame(driver.findElement(By.xpath("//*[@id=\"content-main\"]/iframe[2]")));
    }

    public AddUserPage clickAddUserButton() {
        addUserButton.click();
        return new AddUserPage(driver);
    }

    public EditUserPage clickEditUserButton(){
        editUserButton.click();
        return new EditUserPage(driver);
    }

    public ResetPasswordPage clickResetPasswordButton(){
        resetPasswordButton.click();
        return new ResetPasswordPage(driver);
    }

    public DisableUserPage clickDisableUserButton(){
        disableUserButton.click();
        return new DisableUserPage(driver);
    }

    public EnableUserPage clickEnableUserButton(){
        enableUserButton.click();
        return new EnableUserPage(driver);
    }

    public DeleteUserPage clickDeleteOperate(){
        /*
            xpath = //tr[contains(@class,"datagrid-row-selected")]/td[@field="opt"]/div/a
            如果是选中行，其class样式包含：datagrid-row-selected
            通过这个定位到行，然后再定位到这行的删除按钮
         */
        locateSelectUserDelete = driver.findElement(By.xpath("//tr[contains(@class,\"datagrid-row-selected\")]/td[@field=\"opt\"]/div/a"));
        locateSelectUserDelete.click();
        return new DeleteUserPage(driver);
    }

    public SelectUserBy selectUser(){
        return this.selectUserBy;
    }

    public class SelectUserBy {
        @FindBy(xpath = "//td[@field=\"userName\"]")
        private List<WebElement> usernameList;

        @FindBy(xpath = "//td[@field=\"realName\"]/div")
        private List<WebElement> realNameList;

        @FindBy(xpath = "//tr[contains(@id,\"row-r1-1\")]")
        private List<WebElement> rowList;

        public SelectUserBy() {
            PageFactory.initElements(driver,this);
        }

        public UserManageHomePage byUserName(String username){
            for(WebElement element : usernameList){
                if(element.getText().equals(username)){
                    element.click();
                    return UserManageHomePage.this;
                }
            }
            return null;
        }

        public UserManageHomePage byRealName(String realName){
            for(WebElement element : realNameList){
                if(element.getText().equals(realName)){
                    element.click();
                    return UserManageHomePage.this;
                }
            }
            return null;
        }

        public UserManageHomePage byRow(int row){
            if(row > rowList.size() || row <= 0){
                return null;
            }
            rowList.get(row-1);
            return UserManageHomePage.this;
        }
    }
}