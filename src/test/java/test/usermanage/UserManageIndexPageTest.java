package test.usermanage;

import base.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.LoginPage;
import pages.admin.AdminHomePage;
import pages.admin.usermanage.UserManageHomePage;
import pages.admin.usermanage.subpage.AddUserPage;
import pages.admin.usermanage.subpage.EditUserPage;
import util.LoginUtil;
import static org.assertj.core.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.fail;

public class UserManageIndexPageTest extends BaseTest {
    private LoginPage login;
    private UserManageHomePage userManage;

    @Nested
    class OpenUserManage{
        @BeforeEach
        public void setUp(){
            driver.get(url);
            login = new LoginPage();
            LoginUtil.loginByAdmin();
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
            private AddUserPage addUser;
            private EditUserPage editUser;
            @BeforeEach
            public void setUpPage(){
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

            @Test
            public void openEditUserTest(){
                String username = "admin";
                userManage.selectUser().byUserName(username);
                editUser = userManage.clickEditUserButton();
                driver.switchTo().defaultContent();
                try{
                    driverWait.until(ExpectedConditions.textToBe(By.xpath("/html/body/div[2]/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td/div/div[1]"),"用户编辑"));
                }catch(TimeoutException e){
                    fail("doesn't open");
                }
                editUser.switchFrame();
                WebElement expected = driver.findElement(By.xpath("//*[@id=\"formobj\"]/table/tbody/tr[1]/td[2]"));
                assertThat(expected.getText()).isEqualTo("admin");
            }
        }
    }
    
}
