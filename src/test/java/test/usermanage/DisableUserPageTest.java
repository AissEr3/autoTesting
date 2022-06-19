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
import static org.assertj.core.api.Assertions.*;
import util.LoginUtil;

public class DisableUserPageTest extends BaseTest {
    private String username = "test002";
    private UserManageHomePage index;
    private DisableUserPage disableUser;

    @BeforeEach
    public void setUp(){
        driver.get(url);
        LoginUtil.loginByAdmin();
        index = AdminHomePage.getInstance().openUserManager();
        index.selectUser().byUserName(username);
        disableUser = index.clickDisableUserButton();
    }

    @Test
    public void rightTest(){
        assertThat(disableUser.getHintInformation().getText()).isEqualTo("确定禁用用户吗");
        disableUser.clickConfirmButton();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driverWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//*[@id=\"content-main\"]/iframe[2]")));
        WebElement elem = driver.findElement(By.xpath("/html/body/div[3]/div[2]"));
        assertThat(elem.getText()).contains("用户："+username+"禁用成功!");

        driver.get(url);
        LoginUtil.login(LoginPage.getInstance(),username,"1234567");
        // 如果超时了仍然没有显示错误信息，则该测试失败
        try{
            driverWait.until(ExpectedConditions.textToBe(By.id("showErrMsg"),"用户被禁用"));
        }catch(TimeoutException e){
            fail("login error not found error message");
        }
    }
}
