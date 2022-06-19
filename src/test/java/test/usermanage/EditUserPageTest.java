package test.usermanage;

import base.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.admin.usermanage.UserManageHomePage;
import pages.admin.usermanage.subpage.EditUserPage;
import util.LoginUtil;

import static org.assertj.core.api.Assertions.assertThat;

public class EditUserPageTest extends BaseTest {
    private UserManageHomePage index;
    private EditUserPage editUser;
    private String username = "test002";

    @BeforeEach
    public void setup(){
        driver.get(url);
        index = LoginUtil.loginByAdmin().openUserManager();
        index.selectUser().byUserName(username);
        editUser = index.clickEditUserButton();
    }

    @Test
    public void rightEdit(){
        editUser.setRealName("AissEr1")
                .setCellPhone("13212345678")
                .setOfficePhone("13212345678")
                .setEmail("553021234@qq.com");
        editUser.clickSelectOrgButton().select("test2","sss").clickConfirmButton();
        editUser.switchFrame();
        editUser.clickCheckRoleButton().select("管理人员").clickConfirmButton();
        editUser.clickConfirmButton();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driverWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//*[@id=\"content-main\"]/iframe[2]")));
        WebElement elem = driver.findElement(By.xpath("/html/body/div[3]/div[2]"));

        assertThat(elem.getText()).contains("用户: "+username+"更新成功");

        index.selectUser().byUserName(username);
        editUser = index.clickEditUserButton();
        assertThat(editUser.getRealName().getAttribute("value")).isEqualTo("AissEr1");
        assertThat(editUser.getCellPhone().getAttribute("value")).isEqualTo("13212345678");
        assertThat(editUser.getOfficePhone().getAttribute("value")).isEqualTo("13212345678");
        assertThat(editUser.getEmail().getAttribute("value")).isEqualTo("553021234@qq.com");
    }

}
