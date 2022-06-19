package test.usermanage;

import base.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.LoginPage;
import pages.admin.AdminHomePage;
import pages.admin.usermanage.UserManageHomePage;
import pages.admin.usermanage.subpage.DisableUserPage;
import pages.admin.usermanage.subpage.EnableUserPage;
import util.LoginUtil;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class EnableUserPageTest extends BaseTest {
    private String username = "test002";
    private UserManageHomePage index;
    private EnableUserPage enableUser;

    @BeforeEach
    public void setUp(){
        driver.get(url);
        LoginUtil.loginByAdmin();
        index = AdminHomePage.getInstance().openUserManager();
        index.selectUser().byUserName(username);
        enableUser = index.clickEnableUserButton();
    }

    @Test
    public void rightTest(){
        assertThat(enableUser.getHintInformation().getText()).isEqualTo("确定激活用户吗");
        enableUser.clickConfirmButton();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driverWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//*[@id=\"content-main\"]/iframe[2]")));
        WebElement elem = driver.findElement(By.xpath("/html/body/div[3]/div[2]"));
        assertThat(elem.getText()).contains("用户："+username+"激活成功!");

        // 重新登录验证
        driver.get(url);
        LoginPage login = new LoginPage();
        LoginUtil.login(login,"test002","1234567");
        driverWait.until(ExpectedConditions.titleIs("物业管理平台"));
        assertThat(login.getUserName()).isEqualTo("test002");
    }
}
