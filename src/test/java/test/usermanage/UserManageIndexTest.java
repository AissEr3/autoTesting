package test.usermanage;

import base.BaseTest;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.LoginPage;
import pages.admin.AdminHomePage;
import pages.admin.usermanage.UserManageHomePage;
import pages.admin.usermanage.subPage.AddUserPage;
import util.LoginUtil;

import static org.junit.jupiter.api.Assertions.fail;

public class UserManageIndexTest extends BaseTest {
    private LoginPage login;
    private UserManageHomePage userManage;
    private AddUserPage addUser;

    @Nested
    class OpenUserManage{
        @BeforeEach
        public void setUp(){
            driver.get(url);
            login = new LoginPage();
            LoginUtil.login(login,"admin","123456");
        }

        @Test
        public void openUserManageTest(){
            userManage = new AdminHomePage().openUserManager();
            try{
                driverWait.until(ExpectedConditions.textToBe(By.xpath("/html/body/div[2]/div[1]/div[1]"),"用户管理"));
            }catch(TimeoutException e){
                fail("doesn't open");
            }
        }

        @Nested
        class OpenSubPage{
            @BeforeEach
            public void setUpPage(){
                driver.get(url);
                login = new LoginPage();
                LoginUtil.login(login,"admin","123456");
                userManage = new AdminHomePage().openUserManager();
            }

            @Test
            public void openAddUserTest(){
                addUser = userManage.clickAddUserButton();
                driver.switchTo().defaultContent();
                try{
                    driverWait.until(ExpectedConditions.textToBe(By.xpath("/html/body/div[2]/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td/div/div[1]"),"用户录入"));
                }catch(TimeoutException e){
                    fail("doesn't open");
                }
            }
        }
    }
}
