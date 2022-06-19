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
import pages.admin.usermanage.subpage.DeleteUserPage;
import util.LoginUtil;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class DeleteUserPageTest extends BaseTest {
    private String username = "test002";
    private UserManageHomePage index;
    private DeleteUserPage deleteUser;

    @BeforeEach
    public void setUp(){
        driver.get(url);
        LoginUtil.loginByAdmin();
        index = AdminHomePage.getInstance().openUserManager();
        index.selectUser().byUserName(username);
        deleteUser = index.clickDeleteOperate();
    }

    @Test
    public void rightTest(){
        assertThat(deleteUser.getHintInformation().getText()).isEqualTo("确定删除该记录吗 ?");
        deleteUser.clickConfirmButton();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driverWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//*[@id=\"content-main\"]/iframe[2]")));
        WebElement elem = driver.findElement(By.xpath("/html/body/div[3]/div[2]"));

        assertThat(elem.getText()).contains("用户："+username+"删除成功");
        assertThat(index.selectUser().byRealName(username)).isNull();
    }
}
