package pages.admin;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.LoginPage;
import pages.admin.usermanage.UserManageHomePage;
import util.LoginUtil;

public class AdminHomePage {
    private WebDriver driver;

    @CacheLookup
    @FindBy(linkText = "用户管理")
    private WebElement userManager;

    @CacheLookup
    @FindBy(linkText = "组织机构")
    private WebElement institutionalFramework;

    public AdminHomePage(){
        driver = BaseTest.getDriver();
        if(!driver.getCurrentUrl().contains("loginController.do?login")){
            throw new IllegalStateException("this page is not home page");
        }
        // 显示出下拉框
        driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[2]/a")).click();
        PageFactory.initElements(driver,this);
    }

    public UserManageHomePage openUserManager(){
        userManager.click();
        return new UserManageHomePage(driver);
    }

    public void openInstitutionalFramework(){
        institutionalFramework.click();
    }
}
